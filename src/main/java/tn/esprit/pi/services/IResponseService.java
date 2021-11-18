package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Response;


public interface IResponseService {


	Response FeedbackResponseQuestion(Response response,int question);
	Response userResponseByQuestion(int user ,int question)throws Exception ;
	List<Response> getAllUserResponses(int user)throws Exception ;

	List<Response> getAllResponses(int meeting);
	void removeResponse(int id);
	Response getResponseById(int id);
	
	List<Response> getResponseByFeedbackQuestion(int question);
	List<Response> getResponseByFeedback(int feedback);
	public List<Response> getOwnQuestionResponses(int question) throws Exception;

	
	
}
