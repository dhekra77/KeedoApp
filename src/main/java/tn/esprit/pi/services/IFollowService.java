package tn.esprit.pi.services;



import java.util.List;

import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.User;
 
public interface IFollowService {
	
	 String followUser(int userId) throws Exception;
	 List<FollowRequest> followRequests( ) throws Exception ;
	    boolean isFollowing(int userId ) throws Exception;
	    List<User>  getUserFollowers(int userId);
	    boolean isUserFollowedByCurrentUser(int currentUser, int userId);
	    List<User> getUserFollowing(int userId);
	    void acceptFollow(int followRequestId);
	    void declineFollow(int followRequestId);
		int CountCurrentUserFollows() throws Exception;
		void unfollow(int id);
		int CountUsserRequests() throws Exception;

	    
	    
	


}
