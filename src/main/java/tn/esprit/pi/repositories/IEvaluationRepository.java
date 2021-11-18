package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Evaluation;

public interface IEvaluationRepository  extends CrudRepository<Evaluation, Integer>{
	
	@Query("SELECT e FROM Evaluation e WHERE e.event.id=:idEvenement AND e.user.id=:idUser ")
	public List<Evaluation> findEvaluationByUserAndEvent(@Param("idEvenement")int idProd,@Param("idUser")int idUser);

	@Query("SELECT COUNT(e.idEvalution) FROM Evaluation e WHERE e.event.id =:idp")
	int countEvaluation(@Param("idp")int idp);
	@Query("SELECT COUNT(e.idEvalution) FROM Evaluation e WHERE e.event.id =:idp AND e.user.idUser =:idUser")
	int countUserRatingEvent(@Param("idp")int idp,@Param("idUser")int iduser);
}

