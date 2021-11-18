package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;


@Repository
public interface KindergardenRepository  extends JpaRepository<Kindergarden, Integer>{
	
	
	Kindergarden findByName(String name);
	Kindergarden findByDirector(User director);
	@Query("select count(k) from Kindergarden k where k.director.idUser =:director ")
	int isDirectorHasKindergarden(@Param("director")int director);
	

}
