package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.repositories.IJackPotRepository;

@Service
public class JackPotServiceImpl implements IJackPotService {
	
	
	@Autowired
	IJackPotRepository iJackPotService;
	
	@Override
	public Jackpot addJackpot(Jackpot jackpot) {
		Jackpot j = new Jackpot();
		j.setSum(0);
		return iJackPotService.save(j);
		
	}

	@Override
	public Jackpot findJackpot(Event event) {
		
		Jackpot jackpot = event.getJackpot();
		return jackpot;
	}
}
