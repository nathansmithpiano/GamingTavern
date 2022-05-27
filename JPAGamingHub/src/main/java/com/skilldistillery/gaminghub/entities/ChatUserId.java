package com.skilldistillery.gaminghub.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChatUserId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "chat_id")
	private int chatId;

	@Column(name = "user_id")
	private int userId;

	public ChatUserId() {
		super();
	}

	public ChatUserId(int chatId, int userId) {
		super();
		this.chatId = chatId;
		this.userId = userId;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chatId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatUserId other = (ChatUserId) obj;
		return chatId == other.chatId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "ChatUserId [chatId=" + chatId + ", userId=" + userId + "]";
	}

}
