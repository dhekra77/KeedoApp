package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.User;

public interface INotificationSNWService {
	
	public NotificationSNW getNotifById(int id);

	public List<NotificationSNW> getAllNotif();

	public List<NotificationSNW> getNotifByUser(int id);

	public void deleteNotif(int id);

	public User currentUser() throws Exception;

	public List<NotificationSNW> getMyNotifs() throws Exception;


}
