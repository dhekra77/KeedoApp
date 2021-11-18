package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Bus;
@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
	
	@Query("SELECT b FROM Bus b WHERE b.driver.firstName =:firstName")
	public List<Bus> getBusByDriver(@Param("firstName")String D);

}
