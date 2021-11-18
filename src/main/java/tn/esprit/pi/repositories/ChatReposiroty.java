package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Chat;

@Repository
public interface ChatReposiroty extends CrudRepository<Chat, Integer> {

	Chat findById(int id);

	@Transactional
	@Modifying
	@Query("DELETE from Chat c where c.id= :idC")
	void deleteChat(@Param("idC") int idC);

	@Query("select new Chat(c.id, c.respense) from Chat c")
	List<Chat> displayAll();

	@Query("select c from Chat c ORDER BY c.nbRequest DESC")
	List<Chat> getChatsByMostRec();
}
