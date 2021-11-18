package tn.esprit.pi.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.pi.entities.Follow;
import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.User;
 import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.FollowRequestRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;


@Service
public class FollowService implements IFollowService{

	@Autowired
	FollowRepository followRepository;
	
	@Autowired
	FollowRequestRepository followRequestRepository;
	
	
	@Autowired
	IUserRepository userRepository;
	
	
	
	 private final static String ACCOUNT_SID = "ACc623886a49c089d9c967ad2c084e03b3";
	   private final static String AUTH_ID = "dc0032ee96c7dfb15e90930606627313";

	@Override
	public String followUser(int userId) throws Exception {
		
        User following = userRepository.findById(userId).get();
        
        Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	
		
        if (following.isPrivate() ) {
            FollowRequest followRequest = new FollowRequest();

            followRequest.setFollower(((UserDetailsImpl) follower).getUser());
            followRequest.setFollowing(following);

            followRequestRepository.save(followRequest);

            return "Your follow request has been successfuly sent to "+following.getFirstName()+" "+following.getLastName()+". \n "
            		+ "Please wait until he/she accept your request";
        }
        Follow followObject = new Follow();
        followObject.setFollower(((UserDetailsImpl) follower).getUser());
        followObject.setFollowing(following);
        followRepository.save(followObject);

	
        return "You are now the follower of "+following.getFirstName()+" "+following.getLastName()+", so you can now consult"
        		+ " her/his profile and discuss "
        		;
	
	}
	
	
	@Override
	public boolean isFollowing(int userId) throws Exception {
		
		 User following = userRepository.getOne(userId);
	
		 Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

  
	        Optional<Follow> follow = followRepository.findFollowByFollowerAndFollowing(((UserDetailsImpl) follower).getUser().getIdUser(),following.getIdUser());
	        if (follow.isPresent()) {
	            return  true ;
	        }
	        return  false ;
	}
	
	
	
	@Override
	public List<User> getUserFollowers(int userId) {
		
		
		 User user = userRepository.findById(userId).get();

	        List<Follow> followerList = followRepository.findAllByFollowing(user.getIdUser());
	        List<User> userList = new ArrayList<>();
	        for (Follow follow : followerList) {
		 

	            userList.add(userRepository.findById(follow.getFollower().getIdUser()).get());
	   
		
	        }
	        return userList;
	        
	        
		
	
	}
	@Override
	public boolean isUserFollowedByCurrentUser(int currentUser, int userId) {
		// TODO Auto-generated method stub
	     return  false ;
	}
	
	
	
	@Override
	public List<User> getUserFollowing(int userId) {
		
		
		 User user = userRepository.findById(userId).get();

	        List<Follow> followingList = followRepository.findAllByFollower(user.getIdUser());
	        List<User> userList = new ArrayList<>();
	        for (Follow follow : followingList) {
		      

	            userList.add(userRepository.findById(follow.getFollowing().getIdUser()).get());
	                  
	
	        }
			return userList;
	  
	}

	@Override
	public void acceptFollow(int followRequestId) {
		
		 FollowRequest followRequest = followRequestRepository.findById(followRequestId).get();
	
		 User follower=userRepository.findByidUser(followRequest.getFollower().getIdUser());
		 User following=userRepository.findByidUser(followRequest.getFollowing().getIdUser());
		
	      
	            Follow followObject = new Follow();
	            followObject.setFollower(followRequest.getFollower());
	            followObject.setFollowing(followRequest.getFollowing());
	            
	            
	            followRepository.save(followObject);

	            followRequestRepository.delete(followRequest);
	           
	            Twilio.init(ACCOUNT_SID, AUTH_ID);
				Message.creator(new PhoneNumber(follower.getTelNum()), new PhoneNumber("+14435012866"),
						"Hey "+follower.getFirstName()+" "+follower.getLastName()+
	            		", You are now the follower of "+following.getFirstName()+" "+following.getLastName()+", So you have the permission now to consult his/her profile and discuss with ").create();
	    
	        }
	        


	@Override
	public void declineFollow(int followRequestId) {
		 followRequestRepository.deleteById(followRequestId);
	       
	}

	
	@Override
	public int CountCurrentUserFollows() throws Exception {
		// TODO Auto-generated method stub
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List <Follow> follows=(List<Follow>) followRepository.findUserFollows(((UserDetailsImpl) principal).getUser().getIdUser());
		return follows.size();
	}


	public void unfollow(int idFollow) {
		followRepository.deleteById(idFollow);
		
	}


	public List<FollowRequest> followRequests() throws Exception {
		// TODO Auto-generated method stub
		
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				
			List<FollowRequest> requests=followRequestRepository.findAllByFollowing(((UserDetailsImpl) principal).getUser());
		
		
		return requests;
			
	}


	@Override
	public int CountUsserRequests() throws Exception {
		// TODO Auto-generated method stub
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			
			List<FollowRequest> requests=followRequestRepository.findAllByFollowing(((UserDetailsImpl) principal).getUser());
		
		
		return requests.size();
	}



	
	

	
}
