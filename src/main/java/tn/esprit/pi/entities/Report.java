package tn.esprit.pi.entities;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "report")

public class Report implements Serializable{
	@EmbeddedId
	private ReportPK reportPK;
	@Column(name= "reportDate")
	private LocalDateTime reportDate;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idPost", referencedColumnName = "id", insertable = false, updatable = false)
	private Post post;
	
	
	public Report() {
		super();
	}
	
	
	public Report(ReportPK reportPK, LocalDateTime reportDate, User user, Post post) {
		super();
		this.reportPK = reportPK;
		this.reportDate = reportDate;
		this.user = user;
		this.post = post;
	}


	public ReportPK getReportPK() {
		return reportPK;
	}
	public void setReportPK(ReportPK reportPK) {
		this.reportPK = reportPK;
	}
	public LocalDateTime getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDateTime reportDate) {
		this.reportDate = reportDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	@Override
	public String toString() {
		return "Report [reportPK=" + reportPK + ", reportDate=" + reportDate + ", user=" + user + ", post=" + post
				+ "]";
	}
	
	
	
	
}