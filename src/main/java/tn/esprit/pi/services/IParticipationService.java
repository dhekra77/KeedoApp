package tn.esprit.pi.services;
import java.util.List;

import tn.esprit.pi.entities.Participation;

public interface IParticipationService {

	 String addParticipation(int iduser, int idevent);
	List<Participation> participationsList();
	List<Participation> myParticipations();
}

