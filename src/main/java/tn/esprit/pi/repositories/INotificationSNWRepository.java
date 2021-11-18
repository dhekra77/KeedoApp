package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.NotificationSNW;

@Repository
public interface INotificationSNWRepository  extends CrudRepository<NotificationSNW,Integer >{	
	
	
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from NotificationSNW n where n.id= :id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT n FROM NotificationSNW n WHERE n.receiver =:id order by n.date desc")
	public List<NotificationSNW> getNotifByUser(@Param("id")int id);
	
	
}