package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.User;

@Repository
public interface NotificationMsgRepository extends CrudRepository<NotificationMsg, Integer> {

	@Query("select n from NotificationMsg n where n.userReceive= :user ORDER BY n.createdAt DESC")
	List<NotificationMsg> userNotification(@Param("user") User user);

}
