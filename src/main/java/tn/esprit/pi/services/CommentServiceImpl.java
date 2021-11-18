package tn.esprit.pi.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.UnhealthyWord;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ICommentRepository;
import tn.esprit.pi.repositories.INotificationSNWRepository;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IUnhealthyWordRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;


@Service
public class CommentServiceImpl implements ICommentService{
	
	@Autowired 
	IPostRepository iPostRepository;
	
	@Autowired 
	ICommentRepository iCommentRepository;
	
	@Autowired 
	IUnhealthyWordRepository iUnhealthyWordRepository;
	
	@Autowired 
	INotificationSNWRepository iNotificationSNWRepository;
	
	@Override
	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}
	
	
	@Override
	public String addComment(Comment c, int idP) throws Exception {
		Post post=iPostRepository.findById(idP).get();
		c.setUser(currentUser());
		c.setPost(post);
		c.setCreateDate(LocalDateTime.now());
		//comment text approval
		for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
			if(c.getCommentContent().toLowerCase().contains(uwd.getWord())){
				return("Sorry, you can't add comments that contain hate speech or bad words on Keedo.");
			}}
		if (c.getUser().getIdUser()==c.getPost().getUser().getIdUser()){
			iCommentRepository.save(c);
			return ("number of comments on this post: " + CountCommentsByPost(idP));
		}
		else{
		c.setNotificationsnw(addCommentNotif(c));
		iCommentRepository.save(c);
		return ("comment notification sent from " +c.getUser().getLogin() +" to " +c.getPost().getUser().getLogin()+ " successfully, number of comments on this post: " + CountCommentsByPost(idP));  						
	}}

	@Override	
	  public NotificationSNW addCommentNotif (Comment c) {
		NotificationSNW notif = new NotificationSNW();
		notif.setSubject(c.getUser().getLogin()+" commented on your post");
		notif.setDate(LocalDateTime.now());
		notif.setSender(c.getUser().getIdUser());
		notif.setReceiver(c.getPost().getUser().getIdUser());
		iNotificationSNWRepository.save(notif);
		return (notif);}
	
	
	@Override
	public String deleteComment(int id) throws Exception {
		int iduser = currentUser().getIdUser();
		Comment c = iCommentRepository.findById(id).get();
		if (iduser==c.getUser().getIdUser()){
			iCommentRepository.deleteById(id);
			iNotificationSNWRepository.deleteById(c.getNotificationsnw().getIdNotificationsnw());
		return ("Comment deleted successfully");
		}
		else{
		return ("You are not allowed to delete this comment");	
		}
	}

	@Override
	public String updateComment(Comment c, int id) throws Exception {
		int iduser = currentUser().getIdUser();
		String roleuser = currentUser().getRole().getRoleType().name();
		Comment comment = iCommentRepository.findById(id).get();
		//only the owner of the comment or the admin can update it
		if (!((iduser==comment.getUser().getIdUser())) && !(roleuser=="Admin")){
			return("You are not allowed to update this comment");}
		else{

		comment.setModifyDate(LocalDateTime.now());
		//comment text approval
		for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
			if(c.getCommentContent().toLowerCase().contains(uwd.getWord())){
				return("Sorry, you can't comment hate speech or bad words on Keedo.");
			}}
		iCommentRepository.save(comment);
		return ("Comment updated successfully");	}	
	}


	@Override
	public List<Comment> getAllComments() {
		List<Comment>Comments = new ArrayList<Comment>();
		iCommentRepository.findAll().forEach(e ->Comments.add(e));
		return Comments;
	}

	
	@Override
	public List<Comment> getMyComments() throws Exception {
		return iCommentRepository.getCommentsByUserId(currentUser().getIdUser());
	}
	
	
	@Override
	public Comment getCommentById(int id) {
		return iCommentRepository.findById(id).get();  
	}


	@Override
	public List<Comment> getCommentsByUserId(int id) {
		return iCommentRepository.getCommentsByUserId(id);

	}

	
	@Override
	public List<Comment> getCommentsByPostId(int id) {
		return iCommentRepository.getCommentsByPostId(id);
	}

	
	@Override
	public int CountComments() {
		List <Comment> comments=(List<Comment>) iCommentRepository.findAll();
		return comments.size();
	}
	

	@Override
	public int CountCommentsByUser(int id) {
		List <Comment> comments=(List<Comment>) iCommentRepository.getCommentsByUserId(id);
		return comments.size();
	}


	@Override
	public int CountCommentsByPost(int id) {
		List <Comment> comments=(List<Comment>) iCommentRepository.getCommentsByPostId(id);
		return comments.size();
	}

	@Override
	public List<Comment> searchComments(String pattern) {
        return iCommentRepository.findCommentsByTextContaining(pattern);
	}





}