package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;
import tn.esprit.pi.services.ChatService;

@RestController
@RequestMapping("chat/")
public class ChatController {

	@Autowired
	ChatService chatService;

	// Add Chat
	// localhost:8080/SpringMVC/servlet/chat/add
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("add")
	public Chat addContent(@RequestBody Chat chat) throws Exception {
		return chatService.addChat(chat);
	}

	// Add key words by chat
	// localhost:8080/SpringMVC/servlet/chat/addKW/1
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("addKW/{idC}")
	public String addKeyWords(@RequestBody List<ChatKeyWord> keyWords, @PathVariable int idC) {
		return chatService.addKeyWords(keyWords, idC);
	}

	// Delete chat with key word
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("delete/{idC}")
	public void deleteChat(@PathVariable int idC) {
		chatService.deleteChat(idC);
	}

	// Display All chats without response
	// localhost:8080/SpringMVC/servlet/chat/getAll
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("getAll")
	public List<Chat> displayAll() {
		return chatService.displayAll();
	}

	// Display words by response
	// localhost:8080/SpringMVC/servlet/chat/displayKeWords/1
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("displayKeWords/{idC}")
	public List<ChatKeyWord> diplayByChatId(@PathVariable int idC) {
		return chatService.diplayByChatId(idC);
	}

	// Display chat by id without keyWords
	// localhost:8080/SpringMVC/servlet/chat/displayChatId/1
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("displayChatId/{idC}")
	public Chat displayChatById(@PathVariable int idC) {
		return chatService.displayChatById(idC);
	}

	// Edit chat by id
	// localhost:8080/SpringMVC/servlet/chat/updateC/1
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("updateC/{idC}")
	public Chat updateChat(@RequestBody Chat chat, @PathVariable int idC) {
		return chatService.updateChat(chat, idC);
	}

	// localhost:8080/SpringMVC/servlet/chat/replayBasedOnWords
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("replayBasedOnWords/{idLang}")
	public String getRespenseBasedOnWord(@RequestBody String word, @PathVariable("idLang") int idLang) {
		return chatService.getRespenseBasedOnWord(word.intern(), idLang);
	}

	// localhost:8080/SpringMVC/servlet/chat/connectToChat
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("connectToChat")
	public String connectToChat() {
		return chatService.connectToChat();
	}

	// localhost:8080/SpringMVC/servlet/chat/ChatsByMostRec
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("ChatsByMostRec")
	public List<Chat> getChatsByMostRec() {
		return chatService.getChatsByMostRec();
	}

	// localhost:8080/SpringMVC/servlet/chat/translate
	@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')  ")
	@GetMapping("translate")
	public String translate(@RequestBody String word) {
		return chatService.translate(word.intern());
	}
}
