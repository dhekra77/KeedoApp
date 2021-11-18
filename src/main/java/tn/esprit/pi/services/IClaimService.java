package tn.esprit.pi.services;


import java.util.List;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.ClaimStatus;





public interface IClaimService {
	String addClaim(Claim c,int kindergarden) throws Exception;
	List<Claim> retrieveAllclaims();
	void deleteClaim(int id);
	
	Claim updateClaim(Claim c,int id) throws Exception;
	Claim getClaimById(int id);
	int CountClaim();
	List<Claim> getClaimByCategory(ClaimCategory cc);
	List<Claim> getClaimByKindergarden(String k);
	int CountClaimByKindergarden(String k);
	String processClaim(Claim c, int id) throws Exception;
	int CountSkipedClaimByKindergarden(String name);
	int CountProcessingClaimByKindergarden(String name);
	void blockSubscription(String kindergarden);
	void unBlockSubscription(String kindergarden);

}
