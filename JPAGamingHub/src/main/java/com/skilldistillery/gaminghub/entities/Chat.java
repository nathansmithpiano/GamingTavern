package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chat")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "created_by_user_id")
	private User user;
	private boolean enabled;
	private String title;
	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	private LocalDateTime created;
	private LocalDateTime updated;

//	@JsonIgnore
//	@OneToMany(mappedBy = "chat")
//	private List<Message> messages;

	public Chat() {
		super();
	}

public Chat(int id, User user, boolean enabled, String title, String description, String imageUrl,
		LocalDateTime created, LocalDateTime updated) {
	super();
	this.id = id;
	this.user = user;
	this.enabled = enabled;
	this.title = title;
	this.description = description;
	this.imageUrl = imageUrl;
	this.created = created;
	this.updated = updated;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public LocalDateTime getCreated() {
	return created;
}

public void setCreated(LocalDateTime created) {
	this.created = created;
}

public LocalDateTime getUpdated() {
	return updated;
}

public void setUpdated(LocalDateTime updated) {
	this.updated = updated;
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
	Chat other = (Chat) obj;
	return id == other.id;
}

@Override
public String toString() {
	return "Chat [id=" + id + ", user=" + user + ", enabled=" + enabled + ", title=" + title + ", description="
			+ description + ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated + "]";
}
	
	

}
