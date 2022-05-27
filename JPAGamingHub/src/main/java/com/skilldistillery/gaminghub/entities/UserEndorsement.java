package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_endorsement")
public class UserEndorsement {

	@EmbeddedId
	private UserEndorsementId id;

	private LocalDateTime created;
	
	@ManyToOne
	@JoinColumn(name="endorsement_id")
	@MapsId("endorsementId")
	private Endorsement endorsement;
	
	@ManyToOne
	@JoinColumn(name="endorsed_user_id")
	@MapsId("endorsedUserId")
	private User endorsedUser;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId("endorsingUserId")
	private User endorsingUser;

	public Endorsement getEndorsement() {
		return endorsement;
	}

	public void setEndorsement(Endorsement endorsement) {
		this.endorsement = endorsement;
	}

	public User getEndorsedUser() {
		return endorsedUser;
	}

	public void setEndorsedUser(User endorsedUser) {
		this.endorsedUser = endorsedUser;
	}

	public User getEndorsingUser() {
		return endorsingUser;
	}

	public void setEndorsingUser(User endorsingUser) {
		this.endorsingUser = endorsingUser;
	}


	public UserEndorsement() {
		super();
	}

	public UserEndorsementId getId() {
		return id;
	}

	public void setId(UserEndorsementId id) {
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
		UserEndorsement other = (UserEndorsement) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserEndorsement [id=" + id + ", created=" + created + "]";
	}

}
