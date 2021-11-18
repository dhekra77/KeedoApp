package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Kid;

@Repository
public interface KidRepository extends CrudRepository<Kid, Integer> {

	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ")
	List<Kid> DisplayAll();
	
	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ORDER BY k.firstName ASC")
	List<Kid> orderKidsByNameAsc();
	
	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ORDER BY k.firstName DESC")
	List<Kid> orderKidsByNameDESC();
	
	@Transactional
	@Modifying
	@Query("DELETE from Kid k where k.id= :id")
	void deleteKidById(@Param("id") int id);
	
	@Query("SELECT k FROM Kid k where k.daycare= :daycare")
	List<Kid> displayKidsByDaycare(@Param("daycare") Daycare daycare);
	
	/*@Query(value="SELECT k.birth_date, COUNT(k.birth_date) FROM Kid k GROUP BY k.birth_date ORDER BY COUNT(e.label) DESC", nativeQuery= true)
	int nbrKidPerAge();*/
	
}
