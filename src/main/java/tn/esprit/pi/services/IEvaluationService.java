package tn.esprit.pi.services;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface IEvaluationService {

	public String recommendedProducts(int  userId) throws JSONException ;
	public  ResponseEntity<?>evaluateEvent(int idUser ,int idProduct , int rate);
	String updateRating(int iduser, int idproduct, int idRate, int rate);
	int countRatingUserByProduct(int idp);
	
	
}
