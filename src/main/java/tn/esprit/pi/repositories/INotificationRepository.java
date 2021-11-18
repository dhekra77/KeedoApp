package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.Notification;

public interface INotificationRepository extends CrudRepository<Notification, Integer> {

}
