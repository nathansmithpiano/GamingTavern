package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	public Message getReplyingToMessage() {
		return replyingToMessage;
	}

	public void setReplyingToMessage(Message replyingToMessage) {
		this.replyingToMessage = replyingToMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chat, contents, created, id);
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
		return Objects.equals(chat, other.chat) && Objects.equals(contents, other.contents)
				&& Objects.equals(created, other.created) && id == other.id;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", contents=" + contents + ", created=" + created + ", chat=" + chat + "]";
	}
	
	
	
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", contents=");
		builder.append(contents);
		builder.append(", created=");
		builder.append(created);
		builder.append("]");
		return builder.toString();
	}

}
