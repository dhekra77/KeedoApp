package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.Donation;
import tn.esprit.pi.entities.Event;


public interface IDonationRepository extends CrudRepository<Donation,Integer >{
	
	@Query("SELECT d FROM Donation d WHERE d.event=:event ")
	List<Donation> DonationOfEvent(@Param("event") Event event);
	

}
