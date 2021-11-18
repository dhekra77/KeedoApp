package tn.esprit.pi.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.controllers.AuthController;
import tn.esprit.pi.controllers.UserController;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.entities.ParticipationPK;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IParticipantRepository;
import tn.esprit.pi.repositories.IUserRepository;


@Service
public class ParticipationServiceImpl implements IParticipationService{
	@Autowired
	EventServiceImpl eventServiceImpl;
	@Autowired
	IParticipantRepository iParticipationRepository;
	@Autowired
	IEventRepository iEventRepository;
	@Autowired
	IUserRepository iUserRepository;

@Override
public String addParticipation(int iduser, int idevent) {

	Event event = iEventRepository.findById(idevent).get();
	
	
	User user = iUserRepository.findById(iduser).get();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	int number = 0;
	Participation p = new Participation();
	ParticipationPK participationPK = new ParticipationPK();
	List<Participation>participations = (List<Participation>) iParticipationRepository.findAll();
	
	
	Date currentdate = new Date();
	if(currentdate.getTime() - event.getDate().getTime() >0)
		return "Event finished you can't participate ";
	
	
	if(user.getRole().equals(RoleType.Admin)) 
			return "Admin couldn't  participate to any event ";

	
	for(int i=0 ; i<participations.size();i++) {
		if(participations.get(i).getEvent().getIdEvenement() == idevent && 
				participations.get(i).getUser().getIdUser() == iduser)
	
			return "You are already participate !!";
		
	}
	
	if(event.getPlacesNbr() > 0) {
		
		System.out.println("/////---"+participations.size()+", "+event.getNbrTicketEarlyParticipants());
		if(event.isEarlyParticipants() == true && participations.size()<event.getNbrTicketEarlyParticipants()) {

			float discPercent =(100f-event.getDiscountPercentage())/100f; 
			float newPrice =event.getTicketPrice() * discPercent;
		
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		p.setParticipationDate(dateFormat.format(date));	
		event.setPlacesNbr(event.getPlacesNbr() - 1);
	    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
	    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+newPrice);
		event.setCollAmount(event.getCollAmount()+ newPrice);
		user.setAccBalance(user.getAccBalance()-newPrice);
		p.setPrice(newPrice);
		p.setParticipationPK(participationPK);
		//participationPK.setNumber(number);
	//	p.setParticipationDate(new Date());
		iParticipationRepository.save(p);
		iUserRepository.save(user);
	
		}	
		else {
			participationPK.setIdEvent(event.getIdEvenement());
			participationPK.setIdUser(iduser);
			p.setParticipationDate(dateFormat.format(date));	
			event.setPlacesNbr(event.getPlacesNbr() - 1);
		    event.setParticipantsNbr(event.getParticipantsNbr() + 1);
		    System.out.println("coll amount =="+event.getCollAmount()+", newPrice="+event.getTicketPrice());
			event.setCollAmount(event.getCollAmount()+ event.getTicketPrice());
			user.setAccBalance(user.getAccBalance()-event.getTicketPrice());

			p.setPrice(event.getTicketPrice());
			p.setParticipationPK(participationPK);
			//participationPK.setNumber(number);
			//p.setParticipationDate(new Date());
			
			
			iParticipationRepository.save(p);

		iUserRepository.save(user);
	}
	return "Affected successfully with discount percentage";
	
}
return "Event places is full";
}

	@Override
	public List<Participation> participationsList() {
		List<Participation> list = (List<Participation>) iParticipationRepository.findAll();
		return list;
}


	@Override
	public List<Participation> myParticipations() {
		// TODO Auto-generated method stub
//		 		List<Participation>list = iParticipationRepository.myParticipations(AuthController.USERCONNECTED);

		 		return null;
	}

}
