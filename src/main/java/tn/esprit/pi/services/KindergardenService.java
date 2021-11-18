package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.IUserRepository;
@Service
public class KindergardenService implements IKindergardenService{

	
	@Autowired 
	KindergardenRepository kindergardenRepository;
	
	@Autowired
	IUserRepository ur;
	
	private static final Logger L=LogManager.getLogger(Kindergarden.class);
	@Override
	public Kindergarden addKindergarden(Kindergarden kindergarden, int director) {
		// TODO Auto-generated method stub
		User u=ur.findById(director).get();
		kindergarden.setDirector(u);
		kindergarden.setCreatedAt(LocalDateTime.now());
		return kindergardenRepository.save(kindergarden);
	

	
	}
	
	public boolean isDirectorHasKindergarden(int director){
		
		if(kindergardenRepository.isDirectorHasKindergarden(director)==0)
			return false;
		else

		return true;
	}

	@Override
	public List<Kindergarden> retrieveAllKindergardens() {
		List <Kindergarden> kindergardens=(List<Kindergarden>) kindergardenRepository.findAll();

		return kindergardens;
	}

	@Override
	public void removeKindergarden(int id) {
		// TODO Auto-generated method stub
		Kindergarden k=kindergardenRepository.findById(id).get();
		kindergardenRepository.delete(k);
	}

	@Override
	public String updateKindergarden(Kindergarden kindergarden, int id) throws Exception {
		// TODO Auto-generated method stub
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Kindergarden k=kindergardenRepository.findById(id).get();
	
		User director=k.getDirector();
		System.out.println("Director= "+director.getFirstName());
		System.out.println("cureent= "+((UserDetailsImpl)principal).getUser().getFirstName());
	
			if (!((UserDetailsImpl)principal).getUser().isBlocked()){
				k.setName(kindergarden.getName());
				k.setCreatedAt(k.getCreatedAt());
				k.setUpdatedAt(LocalDateTime.now());
				k.setDirector(k.getDirector());
				 kindergardenRepository.save(k);  
				 
				 return"The kindergarden account was successfuly updated by  "+((UserDetailsImpl)principal).getUser().getFirstName()+" "+((UserDetailsImpl)principal).getUser().getLastName()+" which is the "
						 +((UserDetailsImpl)principal).getUser().getRole().getRoleType();}else{
						return "Sorry "+((UserDetailsImpl)principal).getUser().getFirstName()+", you don't have the permission to modify the profile of your kindergarten account because your are blocked."
								+ "Please sir contact the administration to relsolve your problems and recover your account.  ";
				 }
				
		}
		


	@Override
	public Kindergarden getKindergardenById(int id) {
		// TODO Auto-generated method stub
		
	
	
		return kindergardenRepository.findById(id).get();
	}

	@Override
	public int countKindergardens() {
		List <Kindergarden> kindergardens=(List<Kindergarden>) kindergardenRepository.findAll();

		return kindergardens.size();
	}

	@Override
	public Kindergarden getKindergardenByName(String name) {
		// TODO Auto-generated method stub
		return kindergardenRepository.findByName(name);
	}

	@Override
	public Kindergarden getKindergardenByDirector(int director) {
		// TODO Auto-generated method stub
		
		User u=ur.findByidUser(director);
		return kindergardenRepository.findByDirector(u);
	}

}
