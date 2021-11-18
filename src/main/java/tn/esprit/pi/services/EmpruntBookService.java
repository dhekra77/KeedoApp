package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.repositories.EmpruntBookRepository;

@Service
public class EmpruntBookService implements IEmpruntService{

	@Autowired
	private EmpruntBookRepository empruntBookRepository;
	
	@Override
	public tn.esprit.pi.entities.EmpruntBook findById(int id) {
		// TODO Auto-generated method stub
		return empruntBookRepository.findById(id).get();
	}

}
