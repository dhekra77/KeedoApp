package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Donation;

public interface IDonationService {

	public String Donation(int eid,int uid, float amount);
	public List<Donation> getDonationEvention(Event event) ;
	public List<Donation> getHistoryDonation();
}