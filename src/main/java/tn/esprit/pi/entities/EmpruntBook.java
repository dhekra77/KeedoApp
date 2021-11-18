	package tn.esprit.pi.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name= "empruntBook")

public class EmpruntBook implements Serializable {
	
	//@EmbeddedId
	//private EmpruntPK empruntPK;

   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id", nullable= false, unique = true)
   private int idEmprunt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_user")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "id_book")
	private Book book;

    @Column(name="debutDate")
    private Date debutDate;

    @Column(name="finDate")
    private Date finDate;
    @Column(name="isProlonge")
    private boolean isProlonge= false ;

    @Column(name="isRendu")
    private boolean isRendu =false;
    @Column(name="detail")
    private String detail;
   

	public EmpruntBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public EmpruntBook(int idEmprunt, User user, Book book, Date debutDate, Date finDate, boolean isProlonge,
			boolean isRendu) {
		super();
		this.idEmprunt = idEmprunt;
		this.user = user;
		this.book = book;
		this.debutDate = debutDate;
		this.finDate = finDate;
		this.isProlonge = false;
		this.isRendu = false;
	}



	/*public EmpruntPK getEmpruntPK() {
		return empruntPK;
	}

	public void setEmpruntPK(EmpruntPK empruntPK) {
		this.empruntPK = empruntPK;
	}
*/
	public int getIdEmprunt() {
		return idEmprunt;
	}

	public void setIdEmprunt(int idEmprunt) {
		this.idEmprunt = idEmprunt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(Date debutDate) {
		this.debutDate = debutDate;
	}

	public Date getFinDate() {
		return finDate;
	}

	public void setFinDate(Date finDate) {
		this.finDate = finDate;
	}

	public boolean isProlonge() {
		return isProlonge;
	}

	public void setProlonge(boolean isProlonge) {
		this.isProlonge = isProlonge;
	}

	public boolean isRendu() {
		return isRendu;
	}

	public void setRendu(boolean isRendu) {
		this.isRendu = isRendu;
	}



	public String getDetail() {
		return detail;
	}



	public void setDetail(String detail) {
		this.detail = detail;
	}





	

	
}
	