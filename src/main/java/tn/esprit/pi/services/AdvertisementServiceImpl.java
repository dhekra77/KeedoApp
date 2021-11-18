package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.TypeAd;
import tn.esprit.pi.repositories.IAdvertisementRepository;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService{
	@Autowired
	private IAdvertisementRepository iAdvertisementRepository;
	/**********************Creating add method that insert advertisement into database***************/
	@Override
	public Advertisement addAdvertisement(Advertisement adv) {
		
		iAdvertisementRepository.save(adv);
		return adv;
	}
    /*******************Creating deleting method that remove Advertisement by id  from database*********/
	@Override
	public void deleteAdvertisement(int idAdv) {
		Advertisement adver = iAdvertisementRepository.findById(idAdv).get();
		iAdvertisementRepository.delete(adver);
		
	}
	/*******************Creating updating method that remove advertisement by id  from database*********/
	@Override
	public Advertisement updateAdvertisement(Advertisement adv, int idAd) {
		Advertisement a = adv;
		return iAdvertisementRepository.save(a);
	}
	/*****************Creating getAll method that retrieve all advertisement from database **************/
	@Override
	public List<Advertisement> getAllAdvertisements() {
		List<Advertisement>advertisements= new ArrayList<Advertisement>();
		iAdvertisementRepository.findAll().forEach(adv ->advertisements.add(adv));
		return advertisements;
	}
	/**************Creating getByid method that retrieve advertisement detail from database************/
	@Override
	public Advertisement getAdvertisementById(int idAd) {
		return iAdvertisementRepository.findById(idAd).get();
	}
	/**********************Creating getByName method that retrieve advertisement by Canal Name (facebook,Youtube,...)**********************/
	@Override
	public Advertisement findAdvertisementByName(String name) {
		return iAdvertisementRepository.findAdvertisementByCanal(name);
	}
	@Override
	public String affecterAdCategoryAdvertisement(String typeAd, int idadv) {
		Advertisement advertisement = iAdvertisementRepository.findById(idadv).get();
		String msg=" ";
		
		//bch n7awl men string l enum => CategoryEvent.valueOf(category)
		try {
		for(TypeAd c : TypeAd.values()) {
			if(c == TypeAd.valueOf(typeAd)) {
				advertisement.setTypeAd(TypeAd.valueOf(typeAd));
				iAdvertisementRepository.save(advertisement);
				return msg ="TypeAd Affected successfully!";
				
			}
		}
	}catch(Exception e) {
			 msg="Failed to affected TypeAd";
		}
		return msg;
		
		
	}


	
	
	
}
