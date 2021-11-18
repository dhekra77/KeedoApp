package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Driver;
import tn.esprit.pi.services.BusService;




@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	
	
	//localhost:9293/SpringMVC/servlet/bus/showBus
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	 @GetMapping("/showBus")
	    public List<Bus> showNewDrivereForm() {
	        return busService.getAllBus();
	    }
	
	
	//localhost:9293/SpringMVC/servlet/bus/saveBus/1/1
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	 @PostMapping("/saveBus/{idDriver}/{idU}")
		public Bus AffectBusDriver(@RequestBody Bus bus, @PathVariable("idDriver")int idDriver,@PathVariable("idU")int idU)   
		{  
			 
			return busService.AffectBusDriver(bus, idDriver,idU);
		}
	 
	//localhost:9293/SpringMVC/servlet/bus/deleteBus/1
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@DeleteMapping("/deleteBus/{id}")
		public void deleteDriver(@PathVariable("id") int id) {
			busService.deleteBusById(id);
		}
	//localhost:9293/SpringMVC/servlet/bus/updateBus/1
	@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/updateBus/{idBus}")  
		public Bus updateBus(@RequestBody Bus bus, @PathVariable("idBus")int idBus)   
		{  
		
			busService.updateBus(bus, idBus) ;
			return bus;  
		} 
	//localhost:9293/SpringMVC/servlet/bus/showBusByDriver/dhekra
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@GetMapping("/showBusByDriver/{firstName}")
		public List<Bus> getBusByDriver(@PathVariable String firstName) {
			 List<Bus> bus = busService.getBusByDriver(firstName);
			return bus;
			}
		
}
