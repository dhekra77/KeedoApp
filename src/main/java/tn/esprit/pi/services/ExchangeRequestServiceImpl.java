package tn.esprit.pi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.AppointmentStatus;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.repositories.IExchangeRepository;
import tn.esprit.pi.repositories.IMeetingRepository;


@Service
public class ExchangeRequestServiceImpl implements IExchangeService {



	IMeetingRepository iMeetingRepository;
    @Override
    public boolean checkIfEligibleForExchange(int userId, int appointmentId) {
        Meeting appointment = iMeetingRepository.findById(appointmentId).get();
        return appointment.getStartDate().atStartOfDay().minusHours(24).isAfter(LocalDateTime.now()) && appointment.getStatus().equals(AppointmentStatus.SCHEDULED) && appointment.getUsers().getIdUser() == userId;
    }

  

    @Override
    public boolean checkIfExchangeIsPossible(int oldAppointmentId, int newAppointmentId, int userId) {
    	Meeting oldAppointment = iMeetingRepository.findById(oldAppointmentId).get();
        Meeting newAppointment = iMeetingRepository.findById(newAppointmentId).get();
        
        
        if (oldAppointment.getUsers().getIdUser() == userId) {
            return 
                    ( oldAppointment.getKindergarden().equals(newAppointment.getKindergarden())
                    && oldAppointment.getStartDate().atStartOfDay().minusHours(24).isAfter(LocalDateTime.now())
                    && newAppointment.getStartDate().atStartOfDay().minusHours(24).isAfter(LocalDateTime.now()));
        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }

    }

	@Override
	public boolean acceptExchange(int exchangeId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectExchange(int exchangeId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestExchange(int oldAppointmentId, int newAppointmentId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public List<Meeting> getEligibleAppointmentsForExchange(int appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

    /*@Override
    public boolean acceptExchange(int exchangeId, int userId) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.getOne(exchangeId);
        Meeting requestor = exchangeRequest.getRequestor();
        Meeting requested = exchangeRequest.getRequested();
        Parent tempCustomer = requestor.getCustomer();
        requestor.setStatus(AppointmentStatus.SCHEDULED);
        exchangeRequest.setStatus(ExchangeStatus.ACCEPTED);
        requestor.setCustomer(requested.getCustomer());
        requested.setCustomer(tempCustomer);
        exchangeRequestRepository.save(exchangeRequest);
        appointmentRepository.save(requested);
        appointmentRepository.save(requestor);
        notificationService.newExchangeAcceptedNotification(exchangeRequest, true);
        return true;
    }

    @Override
    public boolean rejectExchange(int exchangeId, int userId) {
        ExchangeRequest exchangeRequest = exchangeRequestRepository.getOne(exchangeId);
        Appointment requestor = exchangeRequest.getRequestor();
        exchangeRequest.setStatus(ExchangeStatus.REJECTED);
        requestor.setStatus(AppointmentStatus.SCHEDULED);
        exchangeRequestRepository.save(exchangeRequest);
        appointmentRepository.save(requestor);
        notificationService.newExchangeRejectedNotification(exchangeRequest, true);
        return true;
    }

    @Override
    public boolean requestExchange(int oldAppointmentId, int newAppointmentId, int userId) {
        if (checkIfExchangeIsPossible(oldAppointmentId, newAppointmentId, userId)) {
            Appointment oldAppointment = appointmentRepository.getOne(oldAppointmentId);
            Appointment newAppointment = appointmentRepository.getOne(newAppointmentId);
            oldAppointment.setStatus(AppointmentStatus.EXCHANGE_REQUESTED);
            appointmentRepository.save(oldAppointment);
            ExchangeRequest exchangeRequest = new ExchangeRequest(oldAppointment, newAppointment, ExchangeStatus.PENDING);
            exchangeRequestRepository.save(exchangeRequest);
            notificationService.newExchangeRequestedNotification(oldAppointment, newAppointment, true);
            return true;
        }
        return false;
    }
    */
}