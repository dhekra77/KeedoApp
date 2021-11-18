package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "topic")
public class Topic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int idTopic;
	@Column(nullable = false, length = 32)
	private String title;
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	@OneToMany(cascade= CascadeType.ALL, mappedBy= "topic", fetch= FetchType.EAGER)
	private Set<Answer> answers;
	
	public Topic() {
		super();
	}

	public int getIdTopic() {
		return idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Topic [idTopic=" + idTopic + ", title=" + title + ", createdDate=" + createdDate + ", user=" + user
				+ ", answers=" + answers + "]";
	}
	
}
