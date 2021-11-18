package tn.esprit.pi.services;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.ClaimStatus;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ClaimRepository;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class ClaimService implements IClaimService {

	
	
	@Autowired
	ClaimRepository cr;

	@Autowired
	IUserRepository ur;
	
	
	
	
	@Autowired
	KindergardenRepository kr;
	
	
	 private final static String ACCOUNT_SID = "ACc623886a49c089d9c967ad2c084e03b3";
	   private final static String AUTH_ID = "dc0032ee96c7dfb15e90930606627313";


	
	@Override
	public String addClaim(Claim c , int kindergarden) throws Exception {
		// TODO Auto-generated method stub
		

		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	
			Kindergarden k=kr.findById(kindergarden).get();
			User director=k.getDirector();

			
		if (!director.isBlocked()){
			
		
			c.setUser(((UserDetailsImpl) principal).getUser());
			c.setCreatedAt(LocalDateTime.now());
			c.setKindergarden(k);
			c.setStatus(ClaimStatus.Processing);
			Twilio.init(ACCOUNT_SID, AUTH_ID);
			Message.creator(new PhoneNumber(k.getDirector().getTelNum()), new PhoneNumber("+14435012866"),
			  "Dear Sir "+k.getDirector().getFirstName()+" "+k.getDirector().getLastName()+", we would like"
			  		+ " to bring your attention to the fact that you have a complaint about your daycare"
			  		+ " claimed by mr / mrs "+((UserDetailsImpl) principal).getUser().getFirstName()+""
			  				+ " "+((UserDetailsImpl) principal).getUser().getLastName()+" please handle this"
			  						+ " complaint as soon as possible otherwise your subscription "
			  						+ "will be blocked ").create();
			
			cr.save(c);
				return "Dear "+((UserDetailsImpl) principal).getUser().getFirstName()+" "+((UserDetailsImpl) principal).getUser().getLastName()+", "
						+ "your claim was successfuly noted we will contact the director to relsolve your problem with.";}else{
							return "We are Sorry Dear "+((UserDetailsImpl)principal).getUser().getFirstName()+" "+((UserDetailsImpl)principal).getUser().getLastName()+", this kindergarden is blocked right now so you can't make a claim on it,  ."
									+ "you can claim it later when it becomes unblocked if you want.\n   "
									+ "Thank you sir.";
				}
	}

	@Override
	public List<Claim> retrieveAllclaims() {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.findAll();

		return claims;
	}
	
	@Override
	public String processClaim(Claim c, int id) throws Exception {
		// TODO Auto-generated method stub
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Claim claim=cr.findById(id).get();
		Kindergarden k=kr.findById(claim.getKindergarden().getId()).get();
		
		User director=k.getDirector();
		
		//System.out.println("directtooooooor= "+director.getFirstName());
		if (!director.isBlocked()){
		claim.setCategory(claim.getCategory());
		claim.setDescription(claim.getDescription());
		claim.setCreatedAt(claim.getCreatedAt());
		claim.setUpdatedAt(claim.getUpdatedAt());
		claim.setKindergarden(k);
		claim.setUser(claim.getUser());
		
		claim.setCheckedAt(LocalDateTime.now());
		claim.setStatus(c.getStatus());
		
	
		
		cr.save(claim);
		
		if (claim.getStatus()==ClaimStatus.Resolved){
			deleteClaim( id);
			
			return "Dear "+((UserDetailsImpl) principal).getUser().getFirstName()+" "+((UserDetailsImpl) principal).getUser().getLastName()+", "
			+ "thank you for resolving your kindergarden problem  .";
		}
			
		
		
		
		
		
		return "Dear "+((UserDetailsImpl) principal).getUser().getFirstName()+" "+((UserDetailsImpl) principal).getUser().getLastName()+", "
				+ "your claim process was successfuly recorded .";
		
		
		
		
		}else{
					return "We are Sorry Dear "+((UserDetailsImpl)principal).getUser().getFirstName()+" "+((UserDetailsImpl)principal).getUser().getLastName()+", this kindergarden is blocked right now so you can't make a claim on it,  ."
							+ "you can't treat this claim right now because you are already blocked, please contact the administration to resolve your problem and "
							+ " unlock your subscription with us .\n   "
							+ "Thank you sir.";
		}
		
	}
	

	@Override
	public void deleteClaim(int id) {
		// TODO Auto-generated method stub
		Claim c=cr.findById(id).get();
		cr.delete(c);
	}

	@Override
	public Claim updateClaim(Claim c, int id) throws Exception {

		Claim claim=cr.findById(id).get();
		Kindergarden k=kr.findById(claim.getKindergarden().getId()).get();


		claim.setCategory(c.getCategory());
		claim.setDescription(c.getDescription());
		claim.setCreatedAt(claim.getCreatedAt());
		claim.setUpdatedAt(LocalDateTime.now());
		claim.setKindergarden(k);
		claim.setUser(claim.getUser());
		return cr.save(claim);
		
	}

	@Override
	public Claim getClaimById(int id) {
		// TODO Auto-generated method stub
		return cr.findById(id).get();
	}

	@Override
	public int CountClaim() {
		// TODO Auto-generated method stub
		List <Claim> claims=(List<Claim>) cr.findAll();
		return claims.size();
	}

	@Override
	public List<Claim> getClaimByCategory(ClaimCategory cc) {
		// TODO Auto-generated method stub
	
		
		List<Claim> c=cr.getClaimByCategory(cc);
	
return c;
	}

	

	
	
	
	@Override
	public List<Claim> getClaimByKindergarden(String k) {
		// TODO Auto-generated method stub
		
		
		return cr.getClaimByKindergarden(k);
	}

	
	@Override
	public int CountClaimByKindergarden(String k) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergarden(k);
		return claims.size();
		
	}
	
	
	@Override
	public int CountSkipedClaimByKindergarden(String name) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergardenAndStatus(name,ClaimStatus.Skiped);
		return claims.size();
		
	}
	
	@Override
	public int CountProcessingClaimByKindergarden(String name) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergardenAndStatus(name,ClaimStatus.Processing);
		return claims.size();
		
	}
	

	@Override
	public void blockSubscription(String kindergarden) {
		// TODO Auto-generated method stub
	Kindergarden k=kr.findByName(kindergarden);
	
	
	User director=ur.findByidUser(k.getDirector().getIdUser());
		int nb=CountSkipedClaimByKindergarden( k.getName());
		
		if (nb>=2){
			
			director.setBlocked(true);
			director.setBlockDate(LocalDate.now());
			ur.save(director);
			}


		
	}

	@Override
	public void unBlockSubscription(String kindergarden) {
		// TODO Auto-generated method stub
	Kindergarden k=kr.findByName(kindergarden);
	
	
	User director=ur.findByidUser(k.getDirector().getIdUser());

		System.out.println("kindergadren= +++++" + k.getName());
		System.out.println("director= +++++" + director.getFirstName());

		director.setUnBlockDate(LocalDate.now());

		
			director.setBlocked(false);
			ur.save(director);
			Twilio.init(ACCOUNT_SID, AUTH_ID);
			Message.creator(new PhoneNumber(k.getDirector().getTelNum()), new PhoneNumber("+14435012866"),
			  "Dear Sir "+k.getDirector().getFirstName()+" "+k.getDirector().getLastName()+", Your subscription with us is unblocked now, please respect our conditions to keep your kindergarden on our platform.").create();


		
	}
	
public boolean isClaimCategoryExists(ClaimCategory claimCategory){
		
		if(cr.isClaimCategoryExists(claimCategory)==0)
			return false;
		else

		return true;
	}

public boolean isKindergarden(String kindergarden){
		
		if(cr.iskindergarden(kindergarden)==0)
			return false;
		else

		return true;
	}

public boolean isKindergardenClaimExists(int idkinder){
	
	if(cr.isKindergardenClaimExists(idkinder)==0)
		return false;
	else

	return true;
	
	
}


}
