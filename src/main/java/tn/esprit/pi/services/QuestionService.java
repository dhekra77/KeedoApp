package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Question;
import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.QuestionRepository;

@Service
public class QuestionService implements IQuestionService{

	
	@Autowired
	QuestionRepository questionrepository;
	@Autowired
	FeedbackRepository feedbackrepository;
	@Override
	public String createQuestion(Question question, int feedback) {
		// TODO Auto-generated method stub
		
		Feedback f=feedbackrepository.findById(feedback).get();
		
		if (f!=null){
			question.setFeedback(f);
			return ""+questionrepository.save(question);
		}
		return "Sorry we  don't have this feedback, you should create it first";
	}

	@Override
	public List<Question> getAllQuestions() {
		List <Question> questions=(List<Question>) questionrepository.findAll();

		return questions;

	}

	@Override
	public void removeQuestion(int id) {
Question question=questionrepository.findById(id).get();
		
		questionrepository.delete(question);

		
	}

	@Override
	public Question getQuestionById(int id) {
		// TODO Auto-generated method stub
		Question question=questionrepository.findById(id).get();

		return question;

	}

	@Override
	public int CountFeedbackQuestions(int feedback) {
		// TODO Auto-generated method stub
		Feedback f=feedbackrepository.findById(feedback).get();

		List <Question> questions=(List<Question>) questionrepository.findByFeedback(f);

		return questions.size();

	}

	@Override
	public List<Question> getQuestionByFeedback(int feedback) {
		// TODO Auto-generated method stub
		
		Feedback f=feedbackrepository.findById(feedback).get();

		List <Question> questions=(List<Question>) questionrepository.findByFeedback(f);

		return questions;

	}

}
