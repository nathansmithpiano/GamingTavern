package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chat_user")
public class ChatUser {

	@EmbeddedId
	private ChatUserId id;

	private LocalDateTime created;

	public ChatUser() {
		super();
	}

	public ChatUserId getId() {
		return id;
	}

	public void setId(ChatUserId id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

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
		ChatUser other = (ChatUser) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ChatUser [id=" + id + ", created=" + created + "]";
	}

}
