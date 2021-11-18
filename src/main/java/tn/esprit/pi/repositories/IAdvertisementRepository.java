package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Advertisement;

public interface IAdvertisementRepository extends CrudRepository<Advertisement,Integer >{

	@Query("SELECT a FROM Advertisement a WHERE a.canal = :name")
	 public Advertisement findAdvertisementByCanal(@Param("name")String name);

}
