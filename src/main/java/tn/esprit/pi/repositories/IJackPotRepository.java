package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.pi.entities.Jackpot;

@Repository
public interface IJackPotRepository  extends CrudRepository<Jackpot,Integer>{
	@Query("SELECT e.jackpot FROM Event e WHERE e.id=:id ")
	Jackpot findJackpotEvent(@Param("id") int event);
}
