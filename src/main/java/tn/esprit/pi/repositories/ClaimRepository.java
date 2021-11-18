package tn.esprit.pi.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.ClaimStatus;


@Repository
public interface ClaimRepository extends CrudRepository<Claim,Integer> {

	
	@Query("SELECT c FROM Claim c WHERE c.category =:category")
	public List<Claim> getClaimByCategory(@Param("category")ClaimCategory claimCat);
	
	
	
	@Query("SELECT c FROM Claim c WHERE c.kindergarden.name =:name")
	public List<Claim> getClaimByKindergarden(@Param("name")String k);


	@Query("SELECT c FROM Claim c WHERE c.kindergarden.name =:name and c.status=:status")
	public List<Claim> getClaimByKindergardenAndStatus(@Param("name")String name,@Param("status")ClaimStatus status);
	
	@Query("SELECT count(k) FROM Kindergarden k WHERE k.name =:name")

	public int iskindergarden(@Param("name")String name);


	@Query("SELECT count(c) FROM Claim c WHERE c.category =:claimCategory")

	public int isClaimCategoryExists(@Param("claimCategory")ClaimCategory claimCategory);


	@Query("SELECT count(c) FROM Claim c WHERE c.kindergarden.id=:id")

	public int isKindergardenClaimExists(@Param("id")int idkinder);




}
