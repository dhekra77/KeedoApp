package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.NotificationSNW;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.INotificationSNWRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;

@Service
public class NotificationSNWServiceImpl implements INotificationSNWService{
	@Autowired 
	INotificationSNWRepository iNotificationSNWRepository;

	
	@Override
	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}
	
    @Override
    public NotificationSNW getNotifById(int id) {

        return iNotificationSNWRepository.findById(id).get();
    }

    @Override
    public List<NotificationSNW> getAllNotif() {
		List<NotificationSNW>Notifs = new ArrayList<NotificationSNW>();
		iNotificationSNWRepository.findAll().forEach(e ->Notifs.add(e));
        return Notifs;

    }

    @Override
    public List<NotificationSNW> getNotifByUser(int id) {

        return iNotificationSNWRepository.getNotifByUser(id);

    }


    @Override
    public void deleteNotif(int id) {

       iNotificationSNWRepository.deleteById(id);
    }
    
    
	@Override
	public List<NotificationSNW> getMyNotifs() throws Exception {
		return iNotificationSNWRepository.getNotifByUser(currentUser().getIdUser());
	}
}

