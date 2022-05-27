package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blocked_user")
public class BlockedUser {

	@EmbeddedId
	private BlockedUserId id;

	private LocalDateTime created;
	private String reason;

	public BlockedUser() {
		super();
	}

	public BlockedUserId getId() {
		return id;
	}

	public void setId(BlockedUserId id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
		BlockedUser other = (BlockedUser) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BlockedUser [id=" + id + ", created=" + created + ", reason=" + reason + "]";
	}

}
