package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;

@Repository
public interface ChatKeyWordRepository extends CrudRepository<ChatKeyWord, Integer> {

	@Query("select new ChatKeyWord(c.id, c.word) from ChatKeyWord c where c.chat= :chat")
	public List<ChatKeyWord> diplayByChatId(@Param("chat") Chat chat);

	@Transactional
	@Modifying
	@Query("DELETE from ChatKeyWord c where c.id= :idK")
	void deleteChatKeyWord(@Param("idK") int idK);
}
