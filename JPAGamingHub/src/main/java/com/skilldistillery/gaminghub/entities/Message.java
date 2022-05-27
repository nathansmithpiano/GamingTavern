package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// chat_user_chat
	// chat_user_user

	private String contents;
	private LocalDateTime created;

	@OneToOne
	@JoinColumn(name = "replying_to_message_id")
	private Message replyingToMessage;

	@OneToMany(mappedBy = "replyingToMessage")
	private List<Message> replies;

	@ManyToOne
	@JoinColumn(name = "chat_user_chat_id")
	private Chat chat;

	@OneToOne
	@JoinColumn(name = "chat_user_user_id")
	private User fromUser;

	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Message getReplyingToMessage() {
		return replyingToMessage;
	}

	public void setReplyingToMessage(Message replyingToMessage) {
		this.replyingToMessage = replyingToMessage;
	}

	public List<Message> getReplies() {
		return replies;
	}

	public void setReplies(List<Message> replies) {
		this.replies = replies;
	}

	public void addReply(Message reply) {
		if (this.replies == null) {
			this.replies = new ArrayList<>();
		}
		this.replies.add(reply);
		if (reply.getReplyingToMessage() == null) {
			reply.setReplyingToMessage(this);
		}
	}

	public void removeReply(Message reply) {
		if (reply != null) {
			this.replies.remove(reply);
			if (reply.getReplyingToMessage().equals(this)) {
				reply.setReplyingToMessage(null);
			}
		}
	}
	
//	public User getToUser() {
//		return this.replyingToMessage.getFromUser();
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", contents=" + contents + ", created=" + created + ", replyingToMessage="
				+ replyingToMessage + "]";
	}

}
