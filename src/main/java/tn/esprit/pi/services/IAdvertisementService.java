package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Advertisement;

public interface IAdvertisementService {

	public Advertisement addAdvertisement(Advertisement adv);
	   public void deleteAdvertisement(int idAd);
	   public Advertisement updateAdvertisement(Advertisement adv , int idAd);
	   
	   public List<Advertisement> getAllAdvertisements();
	   
	   public Advertisement getAdvertisementById(int idAd);
	   
	   public Advertisement findAdvertisementByName(String name);
	   
	   public String affecterAdCategoryAdvertisement(String typeAd, int idadv);
}
