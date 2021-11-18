package tn.esprit.pi.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Book;
import tn.esprit.pi.entities.EmpruntBook;
import tn.esprit.pi.entities.EmpruntCreation;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.BookRepository;
import tn.esprit.pi.repositories.EmpruntBookRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.EmpruntBookService;
import tn.esprit.pi.services.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	private static final Logger logger = LoggerFactory.getLogger(EmpruntController.class);

	@Autowired
	private EmpruntBookRepository empruntBookRepository;
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private EmpruntBookService empruntBookService;
	
	
	

	// http://localhost:9293/SpringMVC/servlet/emprunt/showAllEmprunt
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping(value = "/showAllEmprunt")
	public List<EmpruntBook> listeEmprunts() {
		List<EmpruntBook> emprunts = empruntBookRepository.findAll();
		logger.info("[REST] Request emprunt list");
		return emprunts;
	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/getEmpruntsEncours
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
		@GetMapping(value = "/getEmpruntsEncours")
		public List<EmpruntBook> listEmpruntEncours() {
			List<EmpruntBook> emprunts = empruntBookRepository.findEmpruntsEncours(false);
			logger.info("[REST] Request emprunt list is in cours ");

			return emprunts;
		}

	// http://localhost:9293/SpringMVC/servlet/emprunt/detailsEmprunt/1
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping(value = "/detailsEmprunt/{id}")
	public EmpruntBook detailEMprunt(@PathVariable int id) {

		logger.info("[REST] Details emprunt n° " + id);
		return empruntBookService.findById(id);
		// return empruntBookRepository.findById(id);
	}

	
	  // http://localhost:9293/SpringMVC/servlet/emprunt/findEmpruntsByUser/dhekraParent
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@RequestMapping(value = "/findEmpruntsByUser/{firstName}")
	public List<EmpruntBook> findEmpruntsByUserFirstName(@PathVariable String firstName) {
		List <EmpruntBook> empruntBook = userService.findEmpruntsByUserFirstName(firstName);

		//List<EmpruntBook> listeEmprunts = empruntBookRepository.findEmpruntsByUserFirstName(user);

		//logger.info("[REST]  list emprunt with  user " + user.getMail());

		return empruntBook;
	}
	 
	

	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}

       private final static String ACCOUNT_SID = "AC6c88a113ac98865b4e25b8ba4f939311";
	   private final static String AUTH_ID =  "f12b1d4cfd4876dee2384a315c62d4f2";

	// http://localhost:9293/SpringMVC/servlet/emprunt/creerEmprunt
	@PreAuthorize("hasAuthority('Parent')")
	@PostMapping(value = "/creerEmprunt")
	public String creerEmprunt(@RequestBody EmpruntCreation creationEmprunt) throws Exception {
    Twilio.init(ACCOUNT_SID, AUTH_ID);
		System.out.println("dataJSON = " + creationEmprunt);
	 //  int userId = Integer.parseInt(creationEmprunt.getUserId());
		int bookId = Integer.parseInt(creationEmprunt.getBookId());
		
		
	//	System.out.println("bookId = " + bookId + "userId = " + userId);

		Date dateDebut = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime((dateDebut));
		c.add(Calendar.DATE, 28);
		Date dateFin = c.getTime();
		System.out.println("date début = " + dateDebut + ", date de fin = " + dateFin);
		
		User user = new User();
		//User user = userService.findById(userId);
		Book book = bookRepository.findById(bookId);

		if (book.getStockDisponible() == 0) {
			// le livre n'est pas disponible, on ne peut pas l'emprunter !
			logger.error(
					"[REST] Impossible de créer l'emprunt : User =" + user.getMail() + " Book =" + book.getId());
			// return null;
			return (" Impossible to create emprunt:  User =" + user.getMail() + " Book =" + book.getId());

		}

		else {

			EmpruntBook nouvelEmprunt = new EmpruntBook();

			nouvelEmprunt.setDebutDate(dateDebut);
			nouvelEmprunt.setFinDate(dateFin);
			nouvelEmprunt.setBook(book);
			//nouvelEmprunt.setUser(user);
			nouvelEmprunt.setUser(currentUser());

			empruntBookRepository.save(nouvelEmprunt);

                         empruntBookRepository.save(nouvelEmprunt);
			Message.creator(new PhoneNumber(currentUser().getTelNum()), new PhoneNumber("+14086101434"),
			  "congratulations you have to borrow your choice:  "+
			  "\n #BOOK = " + nouvelEmprunt.getBook().getTitre()).create();

			// l'emprunt est validé, on sauvegarde le livre en diminuant son
			// stock de 1
			book.emprunterBook();
			bookRepository.save(book);
			logger.info(" [REST] Nouvel emprunt créé id = " + nouvelEmprunt.getIdEmprunt() + " user = "
					+ nouvelEmprunt.getUser().getFirstName() + " book = " + nouvelEmprunt.getBook().getTitre());
			// return nouvelEmprunt;
			return ("New emprunt create id = " + nouvelEmprunt.getIdEmprunt() + " user = "
					+ nouvelEmprunt.getUser().getFirstName() + " book = " + nouvelEmprunt.getBook().getTitre());

		}

	}

	// http://localhost:9293/SpringMVC/servlet/emprunt/listeEmpruntsExpires
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@RequestMapping(value = "/listeEmpruntsExpires", method = RequestMethod.GET)
	public List<EmpruntBook> empruntsExpires() {
		Date today = new Date();
		List<EmpruntBook> emprunts = empruntBookRepository.findEmpruntsExpires(false, today);
		logger.info("[REST] Request for the list of expire emprunt");

		return emprunts;
	}


	// http://localhost:9293/SpringMVC/servlet/emprunt/stoppedEmprunt/1

	@PreAuthorize("hasAuthority('Parent')")
	@RequestMapping(value = "/stoppedEmprunt/{id}")
	public Book livreRendu(@PathVariable int id) {

		// on tope l'emprunt à "rendu true" + modif de la date de fin
		EmpruntBook emprunt = empruntBookService.findById(id);
		emprunt.setFinDate(new Date());
		emprunt.setRendu(true);
		empruntBookRepository.save(emprunt);
		logger.info("[REST] Arrêt de l'emprunt " + emprunt);

		// RG un livre est rendu, avant de le rentrer en stock 
		Book livreRendu = bookRepository.findById(emprunt.getBook().getId());

		List<EmpruntBook> listeResaBook = empruntBookRepository.trouverResaEncoursParBook(livreRendu);

		boolean absenceNewOption = true;

		if (absenceNewOption) {

			// on remplit le stock du livre +1
			logger.info("[REST] Rentrée en stock du libre " + livreRendu.getId());
			livreRendu.restituerBook();
			bookRepository.save(livreRendu);
		}
		return livreRendu;
	}

	
	// http://localhost:9293/SpringMVC/servlet/emprunt/prolongEmprunt/2
	@PreAuthorize("hasAuthority('Parent')")
	@RequestMapping(value = "/prolongEmprunt/{id}", method = RequestMethod.GET)
	public EmpruntBook prolongerEmprunt(@PathVariable int id) {

		EmpruntBook empruntAProlonger = empruntBookService.findById(id);
		


		empruntAProlonger.setProlonge(true);
		// ajout de 28 jours à dateFin
		Date dateFin = empruntAProlonger.getFinDate();
		Calendar c = Calendar.getInstance();
		c.setTime(dateFin);
		c.add(Calendar.DATE, 28);
		Date dateFinBis = c.getTime();

		empruntAProlonger.setFinDate(dateFinBis);
		empruntBookRepository.save(empruntAProlonger);
		logger.info("[REST] L'emprunt n° " + empruntAProlonger.getIdEmprunt() + " a bien été prolongé !");
		return empruntAProlonger;

	}

	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	// batch qui va nettoyer les emprnts échues :
	@GetMapping(value = "/cleanEmpruntExpired")
	public List<EmpruntBook> cleanEmpruntExpired(@RequestHeader("Authorization") String token) {
		// récupération de la liste des emprnts qui sont en cours et qui ont
		// une date > date du jour
		//Par exp: nous sommes le 15/12 : toutes les réservations en cours avec pour
		// échéance le 14/12 doivent être passée en "expirées"
		List<EmpruntBook> listeResaPurge = empruntBookRepository.listerResaExpiree(new Date());

		// on parcourt chaque réservation et on l'annule :
		// le livre doit revenir en stock : on contrôle comme une restitution si
		// un autre membre l'a réservé ou pas
		for (EmpruntBook resa : listeResaPurge) {
			resa.setRendu(true);
			resa.setDetail("Expirée");
			Book livreLibere = resa.getBook();
			gererListeAttente(livreLibere);
		}
		logger.info(" ***  EMPRUNT ***  emprunt have been put in expired status #check base # : "
				+ listeResaPurge);
		return listeResaPurge;
	}

	// méthode qui va traiter la liste d'attente d'un livre quand une option est
	// annulée par le batch ou un membre directement
	private void gererListeAttente(Book BookParam) {
		List<EmpruntBook> listeResaLivre = empruntBookRepository.trouverResaEncoursByBook(BookParam);
		// si on trouve une liste de réservations sur le livre on va parcourir
		// cette liste et opérer les traitements adequats
		boolean absenceNewOption = true;

		if (absenceNewOption) {

			// on remplit le stock du livre +1
			BookParam.restituerBook();
			bookRepository.save(BookParam);
			logger.info("[REST]Book must be put back in stock : " + BookParam.getTitre() + " Id :"
					+ BookParam.getId());
		}

	}

}
