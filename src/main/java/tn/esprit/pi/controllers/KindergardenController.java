package tn.esprit.pi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.ClaimService;
import tn.esprit.pi.services.KindergardenService;

@RestController
@RequestMapping("/Kindergartens")
public class KindergardenController {
	
	@Autowired
	KindergardenService kindergardenService;
	
	@Autowired
	KindergardenRepository kindergardenRepo;
	@Autowired
	ClaimService claimService;

	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/add-kindergarden/{director}")  
	

	public String addKindergarten(@RequestBody Kindergarden kindergarden,@PathVariable("director") int director)   
	{  
		
			if (!kindergardenService.isDirectorHasKindergarden(director)){
				kindergardenService.addKindergarden(kindergarden,director);
				return "This Kindergaten was successfuly added.";
			}
			else{
				return "Sorry, This director has already a kindergarden you cannot add another one.";}
			
	}  
	
	
	
	@PreAuthorize("permitAll()" )
	@GetMapping("/retrieve-all-kindergartens")
	 @ResponseBody

	 public List<Kindergarden> getkindergatens() {
	 List<Kindergarden> list = kindergardenService.retrieveAllKindergardens();
	return list;
	}
	
	
	@PreAuthorize("permitAll()" )

	@GetMapping("/retrieve-kindergarten-details/{idKindergarten}")
	 @ResponseBody
	 

	 public Kindergarden getKindergarten(@PathVariable("idKindergarten") int idKindergarten) {

	
		return kindergardenService.getKindergardenById(idKindergarten);
	}
	
	
	@PreAuthorize("hasAuthority('KindergardenDirector') or  hasAuthority('Admin')" )
	
	@PutMapping("/update-kindergarten/{idKindergarten}")  
	public  String updateKindergarten(@RequestBody  Kindergarden  Kindergarten, @PathVariable("idKindergarten") int idKindergarten) throws Exception    
	{  
	
			 return kindergardenService.updateKindergarden(Kindergarten,idKindergarten);  
		} 
	
	
	@PreAuthorize("hasAuthority('Admin')" )

	@GetMapping("/count-kindergartens")
	 @ResponseBody
	 public int getnbkindergartens() {
	
	return kindergardenService.countKindergardens();
	}
	
	
	@PreAuthorize("permitAll()" )

	@GetMapping("/kindergarden/{name}")
	 @ResponseBody
	public String getKindergartenByName(@PathVariable String name) {
		 
		Kindergarden k=kindergardenService.getKindergardenByName(name);
		
		if (k != null)
			
				
			return k.toString();
		else 
			
			return "Sorry we were unable to find this kindergarden ";
		}
	
	
	@PreAuthorize("permitAll()" )
	@GetMapping("/kindergarden-review/{name}")
	 @ResponseBody
	public String getKindergartenReview(@PathVariable String name) {
		
	
		Kindergarden k=kindergardenService.getKindergardenByName(name);
		
		if(k!=null){
		if ( claimService.CountSkipedClaimByKindergarden(name)<4)
			return k.getName()+" Is the most Recommended Kindergarten .";
		else
			return k.getName()+" Is the Worst Recommended Kindergarten .";
		
		}else
		{
			return "Sorry we were unable to find this kindergarden ";	
			}
		}
	
	
}
