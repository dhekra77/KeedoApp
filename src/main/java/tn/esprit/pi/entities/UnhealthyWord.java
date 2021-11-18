package tn.esprit.pi.entities;
import javax.persistence.*;

@Entity
public class UnhealthyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="word")
    private String word;

    public UnhealthyWord() {
		super();
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

	@Override
	public String toString() {
		return "UnhealthyWord [id=" + id + ", word=" + word + "]";
	}
    
}