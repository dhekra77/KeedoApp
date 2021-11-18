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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Kid")
public class Kid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKid;

	@Column(name = "fistName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDate")
	private Date birthDate;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "id_dacare")
	private Daycare daycare;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kid", fetch = FetchType.EAGER)
	private Set<Consultation> consultations;

	@ManyToOne
	@JoinColumn(name = "id_bus")
	private Bus bus;

	public Kid() {
		super();
	}

	public Kid(int idKid, String firstName, String lastName, Date birthDate, String gender, String address) {
		super();
		this.idKid = idKid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.address = address;
	}

	public int getIdKid() {
		return idKid;
	}

	public void setIdKid(int idKid) {
		this.idKid = idKid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Daycare getDaycare() {
		return daycare;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	public void setDaycare(Daycare daycare) {
		this.daycare = daycare;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Kid [idKid=" + idKid + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", gender=" + gender + ", address=" + address + ", user=" + user + ", daycare=" + daycare
				+ ", consultations=" + consultations + ", bus=" + bus + "]";
	}

}
