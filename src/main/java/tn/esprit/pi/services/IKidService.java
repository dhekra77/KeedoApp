package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;

public interface IKidService {

	String addKid(Kid kid) throws Exception;

	void deleteKid(int id);

	Kid displayKid(int id);

	List<Kid> displayAllKids();

	Retour<Kid> updateKid(int id, Kid kid);

	List<Kid> orderKidsByNameAsc();

	List<Kid> orderKidsByNameDesc();

	String affectKidToDaycare(int idK, int idD);

	Retour<Kid> deleteKidFromDaycare(int idK, int idD);

	int nbrKid();
	
	//dhekra
	String affectKidToBus(Kid kid , int idBus);
}
