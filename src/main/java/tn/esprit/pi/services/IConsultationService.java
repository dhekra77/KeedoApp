package tn.esprit.pi.services;

import java.util.List;
import java.util.Map;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;

public interface IConsultationService {

	String affectConsultationToKid(Consultation consultation, int idK, int idD) throws Exception;

	void deleteConsultation(int id);

	Consultation updateConsultation(int idC, Consultation consultation);

	List<Consultation> displayAllConsultations();

	Consultation displayById(int id);
	
	List<Consultation> displayConsultationsToDay();

	List<Consultation> displayConsultationsByKid(int idK);

	List<Consultation> displayConsultationsByDoctor(int idD);

	List<Consultation> displayMyConsult() throws Exception;

	Map<String, Integer> percentageParticipationByDoctor();

	int nbrConsultPerMonth(int m);
}
