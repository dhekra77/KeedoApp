package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{

	List<Question> findByFeedback(Feedback f);
	@Transactional
	@Modifying
	@Query("delete from Question q where q.feedback.idFeedback=:id ")
	void deleteQuestions(@Param("id") int id);
	

	
	

}
