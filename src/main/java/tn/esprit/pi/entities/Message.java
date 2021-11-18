package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idMessage;

	@Column(name = "content")
	private String content;

	@Column(name = "image", nullable = true)
	@Lob
	private byte[] image;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;

	@ManyToOne
	@JoinColumn(name = "id_sender")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "id_receiver")
	private User receiver;

	public Message() {
		super();
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public User getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", content=" + content + ", image=" + Arrays.toString(image)
				+ ", date=" + date + ", time=" + time + ", sender=" + sender + ", receiver=" + receiver + "]";
	}

}
