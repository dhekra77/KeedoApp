package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.repositories.IMeetingRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackservice;

	@Autowired
	private IMeetingRepository meetingrepository;
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/Feedbacks/new-feedback-of/{meeting}")  
	public String  createFeedback(@RequestBody Feedback fb,@PathVariable("meeting") int meeting)   
	{  
		Meeting m=meetingrepository.findMeeting(meeting);

		if (m!=null){
			feedbackservice.createFeedback(fb,meeting);
			return "The Feedback Of "+fb.getMeeting().getTypeMeeting()+" Meeting Passed On "+fb.getMeeting().getStartDate()+" At :"+fb.getMeeting().getTime()+" was sucessfully Created!";
		
		}
		else{
		return "Sorry we can't find the meeting";
	
		}
		}  
	
	@PreAuthorize("hasAuthority('Admin')" )

	@PutMapping("/feedbacks/update-feedaback/{idFeedback}")  
	public String updateFeedback(@RequestBody Feedback feedback, @PathVariable("idFeedback")int idFeedback) throws Exception   
	{  
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	feedbackservice.updateFeedback(feedback, idFeedback);
		return "The feedback  was successfuly updated by "+((UserDetailsImpl)principal).getUsername();  
	} 
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/retrieve-all-feedbacks")
	 @ResponseBody

	 public String getfeedbacks() {
	 List<Feedback> list = feedbackservice.getAllFeedbacks();
	return "Feedback List : \n"+list;
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/Feedbacks/retrieve-feedback-details/{idFeedback}")
	 @ResponseBody
	 

	 public String getFeedback(@PathVariable("idFeedback") int idFeedback) {

	
		return "Feedback Details: \n"+feedbackservice.getFeedbackById(idFeedback);
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/Feedbacks/delete-feedback/{idFeedback}")  
	 @ResponseBody
	public String RemoveFeedback(@PathVariable("idFeedback") int idFeedback)   
	{  
		
		
		feedbackservice.removeFeedback(idFeedback); 
		if(!feedbackservice.feedbackexist(idFeedback))
		
		return "The Feedback Of Meeting Passed On "+feedbackservice.getFeedbackById(idFeedback).getMeeting().getStartDate()+" At :"+feedbackservice.getFeedbackById(idFeedback).getMeeting().getTime()+" Was successfully Removed!";
		else 
			
			return "not removed";
	
	}  
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/count-feedbacks")
	 @ResponseBody
	 public int getnbfeedbacks() {
	
	return feedbackservice.Countfeedbacks();
	}
	
	


}
