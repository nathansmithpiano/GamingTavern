package com.skilldistillery.gaminghub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Chat;
import com.skilldistillery.gaminghub.entities.Message;
import com.skilldistillery.gaminghub.repositories.ChatRepository;
import com.skilldistillery.gaminghub.repositories.MessageRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private MessageRepository messageRepo;

	@Override
	public List<Chat> index() {
		return chatRepo.findAll();
	}

	@Override
	public Chat getChatById(int chatId) {
		Optional<Chat> op = chatRepo.findById(chatId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Chat> getChatsByUsername(String username) {
		return chatRepo.findByAllUsersUsername(username);
	}

	@Override
	public Chat create(Chat chat) {
		return chatRepo.saveAndFlush(chat);
	}

	@Override
	public Message createMessage(Message message) {
		return messageRepo.saveAndFlush(message);
	}

	@Override
	public Message getMessageById(int id) {
		Optional<Message> op = messageRepo.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteMessage(int messageId) {
		boolean deleted = false;
		if (messageRepo.existsById(messageId)) {
			messageRepo.deleteById(messageId);
			deleted = true;
		}
		return deleted;
	}

}
