package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "claim")
public class Claim implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idClaim;
	@Column(name= "description")
	private String description;
	@Column(name= "category")
	@Enumerated(EnumType.STRING)
	private ClaimCategory category;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_kindergarden")
	private Kindergarden kindergarden;
	
	@Column(name = "createdAt")
    private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
    private LocalDateTime updatedAt;
	
	
	@Column(name= "status")
	@Enumerated(EnumType.STRING)
	private ClaimStatus status;
	@Column(name= "checkedAt")
	private LocalDateTime checkedAt;
	
	public Claim() {
		super();
	}

	public int getIdClaim() {
		return idClaim;
	}

	public void setIdClaim(int idClaim) {
		this.idClaim = idClaim;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ClaimCategory getCategory() {
		return category;
	}

	public void setCategory(ClaimCategory category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
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

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public LocalDateTime getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(LocalDateTime checkedAt) {
		this.checkedAt = checkedAt;
	}

	@Override
	public String toString() {
		return "Claim [idClaim=" + idClaim + ", description=" + description + ", category=" + category + ", kindergarden=" + kindergarden + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", status=" + status + ", checkedAt=" + checkedAt + "]";
	}


	
	
	
	 
	
	
	
}
