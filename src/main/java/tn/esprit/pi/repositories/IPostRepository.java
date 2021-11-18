package tn.esprit.pi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Post;

@Repository
public interface IPostRepository  extends CrudRepository<Post,Integer >{
	
	@Query("SELECT p FROM Post p WHERE p.postContent LIKE %?1% OR p.user.login LIKE %?1% order by p.createDate desc")
	List<Post> findPostsByTextContaining(String pattern);
	
	@Query("SELECT p FROM Post p WHERE p.user.id =:id order by p.createDate desc") 
	public List<Post> getPostByUserId(@Param("id")int id);
	
	@Query("SELECT p FROM Post p WHERE p.id IN (SELECT c.post.id FROM Comment c WHERE c.user.id =:id) order by p.createDate desc")
	public List<Post> getPostsCommentedByUser(@Param("id")int id);
	
	@Query("SELECT p FROM Post p WHERE p.id IN (SELECT l.post.id FROM Liking l WHERE l.user.id =:id) order by p.createDate desc")
	public List<Post> getPostsLikedByUser(@Param("id")int id);

	
	@Query("SELECT p FROM Post p WHERE p.id IN (SELECT r.post.id FROM Report r) order by p.createDate desc")
	public List<Post> getReportedPosts();
}