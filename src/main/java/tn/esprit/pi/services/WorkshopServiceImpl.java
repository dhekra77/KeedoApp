package tn.esprit.pi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.UnhealthyWord;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;
import tn.esprit.pi.repositories.IWorkshopRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.IUnhealthyWordRepository;


@Service
public class WorkshopServiceImpl implements IWorkshopService{

	
	@Autowired 
	IWorkshopRepository iWorkshopRepository;
	
	@Autowired 
	IUnhealthyWordRepository iUnhealthyWordRepository;
	
	@Autowired 
	FollowRepository followRepository;
	
	@Override
	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}
	
	
	@Override
	public String addWorkshop(Workshop w) throws Exception {
		w.setUser(currentUser());
		w.setCreateDate(LocalDateTime.now());
		for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
			if(w.getContent().toLowerCase().contains(uwd.getWord())){
				return("Sorry, you can't post a workshop that contains hate speech or bad words on Keedo.");
			}
		}
		iWorkshopRepository.save(w);
		return ("workshop added successfully");
	}
	
	
	@Override
	public String deleteWorkshop(int id) throws Exception {
		int iduser = currentUser().getIdUser();
		String roleuser = currentUser().getRole().getRoleType().name();
		Workshop w = iWorkshopRepository.findById(id).get();
		if ((iduser==w.getUser().getIdUser()) || roleuser=="Admin"){
			iWorkshopRepository.delete(w);							
			return "Workshop deleted successfully";
		}
		else{
			return "You are not allowed to delete this workshop";
		}
	}
	
	
	@Override
	public String updateWorkshop(Workshop w, int id) throws Exception {
		int iduser = currentUser().getIdUser();
		String roleuser = currentUser().getRole().getRoleType().name();
		Workshop workshop = iWorkshopRepository.findById(id).get();
		if (!((iduser==workshop.getUser().getIdUser())) && !(roleuser=="Admin")){
			System.out.println("wesletchi hne?");

			return("You are not allowed to update this workshop");
	
		}
		else{
		for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
			if(w.getContent().toLowerCase().contains(uwd.getWord())){
				return("Sorry, you can't post a workshop that contains hate speech or bad words on Keedo.");
			}}
		workshop.setModifyDate(LocalDateTime.now());
		workshop.setContent(w.getContent());
		workshop.setPdffile(w.getPdffile());
		workshop.setCategory(w.getCategory());

		iWorkshopRepository.save(workshop);
		return ("Workshop updated successfully");
		}
	}
	
	
	@Override
	public List<Workshop> getAllWorkshops() {
		List<Workshop>workshops = new ArrayList<Workshop>();
		iWorkshopRepository.findAll().forEach(e ->workshops.add(e));
		return workshops;
	}
	
	
	@Override
	public Workshop getWorkshopById(int id) {
		return iWorkshopRepository.findById(id).get();  
	}
	
	
	@Override
	public int CountWorkshops() {
		List <Workshop> workshops=(List<Workshop>) iWorkshopRepository.findAll();
		return workshops.size();
	}
	
	
	@Override
	public List<Workshop> getWorkshopsByUserId(int id) {
		return iWorkshopRepository.getWorkshopByUserId(id);
	}
	
	
	@Override
	public int CountWorkshopsByUser(int id) {
		List <Workshop> workshops=(List<Workshop>) iWorkshopRepository.getWorkshopByUserId(id);
		return workshops.size();
	}
	
	
	@Override
	public List<Workshop> searchWorkshops(String text) {
        return iWorkshopRepository.findWorkshopsByTextContaining(text);

	}
	
	
	@Override	
	public List<Workshop> filterWorkshop(WorkshopCategory category) {
		return iWorkshopRepository.getByCategory(category);
	}
	
	
	@Override
	public List<Workshop> getFollowingWorkshops() throws Exception {
		List<User> followings = followRepository.listFollowing(currentUser());
		List<Workshop> workshops = new ArrayList<Workshop>();
		for(Workshop w: iWorkshopRepository.findAll()){
			if (followings.contains(w.getUser())){
			workshops.add(w);
		}}
		return workshops;
	}
}
