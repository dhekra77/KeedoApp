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
@Table(name= "follow")
public class Follow implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idFollow;
	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "following")
	private User following;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "follower")
	private User follower;
	public Follow() {
		super();
	}

	public int getIdFollow() {
		return idFollow;
	}

	public void setIdFollow(int idFollow) {
		this.idFollow = idFollow;
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


	

	

	

	
	
	
	
}
