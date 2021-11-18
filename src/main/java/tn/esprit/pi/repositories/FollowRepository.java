package tn.esprit.pi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Follow;
import tn.esprit.pi.entities.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

	/*
	 * @Query("SELECT f FROM Follow f WHERE f.sender.name =:name") public
	 * List<Follow> findFollowbyUsername(@Param("name")String username);
	 * 
	 */

	@Query("SELECT f FROM Follow f WHERE f.following.idUser =:id")
	public List<Follow> findUserFollows(@Param("id") int id);

	@Query("SELECT f FROM Follow f WHERE f.follower.idUser =:follower")

	Follow findFollowByFollower(@Param("follower") int follower);

	@Query("SELECT f FROM Follow f WHERE f.follower.idUser= :follower AND f.following.idUser = :following")

	public Optional<Follow> findFollowByFollowerAndFollowing(@Param("follower") int follower,
			@Param("following") int following);

	@Query("SELECT f FROM Follow f WHERE f.following.idUser =:following")

	public List<Follow> findAllByFollowing(@Param("following") int following);

	@Query("SELECT f FROM Follow f WHERE f.follower.idUser =:follower")

	public List<Follow> findAllByFollower(@Param("follower") int follower);

	// YASMINE

	@Query("select f.following from Follow f where f.follower= :user")
	List<User> listFollowing(@Param("user") User user);

}
