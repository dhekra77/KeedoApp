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
@Table(name= "workshop")
public class Workshop implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int idWorkshop;
	@Column(name="content")
	private String content;
	@Column(name="pdffile")
	private String pdffile;
	@Column(name= "createDate")
	private LocalDateTime createDate;
	@Column(name= "modifyDate")
	private LocalDateTime modifyDate;
	@Enumerated(EnumType.STRING)              
	private WorkshopCategory category;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	
	public Workshop() {
		super();
	}

	public int getIdWorkshop() {
		return idWorkshop;
	}

	public void setIdWorkshop(int idWorkshop) {
		this.idWorkshop = idWorkshop;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPdffile() {
		return pdffile;
	}

	public void setPdffile(String pdffile) {
		this.pdffile = pdffile;
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

	public WorkshopCategory getCategory() {
		return category;
	}

	public void setCategory(WorkshopCategory category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Workshop [idWorkshop=" + idWorkshop + ", content=" + content + ", pdffile=" + pdffile + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", category=" + category + ", user=" + user + "]";
	}

}