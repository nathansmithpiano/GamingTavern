package com.skilldistillery.gaminghub.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BlockedUserId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "blocked_user_id")
	private int blockedUserId;

	public BlockedUserId() {
		super();
	}

	public BlockedUserId(int userId, int blockedUserId) {
		super();
		this.userId = userId;
		this.blockedUserId = blockedUserId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBlockedUserId() {
		return blockedUserId;
	}

	public void setBlockedUserId(int blockedUserId) {
		this.blockedUserId = blockedUserId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(blockedUserId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockedUserId other = (BlockedUserId) obj;
		return blockedUserId == other.blockedUserId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "BlockedUserId [userId=" + userId + ", blockedUserId=" + blockedUserId + "]";
	}

}
