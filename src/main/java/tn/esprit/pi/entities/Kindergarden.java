package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name= "kindergarden")
public class Kindergarden implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;	
	
	@Column(name= "Name")
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
	private Set<Claim> claims;
	
	
	@JsonIgnore
	
	 @OneToOne(cascade = CascadeType.ALL)
	  
	@JoinColumn(name= "IdDirector", referencedColumnName = "id")
	private User director;
  
	
	@Column(name = "createdAt")
    private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
    private LocalDateTime updatedAt;
	
	public Kindergarden() {
		super();
	
	}
	
	public Kindergarden(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Set<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	
	
	public User getDirector() {
		return director;
	}

	public void setDirector(User director) {
		this.director = director;
	}

	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/*@Override
	public String toString() {
		return "Kindergarden [id=" + id + ", name=" + name + ", claims=" + claims + ", director=" + director
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	*/

	 


	
	

	

	
	
	
}
