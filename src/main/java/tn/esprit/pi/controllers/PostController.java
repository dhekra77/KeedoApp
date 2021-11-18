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
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.services.FollowService;
import tn.esprit.pi.services.PostServiceImpl;

@RestController
public class PostController {
	
		@Autowired  
		PostServiceImpl postServiceImpl;

		@Autowired  
		FollowService followservice;
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/add-post
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@PostMapping("/Post/add-post")  
			public String addPost(@RequestBody Post Posts) throws Exception   
			{  
				return  (postServiceImpl.addPost(Posts));  
			}  


		//URL: http://localhost:9293/SpringMVC/servlet/Post/update-post/{Postid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@PutMapping("/Post/update-post/{Postid}")  
			public String updatePost(@RequestBody Post posts, @PathVariable("Postid")int Postid) throws Exception   
			{  
				return postServiceImpl.updatePost(posts,Postid);   
			}


		//URL: http://localhost:9293/SpringMVC/servlet/Post/delete-post/{Postid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@DeleteMapping("/Post/delete-post/{Postid}")  
			public String deletePost(@PathVariable("Postid") int Postid) throws Exception   
			{  
				return (postServiceImpl.deletePost(Postid));  
			} 		

		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/get-my-posts
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/get-my-posts")  
			public List<Post> getMyPosts() throws Exception   
			{  
				return postServiceImpl.getMyPosts();  
			}
		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/get-all-posts
		@PreAuthorize("hasAuthority('Admin')")		
			@GetMapping("/Post/get-all-posts")  
			public List<Post> getAllPosts()   
			{  
				return postServiceImpl.getAllPosts();  
			}  
		
			
		//URL: http://localhost:9293/SpringMVC/servlet/Post/get-all-following-posts
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/get-all-following-posts")  
			public List<Post> getAllFollowingPosts() throws Exception
			{  
				return postServiceImpl.getFollowingPosts();  
			}		

		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-by-user/{idU}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/posts-by-user/{idU}")
			public List<Post> getPostsByUser(@PathVariable("idU") int idU) {
				return postServiceImpl.getPostsByUserId(idU);
			}

		//URL: http://localhost:9293/SpringMVC/servlet/Post/detail-post/{Postid}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/detail-post/{Postid}")  
			public Post getPost(@PathVariable("Postid") int Postid)   
			{  
				return postServiceImpl.getPostById(Postid);  
			}  		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/search-by-admin/?pattern=
		@PreAuthorize("hasAuthority('Admin')")
			@GetMapping("/Post/search-by-admin/")
			public List<Post> postSearchByAdmin(@RequestParam("pattern")String pattern){
				return postServiceImpl.searchPosts(pattern);
			}

		//URL: http://localhost:9293/SpringMVC/servlet/Post/search/?pattern=
		@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/search/")
			public List<Post> postSearch(@RequestParam("pattern")String pattern) throws Exception{
				return postServiceImpl.searchFollowingPosts(pattern);
			}
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/share-post/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@PostMapping("/Post/share-post/{idP}")  
			public String sharePost(@PathVariable("idP")int idP) throws Exception   
			{  
				return  postServiceImpl.sharePost(idP);  			
			}  
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/report-post/{idP}
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@PostMapping("/Post/report-post/{idP}")  
			public String reportPost(@PathVariable("idP")int idP) throws Exception   
			{  
				return (postServiceImpl.reportPost(idP));
			}
			
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/reported
		@PreAuthorize("hasAuthority('Admin')")	
			@GetMapping("/Post/reported")
			public List<Post> getReportedPosts() {
				return postServiceImpl.getReportedPosts();
			}

		
//the admin has the choice to approve the reported post (so deletes all the reports on this post) or delete the post using deletePost method 		
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/approve-reported/{Postid}
		@PreAuthorize("hasAuthority('Admin')")	
			@DeleteMapping("/Post/approve-reported/{Postid}")  
			public void approveReportedPost(@PathVariable("Postid") int Postid)   
			{  
				postServiceImpl.approveReportedPost(Postid);  
			}

		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/count-all-posts
		@PreAuthorize("hasAuthority('Admin')")	
			@GetMapping("/Post/count-all-posts")
			public int getpostscount() {
				return postServiceImpl.CountPosts();
			}

		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/count-user-posts/{idU}
		@PreAuthorize("hasAuthority('Admin')")	
			@GetMapping("/Post/count-user-posts/{idU}")
			public int getuserpostscount(@PathVariable("idU") int idU) {
				return postServiceImpl.CountPostsByUser(idU);
			}


		//URL: http://localhost:9293/SpringMVC/servlet/Post/most-liked-post
		@PreAuthorize("hasAuthority('Admin')")	
			@GetMapping("/Post/most-liked-post")
			public Post getMostLikedPost() throws Exception {
				return postServiceImpl.mostLikedPost();
			}
		
		//URL: http://localhost:9293/SpringMVC/servlet/Post/most-commented-post
		@PreAuthorize("hasAuthority('Admin')")	
			@GetMapping("/Post/most-commented-post")
			public Post getMostCommentedPost() throws Exception {
				return postServiceImpl.mostCommentedPost();
			}
		
		
		
		/**********testing Aws moderation******************/ 

		//URL: http://localhost:9293/SpringMVC/servlet/Post/detectimg/?img=
		@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
			@GetMapping("/Post/detectimg/")  
			public List<String> moderation(@RequestParam("img")String img) throws Exception 
			{  
				return postServiceImpl.detect(img);  
			}



/*	
//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-commented-by-user/{idU}
@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/Post/posts-commented-by-user/{idU}")
	public List<Post> getPostsCommentedByUser(@PathVariable("idU") int idU) {
		return postServiceImpl.getPostsCommentedByUser(idU);
	}
	
//URL: http://localhost:9293/SpringMVC/servlet/Post/posts-liked-by-user/{idU}
@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor')  or hasAuthority('Parent') ")
	@GetMapping("/Post/posts-liked-by-user/{idU}")
	public List<Post> getPostsLikedByUser(@PathVariable("idU") int idU) {
		return postServiceImpl.getPostsLikedByUser(idU);
	}*/
	}