package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Meeting;

public interface IExchangeService {

    boolean checkIfEligibleForExchange(int userId, int appointmentId);

    List<Meeting> getEligibleAppointmentsForExchange(int appointmentId);

    boolean checkIfExchangeIsPossible(int oldAppointmentId, int newAppointmentId, int userId);

    boolean acceptExchange(int exchangeId, int userId);

    boolean rejectExchange(int exchangeId, int userId);

    boolean requestExchange(int oldAppointmentId, int newAppointmentId, int userId);
}