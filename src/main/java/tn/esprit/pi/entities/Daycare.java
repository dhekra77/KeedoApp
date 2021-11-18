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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Daycare implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idDaycare;

	@Column(name = "price_M")
	private Double price_M;

	@Column(name = "price_T")
	private Double price_T;

	@Transient
	private long periode;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateBegin")
	private Date dateBegin;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateEnd")
	private Date dateEnd;

	@Column(name = "capacity")
	private int capacity;

	@Column(name = "nbInscrit")
	private int nbInscrit;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "daycare", fetch = FetchType.EAGER)
	private Set<Kid> kids;

	public Daycare() {
		super();
	}

	public int getIdDaycare() {
		return idDaycare;
	}

	public void setIdDaycare(int idDaycare) {
		this.idDaycare = idDaycare;
	}

	public Double getPrice_M() {
		return price_M;
	}

	public void setPrice_M(Double price_M) {
		this.price_M = price_M;
	}

	public Double getPrice_T() {
		return price_T;
	}

	public void setPrice_T(Double price_T) {
		this.price_T = price_T;
	}

	public long getPeriode() {
		return periode;
	}

	public void setPeriode(long periode) {
		this.periode = periode;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	public int getNbInscrit() {
		return nbInscrit;
	}

	public void setNbInscrit(int nbInscrit) {
		this.nbInscrit = nbInscrit;
	}

	@Override
	public String toString() {
		return "Daycare [idDaycare=" + idDaycare + ", price_M=" + price_M + ", price_T=" + price_T + ", periode="
				+ periode + ", dateBegin=" + dateBegin + ", dateEnd=" + dateEnd + ", capacity=" + capacity
				+ ", nbInscrit=" + nbInscrit + ", kids=" + kids + "]";
	}

}
