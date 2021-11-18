package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.entities.User;

@Repository
public interface IParticipantRepository extends CrudRepository<Participation, Integer> {

	@Query("SELECT p FROM Participation p WHERE p.event=:event")
	
	List<Participation> Participations(@Param ("event") Event event);
	
	@Query("SELECT p FROM Participation p WHERE p.user=:user")
	List<Participation> myParticipations(@Param ("user") User user);
}
