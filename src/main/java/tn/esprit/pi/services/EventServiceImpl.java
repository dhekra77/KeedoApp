package tn.esprit.pi.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.Donation;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Notification;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.entities.ParticipationPK;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IAdvertisementRepository;
import tn.esprit.pi.repositories.IDonationRepository;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IJackPotRepository;
import tn.esprit.pi.repositories.INotificationRepository;
import tn.esprit.pi.repositories.IParticipantRepository;
import tn.esprit.pi.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
public class EventServiceImpl  implements IEventService{

	
	@Autowired 
	private IEventRepository iEventRepository;
	
	@Autowired
	private IJackPotRepository iJackPotRepository;

	
	@Autowired 
	private IUserRepository iUserRepository;
	
	@Autowired
	private IParticipantRepository iParticipantRepository;
	
	@Autowired
	private INotificationRepository iNotificationRepository;
	
	
	@Autowired
	private IAdvertisementRepository iAdvertisementRepository;
	
	@Autowired
	private IDonationRepository iDonationRepository;
	
    private static final long interval_milliSeconds = 60*60*1000; // scheduled to run once every hour

	/**********************************Admin**********************************/

	//creating add method that insert  event into database
	@Override
	public void addEvent(Event e) {
		Jackpot j = new Jackpot();
		
		
		j.setSum(0);
		e.setJackpot(j);
		iJackPotRepository.save(j);
		
		iEventRepository.save(e);
	
		Notification n = new Notification();
		n.setUser(null);
		n.setDate(new Date());
		n.setTarget("notify all users");
		n.setDescription("event added welcome and never forget to join us");
		n.setStatus("Not seen yet");
		n.setTime(new Date());
		n.setEvent(e);
		n.setSubject("Event added");
		iNotificationRepository.save(n);
		
	}

	//creating deleting method that remove   event by id  from database
	@Override
	public void deleteEvent(int id) {
		Event e = iEventRepository.findById(id).get();
		iEventRepository.delete(e);
		
	}

	@Override
	public void findEventById(int id) {
		 iEventRepository.findById(id).get();
	} 


	//creating update method that upgrade  event from database
	@Override
	public int updateEvent(Event e, int id) {
		 
			return iEventRepository.updateEvent(e.getTitle(),e.getDate() , e.getHour(), 
						e.getAddress(), e.getDescription(), e.getPlacesNbr(),e.getCategory(),e.getTicketPrice(),e.getStatus(),e.getImage(),e.getIdEvenement());
		}
	
	


	//creating getAll method that retrieve all events from database
	@Override
	public List<Event> getAllEvents() {
		
		List<Event>events = new ArrayList<Event>();
		iEventRepository.findAll().forEach(e ->events.add(e));
		return events;
	}
	//creating getByid method that retrieve event detail from database

	@Override
	public Event getEventById(int id) {
		int countView;
		Event e = iEventRepository.findById(id).get();
		if(e == null) return null;
		
		e.setViews(e.getViews()+1);
	    countView = iEventRepository.updateViewCountEvent(e.getViews()-1,e.getIdEvenement());
			countView++;
			
		return e ;  

	}
	
	
	
	
	@Override
	public Event findEventByName(String name) {
		return iEventRepository.findEventByName(name);
	}

	
	//creating filterEvent method that filter event by his category
	@Override
	public List<Event> filterEvent(EventCategory category) {
		// TODO Auto-generated method stub
		return iEventRepository.filterByCategory(category);
	}

	
	//creating getEventsByViews method that retrieve top 3 events by views

	@Override
	public Map<Integer, Integer> getEventsByViews() {
		List<Integer>listId = new ArrayList<>();
		List<Integer>listViews = new ArrayList<>();
		Map<Integer,Integer> h = new HashMap<>();

		
		for(Event e : iEventRepository.findAll()) {
			
			listId.add(e.getIdEvenement());
			listViews.add(e.getViews());
			
		}
		
		List<Integer>sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);
		
		for(int i = 0 ; i <2 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
			h.put(ind, max);

			System.out.println(ind +" "+ max);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
		}
		return h;
	}

	
	//creating affectedEventUser that affect user to event
	/*@Override
	public String addParticipation(int iduser, int idevent) {
		
				Event event = iEventRepository.findById(idevent).get();
		
		User u = iUserRepository.findById(iduser).get();
		

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
	
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		
		List<Participation>participations = (List<Participation>) iParticipantRepository.findAll();
		for(int i=0 ; i<participations.size();i++) {
			if(participations.get(i).getEvent().getIdEvenement() == idevent && 
					participations.get(i).getUser().getIdUser() == iduser)
		
				return "You are already participate !!";
			
		}
		
		
		if(event.getPlacesNbr() >0) {
			float discPercent =(100f-event.getDiscountPercentage())/100f;
			float newPrice =event.getTicketPrice() * discPercent;// pourcentage de réduction ticket		}
		
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		p.setParticipationDate(dateFormat.format(date));	
		event.setPlacesNbr(event.getPlacesNbr() - 1);

		event.setParticipantsNbr(event.getParticipantsNbr() + 1);
		
		event.setCollAmount(event.getCollAmount()+ newPrice);
		p.setPrice(newPrice);
		p.setParticipationPK(participationPK);
		p.setParticipationDate(new Date().toString());
		
		
		iParticipantRepository.save(p);
		iEventRepository.save(event);
		}
		
		
		return "Affected successfully with discount percentage";
		
	}

	*/
	//creating displayBestEventsByViews that display most 3 views event
	@Override
	public List<String> displayBestEventsByViews() {
		
		List<String>list = new ArrayList<>();
		String s ="";
		List<Integer>listId = new ArrayList<>();
		List<Integer>listViews = new ArrayList<>();
		
		List<Event> listEvent = (List<Event>)iEventRepository.findAll();
		
		for(Event e : listEvent) {
			listId.add(e.getIdEvenement());
			listViews.add(e.getViews());
		
		}
		
		List<Integer> sortedList = new ArrayList<>(listViews);
		
		Collections.sort(sortedList);
		
		for(int i = 0 ; i<2 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));
			s = (i+1)+"--Event: "+iEventRepository.findById(ind).get().getTitle()+"  = with"+max+" views";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
			}
		
		
		return list;
	}
	
	public String affecterCategoryEvent(String category, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		String msg=" ";
		
		//bch n7awl men string l enum => CategoryEvent.valueOf(category)
		try {
		for(EventCategory c : EventCategory.values()) {
			if(c == EventCategory.valueOf(category)) {
				event.setCategory(EventCategory.valueOf(category));
				iEventRepository.save(event);
				return msg ="Category of Event Affected successfully to event!";
			}
		}
	}catch(Exception e) {
			 msg="Failed to affected Category";
		}
		return msg;
	}

	@Override
	public String affecterEventAdv(int idavert, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		
		Advertisement adv= iAdvertisementRepository.findById(idavert).get();
		
		adv.setEvent(event);
		
		iAdvertisementRepository.save(adv);
		return "Event affected succesfully to advertisement";
	
	}

	@Override
	public List<Event> upcomeEvents() {
		List<Event> list= iEventRepository.upcomingEvents();
		
		return list;	
			}


	
	@Override
	public void refundUsers(int eid) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		Event ev = iEventRepository.findById(eid).get();
		User u = new User();
		
		
	

		List<Participation> participationsOfEvent = iParticipantRepository.Participations(ev);
		
		
		System.out.println("hiii==");
		Notification n = new Notification();

		if(!ev.getParticipations().isEmpty()) {
		for(Participation p : participationsOfEvent) {
			//int u = new User();//user id
			u = iUserRepository.findById(p.getUser().getIdUser()).get();
			float refundedAmount = p.getPrice();
			ev.setCollAmount(ev.getCollAmount()-refundedAmount);
			//iUserRepository.save(u);
			System.out.println("hiiiiii==");

			iParticipantRepository.delete(p);
			
			
			iEventRepository.save(ev);
			

			n.setEvent(ev);
			n.setUser(u);
		
			
			n.setTarget("Sepecific users");
			LocalTime localTime = LocalTime.now();

			n.setTime(Time.valueOf(localTime));
			n.setSubject("Canceled Event");
			n.setDescription("Dear "+u.getLastName()+" "+u.getLastName()+""
					+ ",we regret to announce that the event "+ev.getTitle()+" you want to attend has been canceled for some reason."
					+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
					+ " Thank you.");
			n.setDate(date);
			
			n.setStatus("Not Seen Yet");

			
			iNotificationRepository.save(n);
		
		}
		
	}else {
		System.out.println("event without participations");
	}
		
		List<Donation>donationsResult =  iDonationRepository.DonationOfEvent(ev);
		
		for(Donation d : donationsResult) {
			User user = d.getUser();
			
			float refundedAmount = d.getAmount();// flous ta3 donation
			System.out.println("donation="+refundedAmount);
			System.out.println("jackpot before=="+ev.getJackpot());
			ev.getJackpot().setSum(ev.getJackpot().getSum()- refundedAmount);//fhmtha?? brbi haw jit njib 9ahwa
			System.out.println("jackpot after retrieve money=="+ev.getJackpot().getSum());
			System.out.println("collAmount before=="+ev.getCollAmount());
			ev.setCollAmount(ev.getCollAmount()-refundedAmount);
			
			System.out.println("jackpot=="+ev.getCollAmount());

		//	System.out.println("Accurance balance before="+u.getAccBalance());
			//u.setAccBalance(u.getAccBalance()+refundedAmount);

			//System.out.println("accuranceBalance="+u.getAccBalance());
			
			//************Notification*******************//
			Notification notification = new Notification();
			notification.setSubject("Remoboursement");
			notification.setUser(user);
			notification.setDescription("Dear "+user.getFirstName()+" We annonce that the event"+ev.getTitle()+
					"was canceled for some reasons that's why we refunded your donation . for more informations do not hesitate to contact us"
					+ "thank you");
			notification.setDate(new Date());
			notification.setStatus("not seen Yet");
			iDonationRepository.deleteById(d.getId());
			iEventRepository.save(ev);
			iUserRepository.save(u);

		}
		
	}
	
	
	@Override
	public List<String> displayEventsByParticipants() {
		// TODO Auto-generated method stub
		List<Event> events = (List<Event>) iEventRepository.findAll();
		List<Integer>listIdEvent = new ArrayList<>();
		List<Integer>listNbrParticipantEvent = new ArrayList<>();
		List<String>results = new ArrayList<>();
		String message = "";
		for(Event event : events) {
			listIdEvent.add(event.getIdEvenement());
			listNbrParticipantEvent.add(event.getParticipantsNbr());
			
		}
		
		List<Integer>sortListed =new ArrayList<>(listNbrParticipantEvent);
		Collections.sort(sortListed);
		
		for(int i = 0; i<2 ; i++) {
			int max = sortListed.get(sortListed.size() - 1);
			int idEventByPart = listIdEvent.get(listNbrParticipantEvent.indexOf(max));
			message ="Event "+iEventRepository.findById(idEventByPart).get().getTitle()+"with "+max+" Participations";
			results.add(message);
			sortListed.remove(sortListed.size()-1);
			listNbrParticipantEvent.set(listNbrParticipantEvent.indexOf(max),-1);
		}
		
		return results;
		
		
	}

	@Override
	public List<String> displayEventsByCollAmount() {
	// TODO Auto-generated method stub
		List<Event> events = (List<Event>) iEventRepository.findAll();
		List<Integer>listIdEvent = new ArrayList<>();
		List<Float>listCollAmountEvent = new ArrayList<>();
		List<String>results = new ArrayList<>();
		String message = "";
		for(Event event : events) {
			listIdEvent.add(event.getIdEvenement());
			listCollAmountEvent.add(event.getCollAmount());
		
		}
	
		List<Float>sortListed =new ArrayList<>(listCollAmountEvent);
		Collections.sort(sortListed);
	
		for(int i = 0; i<2 ; i++) {
			float max = sortListed.get(sortListed.size() - 1);//ekher valeur 
			int idEventByPart = listIdEvent.get(listCollAmountEvent.indexOf(max));//nlawj al event eli andou akther partic o yraje3 id event 
			message ="Event "+iEventRepository.findById(idEventByPart).get().getTitle()+"with "+max+" dinars  , Collectd Amount ";
			results.add(message);
			sortListed.remove(sortListed.size()-1);
			listCollAmountEvent.set(listCollAmountEvent.indexOf(max),(float) -1);
		}
	
			return results;
	
		}

	@Override
	public Event findbyId(int id) {
		// TODO Auto-generated method stub
		return iEventRepository.findById(id).get();
	}
	//Resultat Events List between Two Dates 
	public String ResultEvent(List<Event> events,int i) {
		return "Event "+i+""+"Titre: "+events.get(i).getTitle()+
				""+"--Description: "+events.get(i).getDescription()+
				""+"--Addresse: "+events.get(i).getAddress()+
				""+"--CollectAmount: "+events.get(i).getCollAmount()+
				""+"--Discount: "+events.get(i).getDiscountPercentage()+
				""+"--PriceTicket: "+events.get(i).getTicketPrice()+
				""+"--Views: "+events.get(i).getViews()+
				""+"--NumberOfPlaces: "+events.get(i).getPlacesNbr()+
				""+"--ParticipantsNbr: "+events.get(i).getParticipantsNbr()+
				""+"--Category: "+events.get(i).getCategory();
	}
	
	
	
	
	
	@Override
	public List<String> getEventTwoDatesBeetween(Date date1, Date date2) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		List<Event>events = (List<Event>) iEventRepository.findAll();
		List<String>results = new ArrayList<>();
		
		if(date1.after(date2)) {
			return null;
		}
		
		for(int i = 0 ; i<events.size();i++) {
			if((events.get(i).getDate().after(date1) &&( events.get(i).getDate().before(date2)))) {
				results.add(ResultEvent(events,i));
			}
			
		}
		if(results.isEmpty()) {
			 results.add("Event Not Found between two dates we are sorry :( :( ");
			 return results;
		}
		return results;
	}
	@Scheduled(fixedRate=interval_milliSeconds)
	@Override
	public void reintializeJackPotAfterDateEvent(int idevent) {
		
		Jackpot jack = iJackPotRepository.findJackpotEvent(idevent);
		
		Event event = iEventRepository.findById(idevent).get();
		Date now = new Date();
		if(now.getTime() - event.getDate().getTime() >=1) {
			jack.setSum(0);
			iJackPotRepository.save(jack);
		}
		
		
		
	}
	



}
