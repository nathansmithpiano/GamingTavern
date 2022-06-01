package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Chat;
import com.skilldistillery.gaminghub.entities.Message;

public interface ChatService {

	List<Chat> index();
	Chat getChatById(int chatId);
	List<Chat> getChatsByUsername(String username);
	Chat create(Chat chat);
	Message getMessageById(int id);
	Message createMessage(Message message);
	boolean deleteMessage(int messageId);

}
