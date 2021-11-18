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

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.services.DaycareService;

@RestController
@RequestMapping("daycare/")
public class DaycareController {

	@Autowired
	DaycareService daycareService;

	// localhost:8080/SpringMVC/servlet/daycare/add
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@PostMapping("add")
	public String addDaycare(@RequestBody Daycare daycare) {
		return daycareService.addDaycare(daycare);
	}

	// localhost:8080/SpringMVC/servlet/daycare/del/1
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@DeleteMapping("del/{id}")
	public String deleteDaycare(@PathVariable int id) {
		return daycareService.deleteDaycare(id);
	}

	// localhost:8080/SpringMVC/servlet/daycare/update/1
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@PutMapping("update/{id}")
	public Daycare updateDaycare(@PathVariable int id, @RequestBody Daycare daycare) {
		return daycareService.updateDaycare(id, daycare);
	}

	// localhost:8080/SpringMVC/servlet/daycare/get/1
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("get/{id}")
	public Daycare displayById(@PathVariable int id) {
		return daycareService.displayById(id);
	}

	// localhost:8080/SpringMVC/servlet/daycare/getAll
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("getAll")
	public List<Daycare> displayAll() {
		return daycareService.displayAll();
	}

	// localhost:8080/SpringMVC/servlet/daycare/getAll/sat
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("getAll/sat")
	public List<Daycare> displayDaycareSaturated() {
		return daycareService.displayDaycareSaturated();
	}

	// localhost:8080/SpringMVC/servlet/daycare/getAll/Nsat
	@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("getAll/Nsat")
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareService.displayDaycareNonSaturated();
	}

	// localhost:8080/SpringMVC/servlet/daycare/getAll/date
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("getAll/date")
	public List<Daycare> displayDaycareByDate() {
		return daycareService.displayDaycareByDate();
	}

	// STATIC
	// localhost:8080/SpringMVC/servlet/daycare/static/revenue/2021
	//@PreAuthorize("hasAuthority('DaycareManager') or hasAuthority('KindergardenDirector')")
	@GetMapping("static/revenue/{year}")
	public Double daycareRevenuePerYear(@PathVariable("year") String year) {
		return daycareService.daycareRevenuePerYear(year);
	}
}
