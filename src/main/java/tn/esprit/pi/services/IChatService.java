package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Chat;
import tn.esprit.pi.entities.ChatKeyWord;;

public interface IChatService {

	Chat addChat(Chat chat) throws Exception;

	String addKeyWords(List<ChatKeyWord> keyWords, int idC);

	List<Chat> displayAll();

	List<ChatKeyWord> diplayByChatId(int idC);

	Chat displayChatById(int idC);

	Chat updateChat(Chat chat, int idC);

	void deleteChat(int idC);

	String getRespenseBasedOnWord(String word, int idLang);

	String connectToChat();

	List<Chat> getChatsByMostRec();

}
