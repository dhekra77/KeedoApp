package tn.esprit.pi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.AppointmentStatus;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IMeetingRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.KindergardenRepository;

@Service
public class MeetingServiceImpl  implements IMeetingService{

	private static final int NUMBER_OF_CANCELATION_PER_MONTH = 3;

	@Autowired
	private IMeetingRepository iMeetingRepository;
	
	@Autowired
	private IUserRepository iUserRepository;


	@Autowired 
	private KindergardenRepository iKendergardenRepository;
	
	
	@Override
	public void deleteMeeting(int id) {
		iMeetingRepository.deleteById(id);
	}

	@Override
	public Meeting updateMeeting(int id) {
		Meeting m = iMeetingRepository.findById(id).get();
		return iMeetingRepository.save(m);
		
	
	}

	@Override
	public List<Meeting> getAllMeeting() {
		
		List<Meeting>meetings = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting> result =  new ArrayList<Meeting>();
		meetings.forEach(m ->result.add(m));
		return result;
	}

	@Override
	public Meeting getMeetingById(int id) {
		// TODO Auto-generated method stub
		return iMeetingRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<?> createNewMeeting(int parentId, int kindergardenId, Meeting meeting) {
		
		
		List<Meeting> results = new ArrayList<>();
		results = (List<Meeting>) iMeetingRepository.findAll();

		
		
		try{
			for(int i=0 ; i<results.size();i++) {
				if(meeting.getStartDate()!=null) {
					if(meeting.getStartDate().isBefore(results.get(i).getStartDate())
				
							|| meeting.getStartDate().compareTo((results.get(i).getStartDate())) ==1
							) 
				
						return ResponseEntity.ok("meeting exist at : "+meeting.getStartDate());

	
				}	
								
			}
			User parent =iUserRepository.findById(parentId).get();

			System.out.println("Role is  "+parent.getRole().getRoleType());
			if(parent.getRole().getRoleType().equals(RoleType.Parent)) {
				
	 			meeting.setStatus(AppointmentStatus.SCHEDULED);
				meeting.setCreatedAt(LocalDateTime.now());
				meeting.setUsers(iUserRepository.findByidUser(parent.getIdUser()));
				System.out.println("parent ="+meeting.getUsers());
				meeting.setKindergarden(iUserRepository.findDirectorKindergerden(kindergardenId));
				System.out.println("Kindergarden ="+meeting.getKindergarden());
				iMeetingRepository.save(meeting);
			
				return ResponseEntity.ok(" Meet created succesfully start at:"+meeting.getStartDate()+", and finished at: "+meeting.getTime()+"\n parent affected is "
				+meeting.getUsers().getFirstName()+" and kindergarden director is "+meeting.getKindergarden().getDirector().getFirstName());
				
			}	
			return ResponseEntity.ok("Opps create New Meeting failed");
			
		}catch(NoSuchElementException e) {
			return ResponseEntity.ok("Opps no parent");
	
		}
	
	}

	@Override
		public List<Meeting> getMeetingByParentId(int userId) {
			List<Meeting>meeting = (List<Meeting>) iMeetingRepository.findAll();
			List<Meeting>result = new ArrayList<>();
	
			for(Meeting m : meeting) {
				if(m.getUsers().getRole().getRoleType().equals(RoleType.Parent)) {
					result.add(m);
				}
			}
		return result;
	}

	@Override
	public List<Meeting> getMeetingByKindergardenId(int kindergardenId) {
		return null;
	}

	@Override
	public List<Meeting> getMeetingByKindergardenAtDay(int kindergardenId, String day) {
		Kindergarden kindergardenMeeting = iMeetingRepository.findKindergarden(kindergardenId);
		System.out.println("Kinder= "+day);
		LocalDate l = LocalDate.parse(day);
        return iMeetingRepository.getMeetingByKindergardenDirectorAtDay(kindergardenMeeting.getId(), l.atStartOfDay(), l.atStartOfDay().plusDays(1));
	}

	@Override
	public List<Meeting> getMeetingByCustomerAtDay(int kindergardenId, LocalDate day) {
		return null;
	}

	@Override
	public List<Meeting> getConfirmedMeetingByParentId(int userId) {
		return null;
	}

	@Override
	public List<Meeting> getCanceledMeetingsByParentIdForCurrentMonth(int userId) {
		return null;
	}

	  @Override
	    public ResponseEntity<?>  cancelUserAppointmentById(int meetingId, int parentIdOrKindergardenId) {
	        Meeting  meeting = iMeetingRepository.findById(meetingId).get();
			Kindergarden kindergarden = iUserRepository.findDirectorKindergerden(parentIdOrKindergardenId);
			
			User parent =iUserRepository.findParent(RoleType.Parent,parentIdOrKindergardenId);

	        if (meeting.getUsers().equals(parent) || meeting.getKindergarden().getDirector().getIdUser()==kindergarden.getDirector().getIdUser()) {
	        	meeting.setStatus(AppointmentStatus.CANCELED);
	            User canceler = iUserRepository.findByidUser(parentIdOrKindergardenId);
	            meeting.setCanceler(canceler);
	            meeting.setCanceledAt(LocalDateTime.now());
	            iMeetingRepository.save(meeting);
	            if (canceler.equals(parent)) {
	                //notificationService.newAppointmentCanceledByCustomerNotification(appointment, true);
	            	return ResponseEntity.ok("canceld by parent");
	            } else if (canceler.equals(kindergarden)) {
	               // notificationService.newAppointmentCanceledByProviderNotification(appointment, true);
	            	return ResponseEntity.ok("canceld by director kindergarden");

	            }
	        } else {
	        	return ResponseEntity.ok("Unauthorized");
	        }

	        return  ResponseEntity.ok("not allowed to cancel this meet");
	    }

	
	@Override
	public String getCancelNotAllowedReason(int parentId,int kindergardenId, int meetingId) {
		User parent = iUserRepository.findParent(RoleType.Parent, parentId);
		Meeting meeting = iMeetingRepository.findById(meetingId).get();
	      LocalDateTime localDateTime1 = meeting.getStartDate().atStartOfDay();


		System.out.println("callll =="+LocalDateTime.now().plusDays(-1).toLocalDate());
		if(meeting.getUsers().equals(parent)) {
			if(!meeting.getStatus().equals(AppointmentStatus.SCHEDULED)) {
					return "cannot cancel meeting SCHEDULED";

				
			}
			
			
			else if(LocalDateTime.now().plusDays(-1).isAfter(localDateTime1)) {
				return "meeting which will be in less than 24 hours cannot be canceled";
				
			}
			
			else if(iMeetingRepository.findParentIdCanceledAfterDate(parentId,LocalDateTime.now()
					.with(TemporalAdjusters.firstDayOfMonth())).size() >= NUMBER_OF_CANCELATION_PER_MONTH){
				return "you exceeded maximum number of cancelations per month";
			}
			else {
				return null;
			}
			
		}
		
		return null;
	}

	
	
	
	
	 @Override
	    public List<Meeting> updateAppointmentsStatusesWithExpiredExchangeRequest() {
	        
		 List<Meeting>list = new ArrayList<>();
		 System.out.println("hiii =="+LocalDate.now().plusDays(1));
		 iMeetingRepository.findExchangeRequestedWithStartBefore(LocalDate.now().plusDays(1))
	                .forEach(appointment -> {
	                    appointment.setStatus(AppointmentStatus.SCHEDULED);
	                    list.add(appointment);
	                    iMeetingRepository.save(appointment);
	                });
	        return list;
	    }
	
	
	
	
	
	
	
	@Override
	public List<Meeting> getMeetingByKindergardenDirectorAtDay(int providerId, LocalDate day) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
	
	
	
