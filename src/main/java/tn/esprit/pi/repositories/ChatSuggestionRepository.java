package tn.esprit.pi.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.ChatSuggestion;

@Repository
public interface ChatSuggestionRepository extends CrudRepository<ChatSuggestion, Integer> {

	@Transactional
	@Modifying
	@Query("DELETE from ChatSuggestion c where c.id= :id")
	void deleteChatById(@Param("id") int id);
}
