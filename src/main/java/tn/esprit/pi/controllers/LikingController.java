package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.services.LikingServiceImpl;

@RestController
public class LikingController {
	
		@Autowired  
		LikingServiceImpl likingServiceImpl;

		
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/add-like/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@PostMapping("/Liking/add-like/{idP}")  
				public String addLike(@PathVariable("idP")int idP ) throws Exception   
				{    
					return(likingServiceImpl.addLiking(idP));  

				} 
				

		//URL: http://localhost:9293/SpringMVC/servlet/Liking/delete-like/{Likeid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@DeleteMapping("/Liking/delete-like/{Likeid}")  
				public String deleteLiking(@PathVariable("Likeid") int Likeid) throws Exception   
				{  
					return(likingServiceImpl.deleteLiking(Likeid));  
				} 
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/get-all-likes
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Liking/get-all-likes")  
				public List<Liking> getAllLikes()   
				{  
					return likingServiceImpl.getAllLikings();  
				}  
		
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/detail-like/{Likingid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Liking/detail-like/{Likingid}")  
				public Liking getLike(@PathVariable("Likingid") int Likingid)   
				{  
					return likingServiceImpl.getLikingById(Likingid);  
				}  
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-all-likes
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Liking/count-all-likes")
				public int getlikescount() {
					return likingServiceImpl.CountLikings();
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/likes-by-user/{idU}
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Liking/likes-by-user/{idU}")
				public List<Liking> getLikesByUser(@PathVariable("idU") int idU) {
					return likingServiceImpl.getLikingsByUserId(idU);
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-user-likes/{idU}
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Liking/count-user-likes/{idU}")
				public int getuserlikescount(@PathVariable("idU") int idU) {
					return likingServiceImpl.CountLikingsByUser(idU);
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/likes-by-post/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Liking/likes-by-post/{idP}")
				public List<Liking> getLikingsByPost(@PathVariable("idP") int idP) {
					return likingServiceImpl.getLikingsByPostId(idP);
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/count-post-likes/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Liking/count-post-likes/{idP}")
				public int getpostlikescount(@PathVariable("idP") int idP) {
					return likingServiceImpl.CountLikingsByPost(idP);
				}
		//URL: http://localhost:9293/SpringMVC/servlet/Liking/get-my-likes
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Liking/get-my-likes")  
			public List<Liking> getMyComments() throws Exception   
			{  
				return likingServiceImpl.getMyLikes();  
			}
				
}


				

				
				
	