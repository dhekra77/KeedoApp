package tn.esprit.pi.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.services.IMeetingService;


@RestController
public class MeetingController {
	@Autowired
	IMeetingService iMeetingService;
	
	
	
	@PostMapping("/meetings/create-meeting/{parentId}/{kindergardenId}")  
	@ResponseBody
	private ResponseEntity<?> addMeeting(@RequestBody Meeting m ,@PathVariable("parentId")int parentId,@PathVariable("kindergardenId")int kindergardenId) throws ParseException   
	{ 
		 return iMeetingService.createNewMeeting(parentId, kindergardenId, m);

	}

			@DeleteMapping("/meetings/delete-meeting/{advId}")  
			private void deleteAdvertisement(@PathVariable("advId") int advId)   
			{  
				iMeetingService.deleteMeeting(advId); 
			}
    //creating put mapping that updates the advertisement detail   
			@PutMapping("/meetings/update-meeting/{idm}")  
			private Meeting updateMeeting(@RequestBody Meeting adv, @PathVariable("idm")int idm)   
			{  
				return iMeetingService.updateMeeting(idm); 
			}
	//creating a get mapping that retrieves all the advertisement detail from the database   
			@GetMapping("/meetings/get-all-meeting")  
			private List<Meeting> getAllMet()   
			{  
				return iMeetingService.getAllMeeting(); 
			}
	//creating a get mapping that retrieves the detail of a specific advertisement 
			@GetMapping("/meeting/detail-meeting/{idm}")  
			private Meeting getMeeting(@PathVariable("idm") int idAd)   
			{  
				return iMeetingService.getMeetingById(idAd);
			} 
	
			@PutMapping("/meeting/updateAppointmentsStatusesWithExpiredExchangeRequest")  
			private List<Meeting> updateAppointmentsStatusesWithExpiredExchangeRequest()   
			{  
				return iMeetingService.updateAppointmentsStatusesWithExpiredExchangeRequest();
			} 
	

}
