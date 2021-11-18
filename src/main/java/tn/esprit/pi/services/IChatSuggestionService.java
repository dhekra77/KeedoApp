package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.ChatSuggestion;

public interface IChatSuggestionService {

	void addChatSuggestion(String content);

	List<ChatSuggestion> getAllSuggestion();

	String deleteById(int id);
}
