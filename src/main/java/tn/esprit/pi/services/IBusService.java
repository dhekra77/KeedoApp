package tn.esprit.pi.services;

import java.util.List;

import org.springframework.data.domain.Page;



import tn.esprit.pi.entities.Bus;



public interface IBusService {
	List<Bus> getAllBus();
	
	Bus  AffectBusDriver(Bus bus,int idDriver,int idU);
	Bus getBusById(int id);
	void deleteBusById(int id);
	Bus updateBus(Bus bus, int id);
	List<Bus> getBusByDriver(String D);
	
}
