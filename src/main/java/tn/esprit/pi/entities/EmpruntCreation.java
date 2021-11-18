package tn.esprit.pi.entities;

import lombok.Data;

@Data
public class EmpruntCreation {
	
    private String userId;
    private String bookId;
    
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

}