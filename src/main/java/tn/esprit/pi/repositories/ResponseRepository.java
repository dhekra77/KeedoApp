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
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;


@Repository
public interface ResponseRepository extends JpaRepository<Response,Integer>{

	List<Response> findByQuestion(Question q);
	
	@Query("SELECT  r From Response r join r.question q join q.feedback f Join f.meeting.users u  WHERE u.idUser =:user and u.role.roleType=:role")
	List<Response> UserQuestionsResponses(@Param("user") int user, @Param("role") RoleType role);

	@Query("SELECT  r From Response r join r.question q join q.feedback f Join f.meeting.users u  WHERE u.idUser =:user and q.id=:question and u.role.roleType=:role")

	Response findbyUserAndQuestion(@Param("user") int user,@Param("question") int question, @Param("role") RoleType role);

	
	@Query("SELECT  r From Response r join r.question q join q.feedback f  WHERE f.id =:feedback ")

	List<Response> findbyFeedback(@Param("feedback")int feedback);

	
	@Query("SELECT  r From Response r join r.question q join q.feedback f Join f.meeting.users u  WHERE u.idUser =:user and f.idFeedback=:feedback ")

	List<Response> questionResponses(@Param("user") int user,@Param("feedback") int feedback);
	
	
	@Transactional
	@Modifying
	@Query("delete from Response r where  r.question.id=:id")
	void deleteResponse(@Param("id") int id);
	


	
	
/*
	List<Response> findByFeedback(Feedback f);
	
	@Query("SELECT  r From Response r join r.feedback f join f.meeting.users u  WHERE u.idUser =:user ")
	List<Response> UserResponses(@Param("user") int user);

	@Query("SELECT  r From Response r join r.feedback f join f.meeting.users u  WHERE u.idUser =:user and r.feedback.idFeedback=:feedback")
	Response findbyUserAndFeedback(@Param("user") int user,@Param("feedback") int feedback);

*/
}
