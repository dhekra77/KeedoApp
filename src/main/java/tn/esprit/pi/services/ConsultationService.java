package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ConsultationRepository;
import tn.esprit.pi.repositories.KidRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.IUserRepository;

@Service
public class ConsultationService implements IConsultationService {

	@Autowired
	ConsultationRepository consultationRepository;
	@Autowired
	KidRepository kidRepository;
	@Autowired
	IUserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	/*public Retour<User> affectConsultationToKid(Consultation consultation, int idK, int idD) throws Exception {

		Retour<User> ret = new Retour<User>();
		if (consultation.getDateConsultation().before(new Date(System.currentTimeMillis()))) {
			System.out.println("Wrong date!!!");
			ret.setStringValue("Wrong date!!!");
		} else {
			List<User> doctors = new ArrayList<User>();
			User director = getCurrentUser();
			// User director = userRepository.findById(idA).get();
			User doctor = userRepository.findById(idD).get();
			Kid kid = kidRepository.findById(idK).get();

			consultation.setKid(kid);
			consultation.setDirector(director);

			List<Consultation> consults = consultationRepository.findDoctorAvailable(doctor,
					consultation.getDateConsultation(), consultation.getTime());
			if (consults.isEmpty()) {
				consultation.setDoctor(doctor);
				consultationRepository.save(consultation);
				ret.setStringValue("Consultation added successfully to doctor " + doctor.getFirstName() + " "
						+ doctor.getLastName());
			} else {
				List<User> users = getAllUsers();
				for (User user : users) {
					if (user.getRole().getRoleType() == RoleType.Doctor) {
						System.out.println("userRole: " + user.getRole().getRoleType());
						System.out.println(user.getRole().getRoleType() == RoleType.Doctor);
						System.out.println(user.getDoctorConsultations().isEmpty());
						if (user.getDoctorConsultations().isEmpty()) {
							doctors.add(user);
						} else {
							List<Consultation> docConsultations = consultationRepository.findDoctorAvailable(user,
									consultation.getDateConsultation(), consultation.getTime());
							if (docConsultations.isEmpty()) {
								doctors.add(user);
							}
						}
					}
				}
				ret.setStringValue("Sorry the doctor is not available!! Here are the availables doctors!");
				ret.setObjectValue(doctors);
			}
		}
		return ret;
	}*/
	
	public String affectConsultationToKid(Consultation consultation, int idK, int idD) throws Exception {
		String respense;
		Retour<User> ret = new Retour<User>();
		int h = consultation.getTime().getHours();
		consultation.getTime().setHours(h-1);
		System.out.println("tiime:: "+consultation.getTime());
		if (consultation.getDateConsultation().before(new Date(System.currentTimeMillis()))) {
			System.out.println("Wrong date!!!");
			respense= "1";
		} else {
			List<User> doctors = new ArrayList<User>();
			User director = getCurrentUser();
			// User director = userRepository.findById(idA).get();
			User doctor = userRepository.findById(idD).get();
			Kid kid = kidRepository.findById(idK).get();

			consultation.setKid(kid);
			consultation.setDirector(director);

			List<Consultation> consults = consultationRepository.findDoctorAvailable(doctor,
					consultation.getDateConsultation(), consultation.getTime());
			if (consults.isEmpty()) {
				consultation.setDoctor(doctor);
				consultationRepository.save(consultation);
				respense= "2";
			} else {
				List<User> users = getAllUsers();
				for (User user : users) {
					if (user.getRole().getRoleType() == RoleType.Doctor) {
						System.out.println("userRole: " + user.getRole().getRoleType());
						System.out.println(user.getRole().getRoleType() == RoleType.Doctor);
						System.out.println(user.getDoctorConsultations().isEmpty());
						if (user.getDoctorConsultations().isEmpty()) {
							doctors.add(user);
						} else {
							List<Consultation> docConsultations = consultationRepository.findDoctorAvailable(user,
									consultation.getDateConsultation(), consultation.getTime());
							if (docConsultations.isEmpty()) {
								doctors.add(user);
							}
						}
					}
				}
				respense= "3";
				//ret.setObjectValue(doctors);
			}
		}
		System.out.println(respense);
		return respense;
	}
	public void addC(Consultation consultation) throws Exception {
		consultationRepository.save(consultation);
		System.out.println(consultation.getDateConsultation());
		System.out.println(consultation.getTime());
		System.out.println(consultation.getDoctor().getFirstName());
		System.out.println(consultation.getKid().getFirstName());
		System.out.println("dooone!!");
	}
	
	@Override
	public void deleteConsultation(int id) {
		consultationRepository.deleteConsultById(id);
		//return "Consultation deleteded successfully";
	}

	@Override
	public Consultation updateConsultation(int idC, Consultation consultation) {
		Consultation c = consultationRepository.findById(idC).get();
		c.setDateConsultation(consultation.getDateConsultation());
		// Doctor
		int h = consultation.getTime().getHours();
		c.getTime().setHours(h-1);
		c.setTime(consultation.getTime());
		c.getTime().setHours(h-1);
		c.setDoctor(consultation.getDoctor());
		c.setKid(consultation.getKid());
		consultationRepository.save(c);
		return c;
	}

	@Override
	public List<Consultation> displayAllConsultations() {
		return consultationRepository.displayAllConsultations();
	}

	@Override
	public List<Consultation> displayConsultationsToDay() {
		Date currentDate = new Date(System.currentTimeMillis());
		return consultationRepository.findAllByDateConsultation(currentDate);
	}

	@Override
	public List<Consultation> displayConsultationsByKid(int idK) {
		Kid kid = kidRepository.findById(idK).get();
		return consultationRepository.findConsultationByKid(kid);
	}

	@Override
	public List<Consultation> displayConsultationsByDoctor(int idD) {
		User doctor = userRepository.findById(idD).get();
		return consultationRepository.findConsultationByDoctor(doctor);
	}

	@Override
	public List<Consultation> displayMyConsult() throws Exception {
		// User doctor = userRepository.findById(idD).get();
		User doctor = getCurrentUser();
		return consultationRepository.findConsultationByDoctor(doctor);
	}

	@Override
	public Map<String, Integer> percentageParticipationByDoctor() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Double nbConsult = (double) consultationRepository.count();
		List<User> doctors = userRepository.findAllByRole(RoleType.Doctor);
		for (User user : doctors) {
			if (user.getDoctorConsultations().isEmpty()) {
				map.put(user.getFirstName(), 0);
			} else {
				List<Consultation> consult = consultationRepository.findConsultationByDoctor(user);
				double d = (double) Math.round(consult.size() / nbConsult * 100) / 100;
				map.put(user.getFirstName(), (int) (d * 100));
			}
		}
		System.out.println(map);
		return map;
	}

	@Override
	public int nbrConsultPerMonth(int m) {
		List<Consultation> consult = consultationRepository.findByMatchMonthAndMatchDay("-" + m + "-");
		return consult.size();
	}

	public User getCurrentUser() throws Exception {
		Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (follower instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) follower).getUser();
			System.out.println("iD::: " + user.getIdUser());
			System.out.println("namee::: " + user.getFirstName());
			return user;
		}
		return null;
	}

	@Override
	public Consultation displayById(int id) {
		return consultationRepository.findById(id).get();
	}
	
	public List<User> getDoctors(){
		return userRepository.findAllByRole(RoleType.Doctor);
	}
}
