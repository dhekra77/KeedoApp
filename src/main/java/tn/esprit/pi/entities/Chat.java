package tn.esprit.pi.entities;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Table(name = "chat")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "respense")
	private String respense;

	// @JsonIgnore
	@OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "chat", fetch = FetchType.EAGER)
	@Cascade(CascadeType.REMOVE)
	private List<ChatKeyWord> chatKeyWord;

	@Column(name = "nbRequest")
	private int nbRequest;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	public Chat() {
		super();
	}

	public Chat(int id, String respense, List<ChatKeyWord> chatKeyWord) {
		this.id = id;
		this.respense = respense;
		this.chatKeyWord = chatKeyWord;
	}

	public Chat(int id, String respense) {
		super();
		this.id = id;
		this.respense = respense;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRespense() {
		return respense;
	}

	public void setRespense(String respense) {
		this.respense = respense;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ChatKeyWord> getChatKeyWord() {
		return chatKeyWord;
	}

	public void setChatKeyWord(List<ChatKeyWord> chatKeyWord) {
		this.chatKeyWord = chatKeyWord;
	}

	public int getNbRequest() {
		return nbRequest;
	}

	public void setNbRequest(int nbRequest) {
		this.nbRequest = nbRequest;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", respense=" + respense + ", chatKeyWord=" + chatKeyWord + ", nbRequest=" + nbRequest
				+ ", user=" + user + "]";
	}

}
