package tn.esprit.pi.services;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Jackpot;

public interface IJackPotService {

	Jackpot addJackpot(Jackpot jackpot);

	Jackpot findJackpot(Event event);

	
	
}
