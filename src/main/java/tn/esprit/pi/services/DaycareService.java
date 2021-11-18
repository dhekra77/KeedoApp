package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Daycare;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.repositories.DaycareRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.KidRepository;

@Service
public class DaycareService implements IDaycareService {

	@Autowired
	IUserRepository userRepository;
	@Autowired
	DaycareRepository daycareRepository;
	@Autowired
	KidRepository kidRepository;
	@Autowired
	KidService kidService;
	
	@Override
	public String addDaycare(Daycare daycare) {
		String respense;
		Retour<Daycare> rt = new Retour<>();
		if (daycare.getDateEnd().before(daycare.getDateBegin())) {
			rt.setStringValue("Can't add daycare because date begin is after the date end");
			respense="1";
		} else {
			// Set periode
			long period = Math
					.round((daycare.getDateEnd().getTime() - daycare.getDateBegin().getTime()) / (double) 86400000);
			daycare.setPeriode(period);
			// Set total price
			if (period < 30) {
				daycare.setPrice_T(daycare.getPrice_M());
			} else if (period > 30 && period < 60) {
				daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.05));
			} else if (period > 60 && period < 90) {
				daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.1));
			} else if (period > 90 && period < 120) {
				daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.15));
			} else {
				daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.15));
			}
			daycareRepository.save(daycare);
			respense="2";
			rt.setStringValue("Daycare added successfully");
			rt.getObjectValue().add(daycare);
		}
		return respense;
	}

	@Override
	public String deleteDaycare(int id) {
		Daycare daycare = daycareRepository.findById(id).get();
		List<Kid> kids= kidRepository.displayKidsByDaycare(daycare);
		for (Kid kid : kids) {
			kidService.deleteKidFromDaycare(kid.getIdKid(), id);
		}
		daycareRepository.deleteDaycareById(id);
		
		return "Daycare deleted successfuly";
	}

	@Override
	public Daycare updateDaycare(int id, Daycare daycare) {
		Daycare d = daycareRepository.findById(id).get();
		d.setCapacity(daycare.getCapacity());
		d.setDateBegin(daycare.getDateBegin());
		d.setDateEnd(daycare.getDateEnd());
		d.setPrice_M(daycare.getPrice_M());
		d.setNbInscrit(daycare.getNbInscrit());
		// Set periode
		long period = Math
				.round((daycare.getDateEnd().getTime() - daycare.getDateBegin().getTime()) / (double) 86400000);
		d.setPeriode(period);
		// Set total price
		if (period < 30) {
			daycare.setPrice_T(daycare.getPrice_M());
		} else if (period > 30 && period < 60) {
			daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.05));
		} else if (period > 60 && period < 90) {
			daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.1));
		} else if (period > 90 && period < 120) {
			daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.15));
		} else {
			daycare.setPrice_T(daycare.getPrice_M() - (daycare.getPrice_M() * 0.15));
		}

		daycareRepository.save(d);
		return d;
	}

	@Override
	public Daycare displayById(int id) {
		Daycare daycare = daycareRepository.findById(id).get();
		long period = Math
				.round((daycare.getDateEnd().getTime() - daycare.getDateBegin().getTime()) / (double) 86400000);
		daycare.setPeriode(period);
		
		return daycare;
	}

	@Override
	public List<Daycare> displayAll() {
		List<Daycare> daycares = (List<Daycare>) daycareRepository.findAll();
		for (Daycare daycare : daycares) {
			long period = Math
					.round((daycare.getDateEnd().getTime() - daycare.getDateBegin().getTime()) / (double) 86400000);
			daycare.setPeriode(period);
		}
		return daycares;
	}

	@Override
	public List<Daycare> displayDaycareSaturated() {
		return daycareRepository.displayDaycareSaturated();
	}

	@Override
	public List<Daycare> displayDaycareNonSaturated() {
		return daycareRepository.displayDaycareNonSaturated();
	}

	@Override
	public List<Daycare> displayDaycareByDate() {
		return daycareRepository.displayDaycareByDate();
	}

	// STATIC
	@Override
	public Double daycareRevenuePerYear(String year) {
		Double total = 0.0;
		List<Daycare> list = daycareRepository.daycareRevenuePerYear(year);

		for (Daycare daycare : list) {
			total += daycare.getNbInscrit() * daycare.getPrice_T();
		}
		return total;
	}
}
