package tn.esprit.pi.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IDonationRepository;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IJackPotRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.entities.Donation;


//Add Donation to a jackpot an event
@Service
public class DonationServiceImpl implements IDonationService  {

	@Autowired
	IEventRepository iEventRepository;
	
	@Autowired
	IJackPotRepository iJackpotRepository;
	
	@Autowired
	IDonationRepository iDonationRepository;
	
	@Autowired
	IUserRepository iUserRepository;
	
	//Donation lel event w kol event endo jackpot w kol jackpot fiha somme donation
	@Override
	public String Donation(int eid,int uid, float amount) {
		float totale=0;
		float newCollAmount=0;
		Donation donation = new Donation();
		Event ev = iEventRepository.findById(eid).get();
		User user = iUserRepository.findById(uid).get();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		//attribut static  dans l'user 
		Jackpot jackpot = new Jackpot();
		System.out.println(ev.getJackpot());
		
		
		if(date.getTime() - ev.getDate().getTime() >0)
			return "Event finished you can't donate  ";
	
		if(user.getAccBalance() >=amount) {
			user.setAccBalance(user.getAccBalance() - amount);
			Jackpot j = iJackpotRepository.findJackpotEvent(ev.getIdEvenement());
			j.setSum(j.getSum() + amount);
			ev.setCollAmount(ev.getCollAmount() + amount);
			donation.setAmount(amount);
			donation.setContributionDate(dateFormat.format(date));
			donation.setEvent(ev);
			donation.setUser(user);
			iUserRepository.save(user);
			iDonationRepository.save(donation);
			iJackpotRepository.save(j);
			iEventRepository.save(ev);
			/*totale = user.getAccBalance() - amount;
			jackpot = iEventRepository.findJackpot(ev.getJackpot());
			System.out.println("sum="+jackpot.getTotal());
			donation.setAmount(amount);
			donation.setDate(date);
			donation.setEvent(ev);
			donation.setUser(user);
			jackpot.setTotal(jackpot.getTotal()+amount);
			iJackpotRepository.save(jackpot);
			iEventRepository.save(ev);
            iDonationRepository.save(donation);*/
			
			
			return "Donation saved successfully!!";
		}
		return "Balance amount below than amount of donation we are sorry ";
	}

	@Override
	public List<Donation> getDonationEvention(Event event) {
		List<Donation> list = iDonationRepository.DonationOfEvent(event);
		return list;
	}

	@Override
	public List<Donation> getHistoryDonation() {
		return null;
		/*List<Donation> list = iDonationRepository.DonationOfUser(UserController.USERCONNECTED);
		return list;*/
	}


}