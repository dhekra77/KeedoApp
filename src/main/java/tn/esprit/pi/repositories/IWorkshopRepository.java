package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;

@Repository
public interface IWorkshopRepository  extends CrudRepository<Workshop,Integer >{
	
	@Query("SELECT w FROM Workshop w WHERE w.content LIKE %?1% OR w.user.login LIKE %?1% order by w.createDate desc")
	public List<Workshop> findWorkshopsByTextContaining(String text);
	
	@Query("SELECT w FROM Workshop w WHERE w.user.id =:id order by w.createDate desc")
	public List<Workshop> getWorkshopByUserId(@Param("id")int id);
	
	@Query("SELECT w FROM Workshop w WHERE w.category =:category order by w.createDate desc")
	public List<Workshop> getByCategory(@Param("category")WorkshopCategory workshopCategory);

	
}