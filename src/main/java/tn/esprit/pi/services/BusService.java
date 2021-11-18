package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Driver;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.BusRepository;
import tn.esprit.pi.repositories.DriverRepository;
import tn.esprit.pi.repositories.IUserRepository;


@Service
public class BusService implements IBusService {


	@Autowired
	private BusRepository  busRepository;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<Bus> getAllBus() {
		// TODO Auto-generated method stub
		 return busRepository.findAll();
	}

	@Override
	 public Bus AffectBusDriver(Bus bus, int idDriver, int idU) {
		// TODO Auto-generated method stub
		Driver driver =driverRepository.findById(idDriver).get();
		User user=userRepository.findById(idU).get();
		bus.setDriver(driver);
		bus.setUser(user);
		busRepository.save(bus);
		return bus; 
		
	}

	@Override
	public Bus getBusById(int id) {
		// TODO Auto-generated method stub
		return busRepository.findById(id).get();
	}

	@Override
	public void deleteBusById(int id) {
		busRepository.deleteById(id);		
	}

	@Override
	public Bus updateBus(Bus bus, int id) {
		Bus b=busRepository.findById(id).get();
	//	Driver driver =driverRepository.findById(idDriver).get();
		//User user=userRepository.findById(idU).get();
		b.setCapacity(bus.getCapacity());
		b.setDisponible(bus.getDisponible());
		b.setDeparture(bus.getDeparture());
		b.setDestination(bus.getDestination());
		b.setDriver(bus.getDriver());
		b.setUser(bus.getUser());
		b.setKids(bus.getKids());
		b.setTimeA(bus.getTimeA());
		b.setTimeDep(bus.getTimeDep());
		
		
		return busRepository.save(b);
	}
	
	@Override
	public List<Bus> getBusByDriver(String D) {
		// TODO Auto-generated method stub
		return busRepository.getBusByDriver(D);
	}
	 

}
