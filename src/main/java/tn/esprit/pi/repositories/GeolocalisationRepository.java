package tn.esprit.pi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Geolocalisation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Kindergarden;

@Repository
public interface GeolocalisationRepository extends JpaRepository<Geolocalisation, Integer> {
    
	
 @Query(value = "SELECT g FROM Geolocalisation g where g.id = (SELECT max(gg.id) FROM Geolocalisation gg where gg.kid = :idKid)")
 Geolocalisation getLocation(@Param("idKid") Kid KidId);
    
    
    
}