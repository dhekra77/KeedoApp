package tn.esprit.pi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonBackReference;

import tn.esprit.pi.entities.Book;
import tn.esprit.pi.repositories.BookRepository;






@RestController
@RequestMapping("/book")
public class BookController {

	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
    private BookRepository bookRepository;
	
	//http://localhost:9293/SpringMVC/servlet/book/listBook
	@JsonBackReference("")
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector') ")
    @RequestMapping(value="/listBook", method= RequestMethod.GET)
    public List<Book> listBook(){
        List<Book> book = bookRepository.findAll();
        logger.info("[REST] List Book");

        return book;
    }
	//http://localhost:9293/SpringMVC/servlet/book/listDisponibilityBook
	@PreAuthorize("hasAuthority('Parent') or hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector') ")
	@RequestMapping(value="/listDisponibilityBook", method= RequestMethod.GET)
    public List<Book> listDisponibilityBook(){
        List<Book> livres = bookRepository.findBooksByStockDisponibleGreaterThanOrderByTitre(0);
        logger.info("[REST] Request the list of available books");

        return livres;
    }

	
	 //http://localhost:9293/SpringMVC/servlet/book/findById/1
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @GetMapping(value="/findById/{id}")
    public Book detailLivre(@PathVariable int id){
        return bookRepository.findById(id);
    }

    //http://localhost:9293/SpringMVC/servlet/book/randomBook
	@PreAuthorize("hasAuthority('Parent')")
    @GetMapping(value="/randomBook")
    public Book randomBookDispo(){
        List<Book> booksDispo = bookRepository.findBooksByStockDisponibleGreaterThanOrderByTitre(0);
        Random rand = new Random();
        Book bookRandom = booksDispo.get(rand.nextInt(booksDispo.size()));
        logger.info("[REST] A random book : " + bookRandom);

        return bookRandom;
    }
	
	
	//http://localhost:9293/SpringMVC/servlet/book/addBook
	@PreAuthorize(" hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @PostMapping(value="/addBook")
    public Book addBook(@RequestBody Book book){
        logger.info("[REST] new book : "+ book);
        bookRepository.save(book);
        return book;
    }
	
//http://localhost:9293/SpringMVC/servlet/book/deleteBook/1
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
    @DeleteMapping(value="/deleteBook/{id}")
    void deleteBook(@PathVariable int id){
        Book livreToDetele = bookRepository.findById(id);
        bookRepository.delete(livreToDetele);
        logger.info("[REST] Remove a book" + livreToDetele.toString());

    }

	 //http://localhost:9293/SpringMVC/servlet/book/static/nbBooks
		@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	    @RequestMapping(value="/static/nbBooks", method= RequestMethod.GET)
	    public Map<String, Integer> nbBooks(){
	        Map<String, Integer> resultat = new HashMap<>();
	        int nbBook = bookRepository.CalculateTotalStock();
	        int nbBookDispo = bookRepository.calculerStockDispo();

	        resultat.put("numberOfBook", nbBook);
	        resultat.put("nbBookDispo", nbBookDispo);
	        logger.info("[REST] Book dispo :" + nbBookDispo + " and total : " + nbBook);

	        return resultat;
	    }
	
}
