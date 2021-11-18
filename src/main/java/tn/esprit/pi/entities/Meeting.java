package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="meeting")
public class Meeting implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idMeeting;
	
	private LocalDate startDate;


	private LocalTime time;
    
	private LocalDateTime createdAt;
    
	@Column(name = "canceled_at")
	private LocalDateTime canceledAt;
	
	
	@OneToOne
	@JoinColumn(name = "id_canceler")
	private User canceler;
    @OneToOne(mappedBy = "requested", cascade = {CascadeType.ALL})
    private ExchangeRequest exchangeRequest;
	
	@Column(name = "status")
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	@Column(name= "typeMeeting")
	private String typeMeeting;
	@Column(name= "description")
	private String description;

	
	
	
	
	@JsonIgnore
	@OneToMany(cascade= CascadeType.ALL, mappedBy="meeting", fetch= FetchType.EAGER)
	private Set<Feedback> feedbacks;
	
	//@ManyToMany(cascade= CascadeType.ALL, mappedBy="meetings", fetch= FetchType.EAGER)
	//private Set<User> users;
	@JsonIgnore

	@ManyToOne
    @JoinColumn(name = "id_kindergarden")
    private Kindergarden kindergarden;
	@JsonIgnore

    @ManyToOne
    @JoinColumn(name = "id_parent")
    private User users;

	
	public Meeting() {
		super();
	}


	public int getIdMeeting() {
		return idMeeting;
	}


	public void setIdMeeting(int idMeeting) {
		this.idMeeting = idMeeting;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public LocalDateTime getCanceledAt() {
		return canceledAt;
	}


	public void setCanceledAt(LocalDateTime canceledAt) {
		this.canceledAt = canceledAt;
	}


	public User getCanceler() {
		return canceler;
	}


	public void setCanceler(User canceler) {
		this.canceler = canceler;
	}


	public AppointmentStatus getStatus() {
		return status;
	}


	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}


	public String getTypeMeeting() {
		return typeMeeting;
	}


	public void setTypeMeeting(String typeMeeting) {
		this.typeMeeting = typeMeeting;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}


	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}


	public Kindergarden getKindergarden() {
		return kindergarden;
	}


	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}


	public User getUsers() {
		return users;
	}


	public void setUsers(User users) {
		this.users = users;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", startDate=" + startDate + ", time=" + time + ", createdAt="
				+ createdAt + ", canceledAt=" + canceledAt + ", canceler=" + canceler + ", exchangeRequest="
				+ exchangeRequest + ", status=" + status + ", typeMeeting=" + typeMeeting + ", description="
				+ description + ", feedbacks=" + feedbacks + ", kindergarden=" + kindergarden + ", users=" + users
				+ "]";
	}


	

	
	
}
