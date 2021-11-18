package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "notificationSNW")
public class NotificationSNW implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idNotificationsnw;
	@Column(name= "subject")
	private String subject;
	@Column(name= "Date")
	private LocalDateTime date;
	@Column(name= "sender")
	private int sender;
	@Column(name= "receiver")
	private int receiver;
	@JsonIgnore
	@OneToOne(mappedBy="notificationsnw")
	private Comment comment;
	@JsonIgnore
	@OneToOne(mappedBy="notificationsnw")
	private Liking liking;
	
	public NotificationSNW() {
		super();
	}


	public int getIdNotificationsnw() {
		return idNotificationsnw;
	}


	public void setIdNotificationsnw(int idNotificationsnw) {
		this.idNotificationsnw = idNotificationsnw;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public Liking getLiking() {
		return liking;
	}

	public void setLiking(Liking liking) {
		this.liking = liking;
	}


	@Override
	public String toString() {
		return "NotificationSNW [idNotificationsnw=" + idNotificationsnw + ", subject=" + subject + ", date=" + date
				+ ", sender=" + sender + ", receiver=" + receiver + ", comment=" + comment + ", liking=" + liking + "]";
	}








}