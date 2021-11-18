package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "follow_requests")
public class FollowRequest  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idFollowRequest;
	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "following",nullable = false)
	private User following;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "follower",nullable = false)
	private User follower;
	
	
	

	public FollowRequest() {
		super();
	}

	

	public int getIdFollowRequest() {
		return idFollowRequest;
	}



	public void setIdFollowRequest(int idFollowRequest) {
		this.idFollowRequest = idFollowRequest;
	}



	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}





	
	@Override
	public String toString() {
		return "FollowRequest [idFollowRequest=" + idFollowRequest + ", follower="+ follower + "]";
	}



	
	
	

}
