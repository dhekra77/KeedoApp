package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.ClaimStatus;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.ClaimService;

@RestController
public class ClaimController {
	@Autowired
	ClaimService claimService;
	
	@PreAuthorize("hasAuthority('Parent')" )

	@PostMapping("/Claims/add-claim/{kindergarten}")  
	public String addClaim(@RequestBody Claim claim,@PathVariable("kindergarten") int kindergarten) throws Exception   
	{  
		return claimService.addClaim(claim,kindergarten);  
	}  
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/retrieve-all-claims")
		 @ResponseBody
	
		 public List<Claim> getclaims() {
		 List<Claim> list = claimService.retrieveAllclaims();
		return list;
		}
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-details/{idClaim}")
		 @ResponseBody
		 

		 public Claim getclaim(@PathVariable("idClaim") int idClaim) {

		
			return claimService.getClaimById(idClaim);
		}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

		@DeleteMapping("/claims/delete-claim/{idClaim}")  
		private void deleteClaim(@PathVariable("idClaim") int idClaim)   
		{  
			claimService.deleteClaim(idClaim);  
		}  
		

	@PreAuthorize("hasAuthority('Parent')" )

		@PutMapping("/claims/update-claim/{idClaim}")  
		public String updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")int idClaim) throws Exception   
		{  
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			claimService.updateClaim(claim,idClaim);  
			return "The claim  was successfuly updated by "+((UserDetailsImpl)principal).getUsername();  
		} 
		
	@PreAuthorize("hasAuthority('KindergardenDirector')" )

	@PutMapping("/claims/process-claim/{idClaim}")  
	public String processClaim(@RequestBody Claim claim, @PathVariable("idClaim")int idClaim) throws Exception   
	{  
		return claimService.processClaim(claim, idClaim);
		
		
		
	} 
		
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/count-claims")
		 @ResponseBody
		 public int getnbclaims() {
		
		return claimService.CountClaim();
		}
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-category/{claimcategory}")
		public String getClaimByCategory(@PathVariable ClaimCategory claimcategory) {
if (claimService.isClaimCategoryExists(claimcategory))
			return claimService.getClaimByCategory(claimcategory).toString();
else
	return "We don't found any claim having the category '"+claimcategory+"' .";
	
	}
	

	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-kindergarden/{name}")
		public String getClaimByKindergarden(@PathVariable String name) {
		
		if (claimService.isKindergarden(name)){
			List<Claim> claim = claimService.getClaimByKindergarden(name);
			
			
			if(!claim.isEmpty())
				return claim.toString();
				
			else
			return "No Claim found for this kindergarden";
			
		}
		return "Sorry this kindergarden name not found.";
		
	}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/kindergarden-Claims-number/{name}")
		 @ResponseBody
		public int CountClaimByKindergarden(@PathVariable String name) {
			 
				return claimService.CountClaimByKindergarden(name);
			}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

	@PostMapping("/claims/block-subscription/{name}")
	 @ResponseBody
	public void blockSubscription(@PathVariable String name) {
	
		claimService.blockSubscription(name);
		
		 		}
	
	@PreAuthorize("hasAuthority('Admin')" )

	@PostMapping("/claims/unblock-subscription/{name}")
	 @ResponseBody
	public void unBlockSubscription(@PathVariable String name) {
	
		claimService.unBlockSubscription(name);
		
		 		}
	
	@PreAuthorize("hasAuthority('Admin')" )

	@GetMapping("/claims/kindergarden-skiped-claims/{name}")
	 @ResponseBody
	public int CountSkipedClaimByKindergarden(@PathVariable String name) {
		 
			return claimService.CountSkipedClaimByKindergarden(name);
		}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

	@GetMapping("/claims/kindergarden-processing-claims/{name}")
	 @ResponseBody
	public int CountProcessingClaimByKindergarden(@PathVariable String name) {
		 
			return claimService.CountProcessingClaimByKindergarden(name);
		}
		
}
