package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.User;
public interface ICommentService {
	
	public String addComment(Comment c, int idP) throws Exception;
	public String deleteComment(int id) throws Exception;
	public String updateComment(Comment c, int id) throws Exception;
	public List<Comment> getAllComments();
	public Comment getCommentById(int id);
	public int CountComments();
	public List<Comment> getCommentsByUserId(int id);
	public int CountCommentsByUser(int id);
	public List<Comment> getCommentsByPostId(int id);
	public int CountCommentsByPost(int id);
    public List<Comment> searchComments(String text);
	public User currentUser() throws Exception;
	public List<Comment> getMyComments() throws Exception;
	public NotificationSNW addCommentNotif(Comment c);
}