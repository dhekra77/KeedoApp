package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.User;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

	@Query("select m from Message m where m.sender= :sender and m.receiver= :receiver")
	List<Message> getMessages(@Param("sender") User sender, @Param("receiver") User receiver);

	@Transactional
	@Modifying
	@Query("DELETE from Message m where m.idMessage= :id")
	void deleteMessageById(@Param("id") int id);

	@Query("select m from Message m ORDER BY m.time ASC")
	List<Message> getAllOrderByTime();
}
