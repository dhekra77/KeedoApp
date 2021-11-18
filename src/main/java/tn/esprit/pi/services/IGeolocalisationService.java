package tn.esprit.pi.services;
import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Geolocalisation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Kindergarden;

public interface IGeolocalisationService<T> {

	public T save(T t,int idKid);
    public void delete(int id);
    Geolocalisation getGeoLocation(Kid idKid);
    Geolocalisation getGeoLocationById(int id);
  //  Geolocalisation getGeoLocationkindergarden(Kindergarden id);
    double getDistance(T t1, T t2);
	
	
}