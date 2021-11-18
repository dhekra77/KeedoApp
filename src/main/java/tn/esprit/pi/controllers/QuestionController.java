package tn.esprit.pi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Question;

import tn.esprit.pi.services.QuestionService;
 
@RestController
public class QuestionController {

	@Autowired
	QuestionService questionservice;
	
	@PreAuthorize("hasAuthority('Admin')")

	@PostMapping("/Questions/new-question-of/{feedback}")  
	public String addQuestion(@RequestBody Question question,@PathVariable("feedback") int feedback)   
	{  
		
		question.setCreatedAt(LocalDateTime.now());
		questionservice.createQuestion(question, feedback);
		
		question.getId();
		return "The feedback survey question has been added successfuly ";
				
	} 
	
	
	@PreAuthorize("hasAuthority('Admin')")

	@GetMapping("/retrieve-all-questions")
	 @ResponseBody

	 public List<Question> getquestions() {
		
	
	 List<Question> list = questionservice.getAllQuestions();
	 

	return list;
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")

	@GetMapping("/Questions/retrieve-question-details/{idQuestion}")
	 @ResponseBody
	 

	 public String getQuestion(@PathVariable("idQuestion") int idQuestion) {

	if (questionservice.getQuestionById(idQuestion)!=null)
	 return ""+questionservice.getQuestionById(idQuestion);
	else
		return" Sorry we don't have this question";
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")

	@GetMapping("/Questions/retrieve-feedback-questions/{idFeedback}")
	 @ResponseBody
	 

	 public List<Question> getFeedbackQuestions(@PathVariable("idFeedback") int idFeedback) {

		List<Question> list = questionservice.getQuestionByFeedback(idFeedback);
		return  list;
	}
	
	@PreAuthorize("hasAuthority('Admin')")

	@GetMapping("/count-feedback-questions/{idFeedback}")
	 @ResponseBody
	 public int CountFeedbackQuestions(@PathVariable("idFeedback") int idFeedback) {
	
	return questionservice.CountFeedbackQuestions(idFeedback);
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")

	@DeleteMapping("/Questions/delete-question/{idQuestion}")  
	public String RemoveQuestion(@PathVariable("idQuestion") int idQuestion)   
	{  
		questionservice.removeQuestion(idQuestion);
		
		return "The feedback survey question has been removed successfuly";
	} 
	
	
}
