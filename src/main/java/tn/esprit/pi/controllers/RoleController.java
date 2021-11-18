package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonBackReference;

import tn.esprit.pi.entities.Role;
import tn.esprit.pi.services.IRoleservice;

@RestController
public class RoleController {

	@Autowired
	IRoleservice iroleservice;

	@JsonBackReference("")
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/Role/findall")
	public List<Role> getAllUsers() {
		return iroleservice.getAllRoles();
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/Role/rolebyid/{idRole}")
	public Role getUserById(@PathVariable("idRole") int idRole) throws Exception {
		return iroleservice.findRoleById(idRole);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PostMapping("/Role/createRole")
	@ResponseBody
	public Role createUser(@RequestBody Role user) throws Exception {
		return iroleservice.createRole(user);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PutMapping("/Role/updateRole")
	@ResponseBody
	public Role updateUser(@RequestBody Role role) throws Exception {
		return iroleservice.updateRole(role);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@DeleteMapping("/Role/deleteRoleById/{roleId}")
	public void deleteRoleById(@PathVariable("roleId") int roleId) throws Exception {
		iroleservice.deleteRoleById(roleId);
	}
	
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/Role/getRoleeId/{idRole}")
	public Role getRoleeId(@PathVariable("idRole") int idRole) throws Exception {
		return iroleservice.getRoleeId(idRole);
	}
	
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PutMapping("/Role/updateRolee/{id}")
	@ResponseBody
	public void updateRolee(@RequestBody Role role,@PathVariable("id") int id) throws Exception {
		 iroleservice.updateRolee(role, id);
	}
}