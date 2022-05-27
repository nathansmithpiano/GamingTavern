package com.skilldistillery.gaminghub.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserEndorsementId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "endorsed_user_id")
	private int endorsedUserId;

	@Column(name = "endorsement_id")
	private int endorsementId;

	public UserEndorsementId() {
		super();
	}

	public UserEndorsementId(int userId, int endorsedUserId, int endorsementId) {
		super();
		this.userId = userId;
		this.endorsedUserId = endorsedUserId;
		this.endorsementId = endorsementId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEndorsedUserId() {
		return endorsedUserId;
	}

	public void setEndorsedUserId(int endorsedUserId) {
		this.endorsedUserId = endorsedUserId;
	}

	public int getEndorsementId() {
		return endorsementId;
	}

	public void setEndorsementId(int endorsementId) {
		this.endorsementId = endorsementId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endorsedUserId, endorsementId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEndorsementId other = (UserEndorsementId) obj;
		return endorsedUserId == other.endorsedUserId && endorsementId == other.endorsementId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserEndorsementId [userId=" + userId + ", endorsedUserId=" + endorsedUserId + ", endorsementId="
				+ endorsementId + "]";
	}

}
