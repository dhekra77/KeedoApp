package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.services.CommentServiceImpl;

@RestController
public class CommentController {
	
		@Autowired  
		CommentServiceImpl commentServiceImpl;
		
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/add-comment/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@PostMapping("/Comment/add-comment/{idP}")  
				public String addComment(@RequestBody Comment comments, @PathVariable("idP")int idP ) throws Exception   
				{  
					return (commentServiceImpl.addComment(comments, idP));  						
				} 
				

		//URL: http://localhost:9293/SpringMVC/servlet/Comment/delete-comment/{Commentid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@DeleteMapping("/Comment/delete-comment/{Commentid}")  
				public void deleteComment(@PathVariable("Commentid") int Commentid) throws Exception   
				{  
					commentServiceImpl.deleteComment(Commentid);  
				} 


		//URL: http://localhost:9293/SpringMVC/servlet/Comment/update-comment/{Commentid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@PutMapping("/Comment/update-comment/{Commentid}")  
				public String updateComment(@RequestBody Comment comments, @PathVariable("Commentid")int Commentid) throws Exception   
				{  
					return commentServiceImpl.updateComment(comments,Commentid);  			  
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/get-all-comments
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Comment/get-all-comments")  
				public List<Comment> getAllComments()   
				{  
					return commentServiceImpl.getAllComments();  
				}  
		
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/get-my-comments
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Comment/get-my-comments")  
			public List<Comment> getMyComments() throws Exception   
			{  
				return commentServiceImpl.getMyComments();  
			}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/detail-comment/{Commentid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Comment/detail-comment/{Commentid}")  
				public Comment getComment(@PathVariable("Commentid") int Commentid)   
				{  
					return commentServiceImpl.getCommentById(Commentid);  
				}  
				
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/comments-by-user/{idU}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Comment/comments-by-user/{idU}")
				public List<Comment> getCommentsByUser(@PathVariable("idU") int idU) {
					return commentServiceImpl.getCommentsByUserId(idU);

				}
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-all-comments
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Comment/count-all-comments")
				public int getcommentscount() {
					return commentServiceImpl.CountComments();
				}
		
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-user-comments/{idU}
		@PreAuthorize("hasAuthority('Admin')")
				@GetMapping("/Comment/count-user-comments/{idU}")
				public int getusercommentscount(@PathVariable("idU") int idU) {
					return commentServiceImpl.CountCommentsByUser(idU);
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/comments-by-post/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Comment/comments-by-post/{idP}")
				public List<Comment> getCommentsByPost(@PathVariable("idP") int idP) {
					return commentServiceImpl.getCommentsByPostId(idP);

				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/count-post-comments/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Comment/count-post-comments/{idP}")
				public int getpostcommentscount(@PathVariable("idP") int idP) {
					return commentServiceImpl.CountCommentsByPost(idP);
				}
				
				
		//URL: http://localhost:9293/SpringMVC/servlet/Comment/search/?pattern=
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
				@GetMapping("/Comment/search/")
				public List<Comment> commentSearch(@RequestParam("pattern")String pattern){
					//System.out.println(pattern);
					return commentServiceImpl.searchComments(pattern);
				
				}
						
				
	}