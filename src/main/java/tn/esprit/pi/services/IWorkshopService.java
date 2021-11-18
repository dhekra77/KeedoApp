package tn.esprit.pi.services;
import java.util.List;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.Workshop;
import tn.esprit.pi.entities.WorkshopCategory;

public interface IWorkshopService {
		
	public String deleteWorkshop(int id) throws Exception;
	
	public String updateWorkshop(Workshop w, int id) throws Exception;
	
	public List<Workshop> getAllWorkshops();
	
	public Workshop getWorkshopById(int id);
	
	public int CountWorkshops();
	
	public List<Workshop> getWorkshopsByUserId(int id);
	
	public int CountWorkshopsByUser(int id);
    
	public List<Workshop> searchWorkshops(String text);
	
	public List<Workshop> filterWorkshop(WorkshopCategory category);

	public User currentUser() throws Exception;

	public String addWorkshop(Workshop w) throws Exception;

	public List<Workshop> getFollowingWorkshops() throws Exception;
}