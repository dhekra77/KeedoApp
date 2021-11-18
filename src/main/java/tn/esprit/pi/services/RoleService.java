package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IRoleRepository;

@Service
public class RoleService implements IRoleservice {

	@Autowired
	IRoleRepository roleRepository;


	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll() != null ? roleRepository.findAll(): new ArrayList<Role>();
	}

	@Override
	public Role createRole(Role entity) throws Exception {
		return roleRepository.save(entity);
	}

	@Override
	public Role updateRole(Role entity) throws Exception {
		if (entity != null && entity.getIdRole() != 0)
			return roleRepository.save(entity);
		else {
			throw new Exception("No the role record exist"); 
		}
	}

	@Override
	public Role findRoleByroleType(String role) throws Exception {
		return roleRepository.findRoleByroleType(role);
	}


	@Override
	public Role findRoleById(int id) throws Exception {
		return roleRepository.findRoleByidRole(id);
	}
	
	@Override
	public void deleteRoleById(int roleId) throws Exception {
		roleRepository.deleteById(roleId);
	}
	
	@Override
	public Role getRoleeId(int id) throws Exception {
		return roleRepository.getRoleeId(id);
	}

	@Override
	public void updateRolee(Role r, int id) {	
			
			Role role = roleRepository.getRoleeId(id);
			role.setDescription(r.getDescription());
			role.setRoleType(r.getRoleType());
			roleRepository.save(role);
				
			
		}
	
}
