package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.NotificationMsgRepository;
import tn.esprit.pi.services.NotificationMsgService;

@RestController
@RequestMapping("notifMsg/")
public class NotificationMsgController {
	@Autowired
	NotificationMsgService notifService;

	@Autowired
	IUserRepository userRep;
	@Autowired
	NotificationMsgRepository notRep;

	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping("add/{idS}/{idR}")
	public NotificationMsg addNotif(@PathVariable("idS") int idS, @PathVariable("idR") int idR,
			@RequestBody NotificationMsg notification) {
		return notifService.addNotif(idS, idR, notification);
	}

	// localhost:8080/SpringMVC/servlet/notifMsg/getAll
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@GetMapping("getAll")
	public List<NotificationMsg> findAllByUserReceive() throws Exception {
		return notifService.findAllByUserReceive();
	}

	// localhost:8080/SpringMVC/servlet/notifMsg/getById/5
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@GetMapping("getById/{idN}")
	public Retour<NotificationMsg> findByNotificationId(@PathVariable("idN") int idN) {
		return notifService.findByNotificationId(idN);

	}

}
