package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;

public interface IMessageService {
	Retour<User> connected() throws Exception;

	Retour<User> disConnected() throws Exception;

	Retour<Message> sendMessage(int idR, Message message) throws Exception;

	List<Message> checkMessage(int idS) throws Exception;

}
