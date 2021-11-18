package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.NotificationMsg;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.MessageRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;

@Service
public class MessageService implements IMessageService {

	@Autowired
	IUserRepository userRepository;
	@Autowired
	FollowService followService;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	FollowRepository followRepository;
	@Autowired
	NotificationMsgService notificationMsgService;

	@Override
	public Retour<User> connected() throws Exception {
		Retour<User> rt = new Retour<>();
		//User user = getCurrentUser();
		User user = userRepository.findById(3).get();
		user.setStatus(true);
		userRepository.save(user);
		List<User> myFriends = followService.getUserFollowing(user.getIdUser());
		rt.setStringValue("Status: " + user.isStatus());
		rt.setObjectValue(myFriends);
		return rt;
	}

	@Override
	public Retour<User> disConnected() throws Exception {
		Retour<User> rt = new Retour<>();
		//User user = getCurrentUser();
		User user = userRepository.findById(3).get();
		user.setStatus(false);
		userRepository.save(user);
		List<User> myFriends = followService.getUserFollowing(user.getIdUser());
		rt.setStringValue("Status: " + user.isStatus());
		rt.setObjectValue(myFriends);
		return rt;
	}

	@Override
	public Retour<Message> sendMessage(int idR, Message message) throws Exception {
		Retour<Message> rt = new Retour<>();

		// User sender = userRepository.findById(idS).get();
		//User sender = getCurrentUser();
		User sender = userRepository.findById(3).get();
		User receiver = userRepository.findById(idR).get();

		Message m = new Message();
		System.out.println("tst1: ");
		List<Message> messages1 = messageRepository.getMessages(sender, receiver);
		System.out.println("messages1=> " + messages1.size());
		System.out.println("tst2: ");
		List<Message> messages2 = messageRepository.getMessages(receiver, sender);
		System.out.println("messages2=> " + messages2.size());
		List<Message> messages = new ArrayList<Message>();
		messages.addAll(messages1);
		messages.addAll(messages2);
		System.out.println("messages=> " + messages.size());
		// Delete the oldest message
		if (messages1.size() >= 10) {
			m = messages1.get(0);
			messageRepository.deleteMessageById(m.getIdMessage());
		}

		List<User> myFriends = followRepository.listFollowing(sender);
		if (!myFriends.contains(receiver)) {
			rt.setStringValue("Sorry you can't connect him!, follow him at the first");
		} else {
			message.setSender(sender);
			message.setReceiver(receiver);
			message.setDate(new Date(System.currentTimeMillis()));
			message.setTime(new Date(System.currentTimeMillis()));
			messageRepository.save(message);
			NotificationMsg notificationMsg = new NotificationMsg();
			notificationMsg
					.setContent("You get new message from " + sender.getFirstName() + " " + sender.getLastName());
			notificationMsgService.addNotif(sender.getIdUser(), idR, notificationMsg);
			rt.setStringValue("message sent successfully!!");
			rt.getObjectValue().add(message);
		}
		return rt;
	}

	@Override
	public List<Message> checkMessage(int idR) throws Exception {
		User sender = userRepository.findById(3).get();
		User receiver = userRepository.findById(idR).get();
		//User receiver = getCurrentUser();
		List<Message> messages = messageRepository.getAllOrderByTime();
		List<Message> messageShow = new ArrayList<>();
		for (Message message : messages) {
			if (message.getSender().getIdUser() == sender.getIdUser()
					& message.getReceiver().getIdUser() == receiver.getIdUser()) {
				messageShow.add(message);
			}
			if (message.getSender().getIdUser() == receiver.getIdUser()
					& message.getReceiver().getIdUser() == sender.getIdUser()) {
				messageShow.add(message);
			}
		}
		System.out.println("messageShow::: " + messageShow.size());
		return messageShow;
	}

	public List<User> followers(int id) {
		User sender = userRepository.findById(id).get();
		List<User> myFriends = followRepository.listFollowing(sender);
		return myFriends;
	}
	
	public User getReceiver(int id) {
		return userRepository.findById(id).get();
	}
	/*public User getCurrentUser() throws Exception {
		Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (follower instanceof UserDetailsImpl) {
			User user = ((UserDetailsImpl) follower).getUser();
			System.out.println("iD::: " + user.getIdUser());
			System.out.println("namee::: " + user.getFirstName());
			return user;
		}
		return null;
	}*/

}
