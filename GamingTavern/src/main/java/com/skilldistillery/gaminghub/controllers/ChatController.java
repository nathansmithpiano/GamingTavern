package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Chat;
import com.skilldistillery.gaminghub.entities.Message;
import com.skilldistillery.gaminghub.services.ChatService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class ChatController {

	@Autowired
	private ChatService chatSvc;

	@GetMapping("chats")
	public List<Chat> index(Principal principal, HttpServletResponse resp) {
		List<Chat> chats = chatSvc.index();
		if (chats == null) {
			resp.setStatus(404);
			return null;
		}
		return chats;
	}

	@GetMapping("chats/{id}")
	public Chat getChatById(Principal principal, HttpServletResponse resp, @PathVariable int id) {
		Chat chat = chatSvc.getChatById(id);
		if (chat == null) {
			resp.setStatus(404);
		}
		return chat;
	}

	@GetMapping("chats/user/{username}")
	public List<Chat> getChatsByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		List<Chat> chats = chatSvc.getChatsByUsername(username);
		if (chats == null) {
			resp.setStatus(404);
			return null;
		}
		return chats;
	}

	@PostMapping("chats")
	public Chat createChat(Principal principal, HttpServletResponse resp, @RequestBody Chat chat) {
		Chat newChat = chatSvc.create(chat);
		if (newChat == null) {
			resp.setStatus(409);
			return null;
		}
		resp.setStatus(201);
		return newChat;
	}

	@GetMapping("messages/{id}")
	public Message getMessageById(Principal principal, HttpServletResponse resp, @PathVariable int id) {
		Message message = chatSvc.getMessageById(id);
		if (message == null) {
			resp.setStatus(404);
			return null;
		}
		resp.setStatus(400);
		return message;
	}

	@PostMapping("messages")
	public Message createMessage(Principal principal, HttpServletResponse resp, @RequestBody Message message) {
		System.err.println(message.getFromUser());
		System.err.println(message.getChat());
		Message newMessage = chatSvc.createMessage(message);
		if (newMessage == null) {
			resp.setStatus(409);
			return null;
		}
		resp.setStatus(201);
		return newMessage;
	}
	
	@DeleteMapping("messages/{messageId}")
	public void destroy(HttpServletResponse resp, @PathVariable int messageId, Principal principal) {
		if (chatSvc.deleteMessage(messageId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
