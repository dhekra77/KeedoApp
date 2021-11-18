package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "notificationMsg")
public class NotificationMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "content")
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name = "createdAt")
	private Date createdAt;

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;

	@Temporal(TemporalType.TIME)
	@Transient
	private Date timeChecked;

	@Column(name = "isRead")
	private boolean isRead;

	@ManyToOne
	@JoinColumn(name = "userSend")
	private User userSend;

	@ManyToOne
	@JoinColumn(name = "userReceive")
	private User userReceive;

	public NotificationMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTimeChecked() {
		return timeChecked;
	}

	public void setTimeChecked(Date timeChecked) {
		this.timeChecked = timeChecked;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public User getUserSend() {
		return userSend;
	}

	public void setUserSend(User userSend) {
		this.userSend = userSend;
	}

	public User getUserReceive() {
		return userReceive;
	}

	public void setUserReceive(User userReceive) {
		this.userReceive = userReceive;
	}

	@Override
	public String toString() {
		return "NotificationMsg [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", time=" + time
				+ ", timeChecked=" + timeChecked + ", isRead=" + isRead + ", userSend=" + userSend + ", userReceive="
				+ userReceive + "]";
	}

}
