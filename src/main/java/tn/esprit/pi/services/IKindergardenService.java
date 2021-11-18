package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;

public interface IKindergardenService {
	
	Kindergarden addKindergarden(Kindergarden kindergarden, int director);
	List<Kindergarden> retrieveAllKindergardens();
	void removeKindergarden(int id);
	String updateKindergarden(Kindergarden kindergarden,int id) throws Exception;

	Kindergarden getKindergardenById(int id);
	int countKindergardens();
	Kindergarden getKindergardenByName(String name);
	Kindergarden getKindergardenByDirector(int director);
	
	
}
