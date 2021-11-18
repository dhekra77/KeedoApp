package tn.esprit.pi.controllers;

import java.util.List;

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

import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.services.KidService;

@RestController
@RequestMapping("kid/")
public class KidController {

	@Autowired
	KidService kidService;

	// localhost:8080/SpringMVC/servlet/kid/add
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PostMapping("add")
	public String addKid(@RequestBody Kid kid) throws Exception {
		 return kidService.addKid(kid);
	}

	// localhost:8080/SpringMVC/servlet/kid/del/3
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@DeleteMapping("del/{idK}")
	public void deleteKid(@PathVariable("idK") int id) {
		kidService.deleteKid(id);
	}

	// localhost:8080/SpringMVC/servlet/kid/get/2
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("get/{idK}")
	public Kid displayKidById(@PathVariable("idK") int id) {
		return kidService.displayKid(id);
	}

	// localhost:8080/SpringMVC/servlet/kid/getAll
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("getAll")
	public List<Kid> displayAllKids() {
		return kidService.displayAllKids();
	}

	// localhost:8080/SpringMVC/servlet/kid/up/2
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@PutMapping("up/{idK}")
	public Retour<Kid> updateKid(@PathVariable("idK") int id, @RequestBody Kid kid) {
		return kidService.updateKid(id, kid);
	}

	// localhost:8080/SpringMVC/servlet/kid/Asc
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("Asc")
	public List<Kid> orderKidsByNameAsc() {
		return kidService.orderKidsByNameAsc();
	}

	// localhost:8080/SpringMVC/servlet/kid/Desc
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("Desc")
	public List<Kid> orderKidsByNameDesc() {
		return kidService.orderKidsByNameDesc();
	}

	// localhost:8080/SpringMVC/servlet/kid/daycare/testA/1
		//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
		@GetMapping("daycare/testA/{idK}")
		public String testKidAffected(@PathVariable("idK") int idK) {
			return kidService.testKidAffected(idK);
		}
		
	// localhost:8080/SpringMVC/servlet/kid/daycare/aff/2/1
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("daycare/aff/{idK}/{idD}")
	public String affectKidToDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD) {
		return kidService.affectKidToDaycare(idK, idD);
	}

	// localhost:8080/SpringMVC/servlet/kid/daycare/del/2/1
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("daycare/del/{idK}/{idD}")
	public Retour<Kid> deleteKidFromDaycare(@PathVariable("idK") int idK, @PathVariable("idD") int idD) {
		return kidService.deleteKidFromDaycare(idK, idD);
	}

	// localhost:8080/SpringMVC/servlet/kid/static/nb
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
	@GetMapping("static/nb")
	public int nbrKid() {
		return kidService.nbrKid();
	}
	
	//dhekra
		//localhost:9293/SpringMVC/servlet/kid/saveBusKid/1
		@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager')")
			 @PostMapping("saveBusKid/{idBus}")
				public String AffectBusKid(@RequestBody Kid kid, @PathVariable("idBus")int idBus)   
				{  
					 
					return kidService.affectKidToBus(kid, idBus);
				}
}
