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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "response")
public class Response implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;	
	
	@Column(name = "createdAt")
    private LocalDateTime createdAt;
	

	@Column(name= "input")
	private String response;	
	
	@Enumerated(EnumType.STRING)
	private Satistfaction satisfactionDegree;
	
	@Column(name= "rating")
	private int rating;
	

	@JsonIgnore
	
	 @OneToOne
	  
	@JoinColumn(name= "IdQuestion")
	private Question question;


	public Response() {
		super();
	}


	public Response(int id, LocalDateTime createdAt, String response, Satistfaction satisfactionDegree, int rating,
			Question question) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.response = null;
		this.satisfactionDegree = null;
		this.rating = 0;
		this.question = question;
		
		if (question.getType()==QuestionType.Text){
			this.response = response;

		}else if(question.getType()==QuestionType.Satisfaction){
			this.satisfactionDegree = satisfactionDegree;

		}else
		{
			this.rating = rating;

		}
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	public String getResponse() {
		return response;
	}




	public void setResponse(String response) {
		this.response = response;
	}
	

	public Satistfaction getSatisfactionDegree() {
		return satisfactionDegree;
	}


	public void setSatisfactionDegree(Satistfaction satisfactionDegree) {
		this.satisfactionDegree = satisfactionDegree;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Response [id=" + id + ", createdAt=" + createdAt + ", response=" + response + ", satisfactionDegree="
				+ satisfactionDegree + ", rating=" + rating +  "]";
	}


	

 


	
	
	



	
	



	


 



	
	
}
