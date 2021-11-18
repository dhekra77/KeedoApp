package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ConsultationRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.services.ConsultationService;

@RestController
@RequestMapping("consult/")
public class ConsultationController {

	@Autowired
	ConsultationService consultationService;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ConsultationRepository consultationRepository;

	// localhost:8080/SpringMVC/servlet/consult/add/1/4
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@PostMapping("add/{idK}/{idD}")
	public String affectConsultationToKid(@RequestBody Consultation consultation, @PathVariable("idK") int idK,
			@PathVariable("idD") int idD) throws Exception {
		return consultationService.affectConsultationToKid(consultation, idK, idD);
	}
	
	@PostMapping("addC")
	public void addC(@RequestBody Consultation consultation) throws Exception {
		consultationService.addC(consultation);
	}
	
	// localhost:8080/SpringMVC/servlet/consult/del/2
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@DeleteMapping("del/{id}")
	public void deleteConsultation(@PathVariable int id) {
		 consultationService.deleteConsultation(id);
	}

	// localhost:8080/SpringMVC/servlet/consult/up/2
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('Doctor')")
	@PutMapping("up/{idC}")
	public Consultation updateConsultation(@PathVariable("idC") int idC, @RequestBody Consultation consultation) {
		return consultationService.updateConsultation(idC, consultation);
	}

	// localhost:8080/SpringMVC/servlet/consult/getAll
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("getAll")
	public List<Consultation> displayAllConsultations() {
		return consultationService.displayAllConsultations();
	}
	
	// localhost:8080/SpringMVC/servlet/consult/get/3
	@GetMapping("get/{idC}")
	public Consultation displayConsultationById(@PathVariable("idC") int id) {
		return consultationService.displayById(id);
	}

	// localhost:8080/SpringMVC/servlet/consult/getAll/ToDay
	@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("getAll/ToDay")
	public List<Consultation> displayConsultationsToDay() {
		return consultationService.displayConsultationsToDay();
	}

	// localhost:8080/SpringMVC/servlet/consult/kid/getAll/1
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("kid/getAll/{idK}")
	public List<Consultation> displayConsultationsByKid(@PathVariable("idK") int idK) {
		return consultationService.displayConsultationsByKid(idK);
	}

	// localhost:8080/SpringMVC/servlet/consult/doctor/getAll
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("doctor/getAll/{idD}")
	public List<Consultation> displayConsultationsByDoctor(@PathVariable("idD") int idD) {
		return consultationService.displayConsultationsByDoctor(idD);
	}

	// localhost:8080/SpringMVC/servlet/consult/doctor/getMy
	//@PreAuthorize("hasAuthority('Doctor')")
	@GetMapping("doctor/getMy")
	public List<Consultation> displayMyConsult() throws Exception {
		return consultationService.displayMyConsult();
	}

	// STATIC

	// localhost:8080/SpringMVC/servlet/consult/static/doctor
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("static/doctor")
	public Map<String, Integer> percentageParticipationByDoctor() {
		return consultationService.percentageParticipationByDoctor();
	}

	// localhost:8080/SpringMVC/servlet/consult/static/nbC/12
	//@PreAuthorize("hasAuthority('KindergardenDirector')")
	@GetMapping("static/nbC/{m}")
	public int getPerMonth(@PathVariable("m") String m) {
		List<Consultation> consult = consultationRepository.findByMatchMonthAndMatchDay("-" + m + "-");
		return consult.size();
	}

	@GetMapping("getDoctors")
	public List<User> getDoctors(){
		return consultationService.getDoctors();
	}
	
	@GetMapping("current")
	public User getCurrentUser() throws Exception {
		return consultationService.getCurrentUser();
	}
	
}
