package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import tn.esprit.pi.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	
	
	   Book findById(int id);

	    List<Book> findBooksByStockDisponibleGreaterThanOrderByTitre(int mini);


	    // requêtes de calculs

	    @Query("Select sum(c.stockTotal) from Book c")
	    int CalculateTotalStock();


	    @Query("Select sum(c.stockDisponible) from Book c")
	    int calculerStockDispo();


}
