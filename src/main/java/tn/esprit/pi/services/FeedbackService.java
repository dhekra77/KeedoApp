package tn.esprit.pi.services;
 
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;


import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.Question;
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.IMeetingRepository;
//import tn.esprit.pi.repositories.MeetingRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.QuestionRepository;
import tn.esprit.pi.repositories.ResponseRepository;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class FeedbackService implements IFeedbackService{

	
	@Autowired
	FeedbackRepository feedbackrepository;

	@Autowired
	IMeetingRepository meetingrepository;
	
	@Autowired
	IUserRepository userrepository;
	
	@Autowired
	QuestionRepository qr;
	@Autowired
	ResponseRepository rr;
	
	  private final static String ACCOUNT_SID = "ACc623886a49c089d9c967ad2c084e03b3";
	   private final static String AUTH_ID = "dc0032ee96c7dfb15e90930606627313";

	 
	
	
	@Override
	public Feedback createFeedback(Feedback fb, int meeting) {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
		
		
		
		Meeting m=meetingrepository.findMeeting(meeting);
		System.out.println(" m = "+m.getUsers());
		
	  
		User u=userrepository.findDirector(RoleType.Parent, m.getIdMeeting());
	
			
		
		
		fb.setMeeting(m);
		fb.setCreatedAt(LocalDateTime.now());
		Message.creator(new PhoneNumber(u.getTelNum()), new PhoneNumber("+14435012866"),
				   "Please check our application to give you feedback according to the meeting passed on "+m.getStartDate()+" at "+m.getTime()).create();
		
		return feedbackrepository.save(fb);
		
		
	}

	@Override
	public List<Feedback> getAllFeedbacks() {
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.findAll();

		return feedbacks;
	}

	@Override
	public void removeFeedback(int id) {
		// TODO Auto-generated method stub
		
		Feedback f=feedbackrepository.findById(id).get();
		
	
	

		
	qr.deleteQuestions(id);
				feedbackrepository.deleteFeedback(f.getIdFeedback());;
	}
	
	public boolean feedbackexist(int id){
		if (feedbackrepository.feedbackExists(id)==1)
			return true;
		else
			return false;
	}

	@Override
	public Feedback getFeedbackById(int id) {
		// TODO Auto-generated method stub
		return feedbackrepository.findById(id).get();
	}

	@Override
	public int Countfeedbacks() {
		// TODO Auto-generated method stub
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.findAll();

		return feedbacks.size();
	}

	@Override
	public List<Feedback> getFeedbackByMeeting(int meeting) {
		// TODO Auto-generated method stub
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.getmeetingFeedbacks(meeting);
		

		return feedbacks;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback, int id) {
		// TODO Auto-generated method stub
		
		
		Feedback f=feedbackrepository.findById(id).get();

		
		f.setCreatedAt(f.getCreatedAt());
		f.setMeeting(f.getMeeting());
		f.setTitle(feedback.getTitle());
		f.setUpdatedAt(LocalDateTime.now());
		return feedbackrepository.save(f);
	}


	
	
	

}
