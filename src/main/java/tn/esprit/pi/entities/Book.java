package tn.esprit.pi.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name= "book")
public class Book implements Serializable {
	
	

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name="isbn")
	    private String isbn;

	    @Column(name="title")
	    private String titre;

	    @Column(name="firstName_author")
	    private String auteurNom;

	    @Column(name="lastName_author")
	    private String auteurPrenom;

	    @Column(name="collection")
	    private String collection;

	    @Column(name="etiquette")
	    private String etiquette;

	    @Column(name="stock_total")
	    private int stockTotal;

	    @Column(name="stock_disponible")
	    private int stockDisponible;

	
	  // méthodes d'augmentation et de diminution du stock

 public void emprunterBook(){
     this.stockDisponible-=1;
 }

 public void restituerBook(){
     this.stockDisponible+=1;
 }

	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String isbn, String titre, String auteurNom, String auteurPrenom,
			String collection, String etiquette, int stockTotal, int stockDisponible, Set<EmpruntBook> empruntBook) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titre = titre;
		this.auteurNom = auteurNom;
		this.auteurPrenom = auteurPrenom;
		this.collection = collection;
		this.etiquette = etiquette;
		this.stockTotal = stockTotal;
		this.stockDisponible = stockDisponible;
	//	this.empruntBook = empruntBook;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteurNom() {
		return auteurNom;
	}

	public void setAuteurNom(String auteurNom) {
		this.auteurNom = auteurNom;
	}

	public String getAuteurPrenom() {
		return auteurPrenom;
	}

	public void setAuteurPrenom(String auteurPrenom) {
		this.auteurPrenom = auteurPrenom;
	}


	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(String etiquette) {
		this.etiquette = etiquette;
	}

	public int getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	
}