package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "ChatKeyWord")
@Entity
public class ChatKeyWord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "word")
	private String word;

	@ManyToOne
	@JoinColumn(name = "id_chat")
	private Chat chat;

	public ChatKeyWord() {
		super();
	}

	public ChatKeyWord(int id, String word) {
		super();
		this.id = id;
		this.word = word;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public String toString() {
		return "ChatKeyWord [id=" + id + ", word=" + word + ", chat=" + chat + "]";
	}

}
