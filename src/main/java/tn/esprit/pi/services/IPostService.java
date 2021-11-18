package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.User;
public interface IPostService {
	
	public String addPost(Post p) throws Exception;
	
	public String deletePost(int id) throws Exception;
	
	public String updatePost(Post p, int id) throws Exception;
	
	public List<Post> getAllPosts();
	
	public Post getPostById(int id);
	
	public int CountPosts();
	
	public List<Post> getPostsByUserId(int id);
	
	public int CountPostsByUser(int id);
    
	public List<Post> searchPosts(String text);
	
	public List<Post> getPostsCommentedByUser(int id);
	
	public List<Post> getPostsLikedByUser(int id);
	
	public String sharePost(int idP) throws Exception;
	
	public String reportPost(int idP) throws Exception;
	
	public boolean isReportExists(int idu, int idp);
	
	public List<Post> getReportedPosts();
	
	public void approveReportedPost(int idP);
	
	public List<String> detect(String photo) throws Exception;
	
	public List<Post> getMyPosts() throws Exception;
	
	public User currentUser() throws Exception;

	public List<Post> getFollowingPosts() throws Exception;

	public Post mostLikedPost() throws Exception;

	public Post mostCommentedPost() throws Exception;

	public List<Post> searchFollowingPosts(String pattern) throws Exception;

	public String detectText(String photo) throws Exception;
}