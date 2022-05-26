package com.skilldistillery.gaminghub.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserFriendId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "friend_id")
	private int friendId;

	public UserFriendId() {
		super();
	}

	public UserFriendId(int userId, int friendId, LocalDateTime created) {
		super();
		this.userId = userId;
		this.friendId = friendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(friendId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFriendId other = (UserFriendId) obj;
		return friendId == other.friendId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserFriendId [userId=" + userId + ", friendId=" + friendId + "]";
	}

}
