package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.ChatSuggestion;
import tn.esprit.pi.repositories.ChatSuggestionRepository;

@Service
public class ChatSuggestionService implements IChatSuggestionService {

	@Autowired
	ChatSuggestionRepository chatSuggestionRepository;

	@Override
	public void addChatSuggestion(String content) {
		ChatSuggestion chatSuggestion = new ChatSuggestion();
		chatSuggestion.setContent(content);
		chatSuggestionRepository.save(chatSuggestion);
	}

	@Override
	public List<ChatSuggestion> getAllSuggestion() {
		return (List<ChatSuggestion>) chatSuggestionRepository.findAll();
	}

	@Override
	public String deleteById(int id) {
		chatSuggestionRepository.deleteChatById(id);
		return "deleted seccussfully";
	}
}
