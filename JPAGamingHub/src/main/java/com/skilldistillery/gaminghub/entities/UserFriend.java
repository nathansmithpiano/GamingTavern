package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_friend")
public class UserFriend {

	@EmbeddedId
	private UserFriendId id;

	private LocalDateTime created;

	public UserFriend() {
		super();
	}

	public UserFriendId getId() {
		return id;
	}

	public void setId(UserFriendId id) {
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
		UserFriend other = (UserFriend) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserFriend [id=" + id + ", created=" + created + "]";
	}

}
