package tn.esprit.pi.services;


import tn.esprit.pi.entities.EmpruntBook;

public interface IEmpruntService {

	
	EmpruntBook findById(int id);
	
	
}
