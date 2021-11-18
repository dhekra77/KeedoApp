package tn.esprit.pi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import tn.esprit.pi.entities.Book;
import tn.esprit.pi.entities.EmpruntBook;
import tn.esprit.pi.entities.User;
import org.springframework.data.repository.query.Param;


@Repository
public interface EmpruntBookRepository extends JpaRepository<EmpruntBook, Integer>{

	
//	EmpruntBook findById (int id);
	
    @Query("SELECT emprunts FROM EmpruntBook emprunts WHERE emprunts.isRendu = ?1 AND emprunts.finDate < ?2")
    List<EmpruntBook> findEmpruntsExpires(boolean isRendu, Date today);

    //List<EmpruntBook> findEmpruntsByUserFirstName(User user);
    
    @Query("SELECT e FROM EmpruntBook e WHERE e.user.firstName =:firstName")
	public List<EmpruntBook> findEmpruntsByUserFirstName(@Param("firstName")String D);
    
    

    @Query("SELECT e FROM EmpruntBook e WHERE e.user.id =:id ") 
    public List<EmpruntBook> findEmpruntsByUser(@Param("id")int id);

    @Query("SELECT emprunts FROM EmpruntBook emprunts WHERE emprunts.isRendu = ?1 ORDER BY emprunts.book.titre")
    List<EmpruntBook> findEmpruntsEncours(boolean isRendu);

    @Query("Select emprunts from EmpruntBook emprunts Where emprunts.isRendu = false and emprunts.user =?1")
    List<EmpruntBook> trouverEmpruntEncoursParUser(User user);
	
    
   
    @Query("Select empruntBooks from EmpruntBook  empruntBooks Where empruntBooks.isRendu = false and empruntBooks.finDate <?1")
    List<EmpruntBook> listerResaExpiree(Date date);


    @Query("Select empruntBooks from EmpruntBook  empruntBooks Where empruntBooks.isRendu = false and empruntBooks.book =?1 order by  empruntBooks.idEmprunt")
    List<EmpruntBook> trouverResaEncoursParBook(Book book);

    
    
    
    
    @Query("Select empruntBooks from EmpruntBook  empruntBooks Where empruntBooks.isRendu = false and empruntBooks.book =?1 order by  empruntBooks.idEmprunt")
    List<EmpruntBook> trouverResaEncoursByBook(Book book);

    
	
	
}
