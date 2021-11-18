package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Driver;

public interface IDriverService {
	
    List<Driver> getAllDriver();
 	Driver saveDriver(Driver driver);
	Driver getDriverById(int id);
	void deleteDrivereById(int id);
	public Driver updateDriver(Driver driver, int id);
}
