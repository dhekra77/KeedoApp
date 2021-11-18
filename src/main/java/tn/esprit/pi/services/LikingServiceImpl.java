package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.ILikingRepository;
import tn.esprit.pi.repositories.INotificationSNWRepository;

@Service
public class LikingServiceImpl implements ILikingService {
	@Autowired 
	IPostRepository iPostRepository;
	
	@Autowired 
	ILikingRepository iLikingRepository;
	
	@Autowired 
	INotificationSNWRepository iNotificationSNWRepository;
	
	@Override
	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}
	
	
	@Override
	public String addLiking(int idP) throws Exception{
		int iduser = currentUser().getIdUser();
		Liking l = new Liking();
		if (IsLikeExists(iduser, idP)){
			return ("You already liked this post");
		}
		else{
		Post post=iPostRepository.findById(idP).get();
		l.setUser(currentUser());
		l.setPost(post);
		l.setLikeDate(LocalDateTime.now());
		if (l.getUser().getIdUser()==l.getPost().getUser().getIdUser()){
			iLikingRepository.save(l);
			return ("number of likes on this post: " + CountLikingsByPost(idP));
		}
		else{
		l.setNotificationsnw(addLikeNotif(l));
		iLikingRepository.save(l);
		return ("like notification sent from " +l.getUser().getLogin() +" to " +l.getPost().getUser().getLogin()+ " successfully, number of likes on this post: " + CountLikingsByPost(idP));  						
	}}}
	
	@Override
    public NotificationSNW addLikeNotif (Liking l) {
		NotificationSNW notif = new NotificationSNW();
		notif.setSubject(l.getUser().getLogin()+" liked your post");
		notif.setDate(LocalDateTime.now());
		notif.setSender(l.getUser().getIdUser());
		notif.setReceiver(l.getPost().getUser().getIdUser());
		iNotificationSNWRepository.save(notif);
        return (notif);
    }
	

	@Override
	public String deleteLiking(int id) throws Exception {
		int iduser = currentUser().getIdUser();
		Liking l = iLikingRepository.findById(id).get();
		if (iduser==l.getUser().getIdUser()){
		iLikingRepository.deleteById(id);
		iNotificationSNWRepository.deleteById(l.getNotificationsnw().getIdNotificationsnw());
		return ("Like deleted successfully");
		}
		else{
		return ("You are not allowed to delete this like");	
		}
		
	}

	@Override
	public List<Liking> getAllLikings() {
		List<Liking>Likes = new ArrayList<Liking>();
		iLikingRepository.findAll().forEach(e ->Likes.add(e));
		return Likes;
	}
	

	@Override
	public Liking getLikingById(int id) {
		return iLikingRepository.findById(id).get();  

	}

	@Override
	public int CountLikings() {
		List <Liking> likes=(List<Liking>) iLikingRepository.findAll();
		return likes.size();
	}

	@Override
	public List<Liking> getLikingsByUserId(int id) {
		return iLikingRepository.getLikesByUserId(id);
	}

	@Override
	public int CountLikingsByUser(int id) {
		List <Liking> likes=(List<Liking>) iLikingRepository.getLikesByUserId(id);
		return likes.size();
	}

	@Override
	public List<Liking> getLikingsByPostId(int id) {
		return iLikingRepository.getLikesByPostId(id);

	}
	

	@Override
	public int CountLikingsByPost(int id) {
		List <Liking> likes=(List<Liking>) iLikingRepository.getLikesByPostId(id);
		return likes.size();
	}

	@Override
	public List<Liking> getMyLikes() throws Exception {
		return iLikingRepository.getLikesByUserId(currentUser().getIdUser());
	}
	
	//useful for the add method (a user can't like the same post twice)
	@Override
	public boolean IsLikeExists(int idu, int idp) {
	 int count =iLikingRepository.isLikeExists(idu, idp);
	 if (count==0){
		return false;
	}
	 else {
		 return true;
	 }
	 }



}