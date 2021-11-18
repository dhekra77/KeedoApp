package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;


@Repository
public interface FeedbackRepository extends CrudRepository<Feedback,Integer>{

	
	
	@Query("SELECT f FROM Feedback f WHERE f.meeting.id =:id")
	public List<Feedback> getmeetingFeedbacks(@Param("id")int id);
	
	@Query("SELECT  f From Feedback f join f.meeting m join m.users u  WHERE u.role.roleType=:role and  f.id=:feedback  ")

	User findDirectorByFeedback(@Param("role") RoleType role,@Param("feedback") int feedback);
	
	
	@Query("SELECT  f From Feedback f join f.meeting m join m.users u  WHERE u.role.roleType=:role  ")

	User findDirector(@Param("role") RoleType role);
	
	@Query("SELECT  count(f) From Feedback f   WHERE f.idFeedback=:id  ")
	public int feedbackExists(@Param("id")int id);
	
	@Transactional
	@Modifying
	@Query("delete from Feedback f where f.idFeedback=:id ")
	void deleteFeedback(@Param("id") int id);


	
}
