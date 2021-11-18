package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FollowRequestRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.services.FollowService;

@RestController

@RequestMapping("Follow")
public class FollowController {

	@Autowired
	FollowService followservice;
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	FollowRequestRepository followRequestRepository;
	
	
	@PreAuthorize("permitAll()" )
	 @PostMapping("/{userId}")
	    public String followUser(@PathVariable(value = "userId") int userId) throws Exception {
		
    User following = userRepository.findById(userId).get();
        
        
		if (followservice.isFollowing(userId)){
			return " You are already followed this "+following.getRole().getRoleType()+".";
		}else
		return followservice.followUser(userId);
	
	        
	    }
	 
	
	@PreAuthorize("permitAll()" )
	    @GetMapping("/isfollowing/{userId}")
	    public boolean isFollowingUser(@PathVariable(value = "userId") int userId) throws Exception {
	        return followservice.isFollowing(userId);
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/{userId}/following")
		   
	    public String getUserFollowing(@PathVariable(value = "userId") int userId) {
	    	
	    	 User user = userRepository.findById(userId).get(); 
	        if(!followservice.getUserFollowing(userId).isEmpty())
	        	return followservice.getUserFollowing(userId).toString();
	        else
	        	return "This "+user.getRole().getRoleType()+" has not following anyone"; 
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/{userId}/followers")
	   
	    public String getUserFollowers(@PathVariable(value = "userId") int userId) {
	    	
	    	 User user = userRepository.findById(userId).get(); 
		        if(!followservice.getUserFollowers(userId).isEmpty())
		        	return followservice.getUserFollowers(userId).toString();
		        else
		        	return "This "+user.getRole().getRoleType()+" has no follower "; 
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @PostMapping("/accept-follow-request/{followId}")
	    public String acceptFollow(@PathVariable(value = "followId") int followRequestId) {
	  
	   
	         followservice.acceptFollow(followRequestId);
	         return "the request was accepted";
	    
	
       
	    }
	    
	    @PreAuthorize("permitAll()" )
	    @PostMapping("/decline-follow-request/{followId}")
	    public String declineFollow(@PathVariable(value = "followId") int followRequestId) {
	         followservice.declineFollow(followRequestId);
	         return "The follow request was successfuly declined";
	    }
	    

	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/follow-requests")
			 @ResponseBody
			 public String getFollowersRequest() throws Exception {
			
	    	List <FollowRequest> requests=followservice.followRequests();
	    	
	    	if (requests.isEmpty())
	    		return "You don't have any request .";
	    	else
			return ""+requests.toString();
			}
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/follow-requests-number")
			 @ResponseBody
			 public int countUserRequest() throws Exception {
			
	    	return followservice.CountUsserRequests();
	    	
	    	
			}
	    
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/ownfollowers")
		 @ResponseBody
		 public int getnbCrrentUserFollows() throws Exception {
		
		return followservice.CountCurrentUserFollows();
		}
	    
	    
	    @PreAuthorize("permitAll()" )
		@DeleteMapping("/unfollow/{idFollow}")  
		private void Unfollow(@PathVariable("idFollow") int idFollow)   
		{  
			followservice.unfollow(idFollow); 
		}  
	    

	
}
