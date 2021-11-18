package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Kindergarden;


public interface IFeedbackService {

	
	Feedback createFeedback(Feedback fb,int meeting);
	
	Feedback updateFeedback(Feedback feedback,int id);

	List<Feedback> getAllFeedbacks();
	void removeFeedback(int id);
	Feedback getFeedbackById(int id);
	int Countfeedbacks();
	
	
	
	List<Feedback> getFeedbackByMeeting(int meeting);

	
}
