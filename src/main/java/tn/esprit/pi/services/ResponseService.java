package tn.esprit.pi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Feedback;

import tn.esprit.pi.entities.Question;
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.IRoleRepository;
//import tn.esprit.pi.repositories.MeetingRepository;
import tn.esprit.pi.repositories.QuestionRepository;
import tn.esprit.pi.repositories.ResponseRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;

@Service
public class ResponseService implements IResponseService{

	@Autowired 
	ResponseRepository responserepository;
	
	@Autowired 
	FeedbackRepository feedbackrepository;
	@Autowired 
	QuestionRepository questionrepository;
	
	/*
	@Autowired 
	MeetingRepository meetingrepository;
	*/
	@Autowired 
	IUserRepository userrepository;
	
	@Autowired
	IRoleRepository roleRepository;
	@Override
	public Response FeedbackResponseQuestion(Response response,int question) {
		// TODO Auto-generated method stub
		
		Question q=questionrepository.findById(question).get();
		response.setQuestion(q);
		return responserepository.save(response);
	}



	@Override
	public void removeResponse(int id) {
		// TODO Auto-generated method stub
		Question q=questionrepository.findById(id).get();
		responserepository.deleteResponse(q.getId());;
	}

	@Override
	public Response getResponseById(int id) {
		// TODO Auto-generated method stub
		return responserepository.findById(id).get();
	}

	@Override
	public List<Response> getResponseByFeedbackQuestion(int question) {
		// TODO Auto-generated method stub
		
		Question q=questionrepository.findById(question).get();
		
		List <Response> responses=(List<Response>) responserepository.findByQuestion(q);

		return responses;
	}
	
	@Override
	public List<Response> getResponseByFeedback(int feedback) {
		// TODO Auto-generated method stub
		
		Feedback f=feedbackrepository.findById(feedback).get();
		//Question q=questionrepository.findByFeedback(f);

		List <Response> responses=(List<Response>) responserepository.findbyFeedback(f.getIdFeedback());

		return responses;
	}

	@Override
	public List<Response> getAllResponses(int meeting) {
		List <Response> responses=(List<Response>) responserepository.findAll();

		return responses;
	}

	@Override
	public List<Response> getAllUserResponses(int user) throws Exception {
		
		User u=userrepository.findById(user).get();
Role role =roleRepository.findRoleByroleType(u.getRole().getRoleType().Parent);
		List <Response> responses=(List<Response>) responserepository.UserQuestionsResponses(u.getIdUser(),role.getRoleType());

		return responses;

	}
	

	@Override
	public Response userResponseByQuestion( int user ,int question) throws Exception {
		
		
		Question q=questionrepository.findById(question).get();
		User u=userrepository.findById(user).get();
		Role role =roleRepository.findRoleByroleType(u.getRole().getRoleType().Parent);

		return responserepository.findbyUserAndQuestion(u.getIdUser(),q.getId(),role.getRoleType());



		
	}


	@Override
	public List<Response> getOwnQuestionResponses(int feedback) throws Exception {
		// TODO Auto-generated method stub
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Feedback f=feedbackrepository.findById(feedback).get();

		List <Response> responses=(List<Response>) responserepository.questionResponses(((UserDetailsImpl) principal).getUser().getIdUser(), f.getIdFeedback());
		return responses;
	}
	
	
	


}
