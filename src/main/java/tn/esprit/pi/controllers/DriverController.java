	package tn.esprit.pi.controllers;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.domain.Page;
	import org.springframework.security.access.prepost.PreAuthorize;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	
	import tn.esprit.pi.entities.Driver;
	
	import tn.esprit.pi.services.DriverService;
	

	@RestController
	
	@RequestMapping("/driver")
	
	public class DriverController {
		
		
		@Autowired
		private DriverService driverservice;
		
		
		//localhost:9293/SpringMVC/servlet/driver/showDriver
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		 @GetMapping("/showDriver")
		    public List<Driver>showDriver() {
		        return driverservice.getAllDriver();
		    }
		
		//localhost:9293/SpringMVC/servlet/driver/saveDriver
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@PostMapping("/saveDriver")
		public String saveDriver(@RequestBody Driver driver)   
			{  
				driverservice.saveDriver(driver);  
				return "redirect:/";
			}
		 
		
		//localhost:9293/SpringMVC/servlet/driver/deleteDriver/1
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@RequestMapping(value = "/deleteDriver/{id}", method = RequestMethod.DELETE)
		     void deleteDriver(@PathVariable int id) {
	
			  driverservice.deleteDrivereById(id);
	           
		}
		
		//localhost:9293/SpringMVC/servlet/driver/updatedriver/2
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@PutMapping("/updatedriver/{idDriver}")
		public Driver updateDriver(@RequestBody Driver driver, @PathVariable("idDriver")int idDriver) {
	
			return driverservice.updateDriver(driver,idDriver);
		 
		}
		//localhost:9293/SpringMVC/servlet/driver/showbyid/2
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@GetMapping("/showbyid/{id}")
	    public Driver showbyid(@PathVariable int id) {
	        return driverservice.getDriverById(id);
	    }
	
		
		
	}
