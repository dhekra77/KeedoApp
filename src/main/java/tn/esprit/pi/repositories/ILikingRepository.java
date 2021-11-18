package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Liking;

@Repository
public interface ILikingRepository  extends CrudRepository<Liking,Integer >{
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Liking l where l.id= :id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT l FROM Liking l WHERE l.user.id =:id order by l.likeDate desc")
	public List<Liking> getLikesByUserId(@Param("id")int id);

	@Query("SELECT l FROM Liking l WHERE l.post.id =:id order by l.likeDate desc")
	public List<Liking> getLikesByPostId(@Param("id")int id);
		
	@Query("select count(l) from Liking l where l.user.id =:idu and l.post.id =:idp")
	public int isLikeExists(@Param("idu")int idu, @Param("idp")int idp);
	
}
