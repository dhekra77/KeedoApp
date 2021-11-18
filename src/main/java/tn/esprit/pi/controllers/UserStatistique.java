package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.IUserservice;

@RestController
@RequestMapping("/User/Stats")
public class UserStatistique {
	@Autowired
	IUserservice iuserservice;
	
	public int CountUser() {
		return iuserservice.getAllUsers().size();
	}
	
	@GetMapping("/ValidUser")
	public int ValidUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.isValid()) {
				valide++;
			}
		}
		return valide;
	}
	
	@GetMapping("/LockedUser")
	public int LockedUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (!user.isAccountNonLocked()) {
				valide++;
			}
		}
		return valide;
	}
	
	@GetMapping("/KindergardenUser")
	public int KindergardenUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.getRole().getRoleType().name().equals(RoleType.KindergardenDirector.toString())) {
				valide++;
			}
		}
		return valide;
	}

	@GetMapping("/DoctorUser")
	public int DoctorUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.getRole().getRoleType().name().equals(RoleType.Doctor.toString())) {
				valide++;
			}
		}
		return valide;
	}

	@GetMapping("/DaycareManagerUser")
	public int DaycareManagerUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.getRole().getRoleType().name().equals(RoleType.DaycareManager.toString())) {
				valide++;
			}
		}
		return valide;
	}

	@GetMapping("/ParentUser")
	public int ParentUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.getRole().getRoleType().name().equals(RoleType.Parent.toString())) {
				valide++;
			}
		}
		return valide;
	}

	@GetMapping("/visitorUser")
	public int visitorUser() {
		List<User> users = iuserservice.getAllUsers();
		int valide = 0;
		for (User user : users) {
			if (user.getRole().getRoleType().name().equals(RoleType.visitor.toString())) {
				valide++;
			}
		}
		return valide;
	}

}
