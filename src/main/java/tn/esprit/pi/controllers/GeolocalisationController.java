package tn.esprit.pi.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Geolocalisation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.services.GeolocalisationService;

@RestController
@RequestMapping("/loc")
public class GeolocalisationController {

	@Autowired
    private GeolocalisationService serviceGeo;
	
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @PostMapping("/affect/{idKid}")
    public Geolocalisation AffectlocalisationKid(@RequestBody Geolocalisation geolocalisation,@PathVariable("idKid")int idKid){
        return serviceGeo.save(geolocalisation, idKid);
    }
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @DeleteMapping("/delete/{id}")
    public void deleteGeo(@RequestBody Geolocalisation geolocalisation, @PathVariable("id")int id){
        serviceGeo.delete(id);
    }
    
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @GetMapping(value="/getLocation/{id}")
    public Geolocalisation getLocationById(@PathVariable int id){
        return serviceGeo.getGeoLocationById(id);
    }
    
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @PostMapping("/getLocation")
    public Geolocalisation getLocation(@RequestBody Kid kid){
        return serviceGeo.getGeoLocation(kid);
    }
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @RequestMapping(value = "/getDistance/{kidID1}/{kidID2}", method = RequestMethod.GET)
   
    public double getLocation(@PathVariable("kidID1") int kidID1, @PathVariable("kidID2") int kidID2){

        Kid parm1 = new Kid();
        Kid parm2 =new Kid();
        parm1.setIdKid(kidID1);
        parm2.setIdKid(kidID2);
        Geolocalisation g1 = serviceGeo.getGeoLocation(parm1);
        Geolocalisation g2 =serviceGeo.getGeoLocation(parm2);

        return serviceGeo.getDistance(g1, g2);
    }


	
	
}
