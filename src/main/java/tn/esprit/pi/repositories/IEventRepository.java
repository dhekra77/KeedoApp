package tn.esprit.pi.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.Jackpot;



@Repository
public interface IEventRepository  extends CrudRepository<Event,Integer >{

	
	/***************************FindEventByName*****************
	 * 
	 * @param title
	 * @return Event
	 * 
	 */
	@Query("SELECT ev FROM Event ev WHERE ev.title = :title")
	public Event findEventByName(@Param("title")String title);
	
	
	/***************************FindEventByCategory*****************
	 * 
	 * @param EventCategory
	 * @return Event
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.category =:category")
	public List<Event> filterByCategory(@Param("category")EventCategory eventCategory);
	
	/***************************upcomingEvents*********************
	 * 
	 * @param 
	 * @return List<Events>
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.date >= CURRENT_DATE()")
	public List<Event> upcomingEvents();
	
	
	/***************************passedEvents*********************
	 * 
	 * @param 
	 * @return List<Events>
	 * 
	 */
	
	@Query("SELECT ev FROM Event ev WHERE ev.date <= current_timestamp()")
	public List<Event> passedEvents();
	

	/*******************************UpdateEvent*******************************************/
	@Modifying
	@Transactional
	@Query("UPDATE Event e SET e.title = :title  , e.date = :date , e.hour = :hour , e.address =:address , e.description = :description "
			+ ", e.placesNbr = :numberOfPlaces ,e.category =:eventCategory ,e.ticketPrice = :priceTicket , e.status =:status, "
			+ " e.image = :image WHERE e.id = :id")
	
	public int updateEvent(@Param("title")String title,@Param("date")Date date,@Param("hour")Date hour,@Param("address")String address,
			@Param("description")String description,@Param("numberOfPlaces")int numberOfPlaces,@Param("eventCategory")EventCategory evc,
			@Param("priceTicket")float priceTicket,@Param("status")boolean status,@Param("image")byte[] image,@Param("id")int id	);
	

	@Query("SELECT e.jackpot FROM Event e WHERE e.jackpot =:jackpot")
	public Jackpot findJackpot(@Param("jackpot")Jackpot jackpot);


	@Modifying
	@Transactional
	@Query("UPDATE Event p   SET p.views = :view+1 WHERE p.id =:id ")
	public int updateViewCountEvent(@Param("view")int view,@Param("id")int id);
}
