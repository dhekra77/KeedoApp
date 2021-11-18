package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.ChatSuggestion;
import tn.esprit.pi.services.ChatSuggestionService;

@RestController
@RequestMapping("chatSug/")
public class ChatSuggestionController {

	@Autowired
	ChatSuggestionService chatSuggestionService;

	// localhost:8080/SpringMVC/servlet/chatSug/getAll
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("getAll")
	public List<ChatSuggestion> getAllSuggestion() {
		return chatSuggestionService.getAllSuggestion();
	}

	// localhost:8080/SpringMVC/servlet/chatSug/delete/1
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("delete/{id}")
	public String deleteById(@PathVariable("id") int id) {
		return chatSuggestionService.deleteById(id);
	}
}
