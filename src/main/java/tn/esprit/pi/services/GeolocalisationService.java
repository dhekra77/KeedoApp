package tn.esprit.pi.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Geolocalisation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.repositories.GeolocalisationRepository;
import tn.esprit.pi.repositories.KidRepository;



@Service
public class GeolocalisationService implements IGeolocalisationService<Geolocalisation> {

	@Autowired
    private GeolocalisationRepository repo;
	@Autowired
	private KidRepository kidRepository;



    @Override
    public Geolocalisation save(Geolocalisation geolocalisation, int idKid) {
    	Kid kids=kidRepository.findById(idKid).get();
    	geolocalisation.setKid(kids);
         repo.save(geolocalisation);
        return geolocalisation;
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Override
	public Geolocalisation getGeoLocationById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}
    

    @Override
    public Geolocalisation getGeoLocation(Kid kid) {
        return repo.getLocation(kid);
    }



    @Override
    public double getDistance(Geolocalisation geolocalisation1, Geolocalisation geolocalisation2) {
        double lat1 = geolocalisation1.getLatitude();
        double lon1 = geolocalisation1.getLongitude();

        double lat2 = geolocalisation2.getLatitude();
        double lon2 = geolocalisation2.getLongitude();


        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

	

	



}