package tn.esprit.pi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "comment")
public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idComment;
	@Column(name="commentContent")
	private String commentContent;
	@Column(name= "createDate")
	private LocalDateTime createDate;
	@Column(name= "modifyDate")
	private LocalDateTime modifyDate;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name= "id_post")
	private Post post;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	@JsonIgnore
	@OneToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name= "notificationsnw_id")
	private NotificationSNW notificationsnw;
	
	public Comment() {
		super();
	}

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	


	public NotificationSNW getNotificationsnw() {
		return notificationsnw;
	}

	public void setNotificationsnw(NotificationSNW notificationsnw) {
		this.notificationsnw = notificationsnw;
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", commentContent=" + commentContent + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", post=" + post + ", user=" + user + ", notificationsnw="
				+ notificationsnw + "]";
	}





}