package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Question;

public interface IQuestionService {
	String createQuestion(Question fb,int feedback);
	List<Question> getAllQuestions();
	void removeQuestion(int id);
	Question getQuestionById(int id);
	int CountFeedbackQuestions(int feedback);
	
	List<Question> getQuestionByFeedback(int feedback);
}
