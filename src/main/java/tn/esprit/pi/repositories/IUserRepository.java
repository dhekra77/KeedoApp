package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;

import java.util.List;

@Transactional
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findBylogin(String userName);
    List<User> findBylastName(String userName);
	User findByidUser(int idUser);
	
	@Query("SELECT r.roleType FROM User u INNER JOIN Role r on (u.role.id = r.id) where  u.id =:id")
	public String getUserRoleDescription(@Param("id")int id);
	
	@Query("SELECT CONCAT(u.firstName,CONCAT(' ',u.lastName)) FROM User u where  u.valid =TRUE")
	public List<String> getUsersFromActivated();
	
	@Query("SELECT CONCAT(u.firstName,CONCAT(' ',u.lastName)) FROM User u where  u.valid =FALSE")
	public List<String> getUsersFromDisabled();
	
	@Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.login = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String login);
	User findUserByresettoken(String login);
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE %?1% OR u.login LIKE %?1% ")
	List<User> findUserSearch(String pattern);
	
	// YASMIN
	@Query("SELECT u FROM User u where u.idUser= :id ")
	User findForChatById(@Param("id") int id);
	
	@Query("Select u FROM User u where u.role.roleType= :role")
	List<User> findAllByRole(@Param("role") RoleType role);



/******Roua*******/
	
	@Query("SELECT  u From User u join u.meetings m  WHERE u.role.roleType=:role and  m.id=:meeting  ")

	User findDirector(@Param("role") RoleType role,@Param("meeting") int meeting);
	
	
	User findDirectorByKindergarden(Kindergarden  kindergarden);
	
	

	//Chadi
	@Query("Select k FROM Kindergarden k join User u  on k.id =:idkindergarden where u.role.roleType ='KindergardenDirector'  ")
	Kindergarden findDirectorKindergerden(@Param("idkindergarden") int idkindergarden );


	//Chadi
	@Query("Select u FROM User u where u.role.roleType =:role and u.id =:idparent")
	User findParent(@Param("role")RoleType role, @Param("idparent") int idparent );
	
}



