package tn.esprit.pi.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.ws.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.el.parser.ParseException;

import tn.esprit.pi.config.FileUploadUtil;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.services.EventServiceImpl;
import tn.esprit.pi.services.ParticipationServiceImpl;

@RestController
public class EventController {

	@Autowired  
	private EventServiceImpl eventServiceImpl;  

	
	@Autowired
	private ParticipationServiceImpl participationServiceImpl;
	
	
	

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') or hasAuthority('Doctor')")
	@GetMapping("/event/get-all-events")  
	public List<Event> getAllEvents()   
	{  
		return eventServiceImpl.getAllEvents();  
	}  
	
	
	//creating a get mapping that retrieves the detail of a specific event  
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")
	@GetMapping("/event/detail-event/{eventid}")  
	public Event getEvent(@PathVariable("eventid") int eventid)   
	{  
		return eventServiceImpl.getEventById(eventid);  
	}  
	
	//creating post mapping that post the event detail in the database  
	ObjectMapper objectMapper = new ObjectMapper();

	
	
	//creating post mapping that post the event detail in the database  
	@PreAuthorize("hasAuthority('Admin')")
		@PostMapping(value="/event/add-event", consumes = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.MULTIPART_FORM_DATA_VALUE
		})  
		public Event addEvent(@RequestPart("evJson")String evJson,@RequestPart("image") MultipartFile file) throws IOException   
		{  Event ev  = new Event();
		 
			String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
			String uploadDir = "uploads/";
			System.out.println("image name ="+fileNameRepo);
			try {
				ev= objectMapper.readValue(evJson, Event.class);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
						.path(fileNameRepo).toUriString();
				System.out.println("file url =====>"+fileDownloadUri);
				
				ev.setImage(fileDownloadUri.getBytes());
				FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			
			
			eventServiceImpl.addEvent(ev);  
			return ev;  
		}
	
	//creating put mapping that updates the event detail   
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/event/update-event/{eventid}")  
	public ResponseEntity<String> updateEvent(@RequestBody Event events, @PathVariable("eventid")int eventid)   
	{  
	
		eventServiceImpl.updateEvent(events,eventid);  
	    return new ResponseEntity<String>("Event updated successfully",HttpStatus.OK);
	}
	
	//creating put mapping that updates the event detail  
	
	@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/event/affecter-event/{iduser}/{idevent}")  
	public  String participateToEvent(@PathVariable("iduser")int iduser,@PathVariable("idevent")int idevent)   
		{  
		
			String result = participationServiceImpl.addParticipation(iduser, idevent);
		    return result;
			
		}
	@PreAuthorize("hasAuthority('Admin')")
		@GetMapping("/event/retrieve-Event-ById/{id}")
		public Event getEventById(@PathVariable("id") int id) {
			 Event ev = eventServiceImpl.getEventById(id);
			 
			return ev;
		}
			
	//creating get mapping that getEventBy Name   
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		 Event ev = eventServiceImpl.findEventByName(name);
		 
		return ev;
		}
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/retrieve-Event-ByCategory/{category}")
	public List<Event> getEventByCategory(@PathVariable EventCategory category) {
		 List<Event> ev = eventServiceImpl.filterEvent(category);
		return ev;
		}
	
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/bestEventsByViews")
	public Map<Integer, Integer> bestEventsByViews(){
		return eventServiceImpl.getEventsByViews();
		}
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/displaybestEventsByViews")
	public List<String> displaybestEventsByViews(){
		return eventServiceImpl.displayBestEventsByViews();
		}
	
	//creating put mapping that updates the event detail   
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/event/affecter-category-event/{category}/{idevent}")  
	public String affecterCategoryEvent(@PathVariable("category")String category,@PathVariable("idevent")int idevent)   
	{  
			
				return eventServiceImpl.affecterCategoryEvent(category, idevent);
				
	}
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")
	@GetMapping("/event/upcomingEvent")
	public List<Event> upcomingEvents() {
		List<Event> upevents = eventServiceImpl.upcomeEvents();
		System.out.println("hi="+upevents);
		return upevents;
	}
	
	@PreAuthorize("hasAuthority('Admin')")

	@DeleteMapping("/event/delete-event/{event-id}")
	@ResponseBody
	public ResponseEntity<String> deleteEvent(@PathVariable("event-id") int eventID) {
		
		eventServiceImpl.refundUsers(eventID);//refund contributions & participations prices to its users

		eventServiceImpl.deleteEvent(eventID);

		return new ResponseEntity<String>("Event canceled with upgrading price and refund money to user participate",HttpStatus.ACCEPTED);
	}
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/displayBestEventsByParticipations")
	public List<String> displayBestEventsByParticipations() {
		return eventServiceImpl.displayEventsByParticipants();
	}
	
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/displayEventsByCollAmount")
	public List<String> displayEventsByCollAmount() {
		return eventServiceImpl.displayEventsByCollAmount();
		
	}
	
	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/retrieve-all-Participations")
	public List<Participation> getParticipations(){
		return participationServiceImpl.participationsList();
	}

	@PreAuthorize("hasAuthority('Admin') or  hasAuthority('KindergardenDirector') or hasAuthority('visitor') or hasAuthority('Parent') ")

	@GetMapping("/event/getEventsBetweenTwoDates/{date1}/{date2}")
	public List<String> getEventBetweenTwoDates(@PathVariable("date1")String date1,@PathVariable("date2")String date2) throws java.text.ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date date1Converted = dateFormat.parse(date1);
		Date date2Converted = dateFormat.parse(date2);
		return eventServiceImpl.getEventTwoDatesBeetween(date1Converted,date2Converted);
	}

}