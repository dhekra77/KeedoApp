package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idUser;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "telNum")
	private String telNum;
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")
	private Date birthdate;
	@Column(name = "address")
	private String address;
	@Column(name = "mail")
	private String mail;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@Column(name = "delegate")
	private boolean delegate;
	@Column(name = "logo")
	private byte[] logo;

	@Column(name = "status")
	private boolean status;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "role")
	private Role role;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Kid> kids;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Post> posts;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Workshop> workshops;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
	private Set<Message> messagesS;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
	private Set<Message> messagesR;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Bus> bus;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "director", fetch = FetchType.EAGER)
	private Set<Consultation> consultations;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", fetch = FetchType.EAGER)
	private Set<Consultation> doctorConsultations;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Chat> chats;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<ChatSuggestion> ChatSuggestions;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userSend", fetch = FetchType.EAGER)
	private Set<NotificationMsg> notifSend;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userReceive", fetch = FetchType.EAGER)
	private Set<NotificationMsg> notifReceive;

	// added by amal
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Report> reports;
	boolean valid;
	@Column(name = "accountLocked", columnDefinition = "boolean default false")
	private boolean accountNonLocked;
	@Column(name = "failedAttempt", columnDefinition = "int default 0")
	private int failedAttempt;
	@Column(name = "lockTime")
	private Date lockTime;
	@Column(name = "resettoken")
	private String resettoken;

	/**** Roua ******/

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Claim> claims;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "follower")
	private Set<Follow> follower;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "following")
	private Set<Follow> following;
	@JsonIgnore
	@OneToOne(mappedBy = "director")
	private Kindergarden kindergarden;

	private boolean isBlocked;
	
	private LocalDate blockDate;
	private LocalDate unBlockDate;
	private boolean isPrivate;

	 /************Start Chadi******/ 
		
		@Column(name="acc_balance")
		private float accBalance;
		
		@OneToMany(cascade = CascadeType.ALL,mappedBy = "users")
		    private List<Meeting> meetings;
		  
			@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
			private List<Notification> notifciations;
			//
			@OneToMany(cascade= CascadeType.ALL, mappedBy= "user")
			private Set<Participation> participations;
			
			@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
			private List<Donation> donations;
			
			@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
			private List<Evaluation>evaluations;	
			

	 /************End Chadi******/

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDelegate() {
		return delegate;
	}

	public void setDelegate(boolean delegate) {
		this.delegate = delegate;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Kid> getKids() {
		return kids;
	}

	public void setKids(Set<Kid> kids) {
		this.kids = kids;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Workshop> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(Set<Workshop> workshops) {
		this.workshops = workshops;
	}

	public Set<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	public Set<Message> getMessagesS() {
		return messagesS;
	}

	public void setMessagesS(Set<Message> messagesS) {
		this.messagesS = messagesS;
	}

	public Set<Message> getMessagesR() {
		return messagesR;
	}

	public void setMessagesR(Set<Message> messagesR) {
		this.messagesR = messagesR;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Bus> getBus() {
		return bus;
	}

	public void setBus(Set<Bus> bus) {
		this.bus = bus;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Set<Consultation> getDoctorConsultations() {
		return doctorConsultations;
	}

	public void setDoctorConsultations(Set<Consultation> doctorConsultations) {
		this.doctorConsultations = doctorConsultations;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}

	public Set<ChatSuggestion> getChatSuggestions() {
		return ChatSuggestions;
	}

	public void setChatSuggestions(Set<ChatSuggestion> chatSuggestions) {
		ChatSuggestions = chatSuggestions;
	}

	public Set<NotificationMsg> getNotifSend() {
		return notifSend;
	}

	public void setNotifSend(Set<NotificationMsg> notifSend) {
		this.notifSend = notifSend;
	}

	public Set<NotificationMsg> getNotifReceive() {
		return notifReceive;
	}

	public void setNotifReceive(Set<NotificationMsg> notifReceive) {
		this.notifReceive = notifReceive;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getResettoken() {
		return resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

	/***** Roua ********/
	public Set<Follow> getFollower() {
		return follower;
	}

	public void setFollower(Set<Follow> follower) {
		this.follower = follower;
	}

	public Set<Follow> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Follow> following) {
		this.following = following;
	}

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public LocalDate getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(LocalDate blockDate) {
		this.blockDate = blockDate;
	}

	public LocalDate getUnBlockDate() {
		return unBlockDate;
	}

	public void setUnBlockDate(LocalDate unBlockDate) {
		this.unBlockDate = unBlockDate;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public float getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(float accBalance) {
		this.accBalance = accBalance;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meeting) {
		this.meetings = meeting;
	}

	public List<Notification> getNotifciations() {
		return notifciations;
	}

	public void setNotifciations(List<Notification> notifciations) {
		this.notifciations = notifciations;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", telNum=" + telNum
				+ ", birthdate=" + birthdate + ", address=" + address + ", mail=" + mail + ", login=" + login
				+ ", password=" + password + ", delegate=" + delegate + ", logo=" + Arrays.toString(logo) + ", status="
				+ status + ", role=" + role + ", kids=" + kids + ", posts=" + posts + ", workshops=" + workshops
				+ ", messagesS=" + messagesS + ", messagesR=" + messagesR + ", bus=" + bus + ", consultations="
				+ consultations + ", doctorConsultations=" + doctorConsultations + ", chats=" + chats
				+ ", ChatSuggestions=" + ChatSuggestions + ", notifSend=" + notifSend + ", notifReceive=" + notifReceive
				+ ", reports=" + reports + ", valid=" + valid + ", accountNonLocked=" + accountNonLocked
				+ ", failedAttempt=" + failedAttempt + ", lockTime=" + lockTime + ", resettoken=" + resettoken
				+ ", claims=" + claims + ", follower=" + follower + ", following=" + following + ", kindergarden="
				+ kindergarden + ", isBlocked=" + isBlocked + ", isPrivate=" + isPrivate + ", accBalance=" + accBalance
				+ ", meetings=" + meetings + ", notifciations=" + notifciations + ", participations=" + participations
				+ ", donations=" + donations + "]";
	}

	/************************/

}