package tn.esprit.pi.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;


public interface IEventService {

	
	public void addEvent(Event e);
	public void deleteEvent(int id);
	public int updateEvent(Event e , int id);
	public List<Event> getAllEvents();
	public Event getEventById(int id);
	
	public Event findEventByName(String name);
	
	public List<Event> filterEvent(EventCategory category);
	
	//public String addParticipation(int iduser,int idevent);
	
	
	public Map<Integer,Integer>getEventsByViews();
	
	public List<String> displayBestEventsByViews() ;
	public String affecterCategoryEvent(String category,int idevent);
    public String affecterEventAdv(int idavert,int idevent);
	public List<Event> upcomeEvents();
	public void refundUsers(int eid) ;
	public void findEventById(int id) ;
	public List<String> displayEventsByParticipants() ;
	public List<String> displayEventsByCollAmount() ;
	public Event findbyId(int id);
	public List<String> getEventTwoDatesBeetween(Date date1, Date date2) ;
	void reintializeJackPotAfterDateEvent(int idevent);

}
