package tn.esprit.pi.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.Retour;

public interface INotificationMsgService {
	public NotificationMsg addNotif(int idS, int idR, NotificationMsg notification);

	public List<NotificationMsg> findAllByUserReceive() throws Exception;

	public Retour<NotificationMsg> findByNotificationId(@PathVariable("idU") int id);
}
