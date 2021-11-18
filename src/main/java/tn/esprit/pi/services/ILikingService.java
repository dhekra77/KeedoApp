package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Liking;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.User;
public interface ILikingService {
	
	public String deleteLiking(int id) throws Exception;
	
	public List<Liking> getAllLikings();
	
	public Liking getLikingById(int id);
	
	public int CountLikings();
	
	public List<Liking> getLikingsByUserId(int id);
	
	public int CountLikingsByUser(int id);
	
	public List<Liking> getLikingsByPostId(int id);
	
	public int CountLikingsByPost(int id);
	
	public boolean IsLikeExists(int idu, int idp);
	
	public User currentUser() throws Exception;
	
	public NotificationSNW addLikeNotif(Liking l);

	public String addLiking(int idP) throws Exception;

	public List<Liking> getMyLikes() throws Exception;
}