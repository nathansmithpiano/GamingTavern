package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "chat")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "created_by_user_id")
	private User creatingUser;

	private boolean enabled;
	private String title;
	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	private LocalDateTime created;
	private LocalDateTime updated;

//	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "chat_user", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> allUsers;

	@JsonIgnoreProperties({"chat"})
	@OneToMany
//	@JoinTable(name = "chat_user", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JoinColumn(name = "chat_user_chat_id")
	private List<Message> messages;

	public Chat() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getCreatingUser() {
		return creatingUser;
	}

	public void setCreatingUser(User creatingUser) {
		this.creatingUser = creatingUser;
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

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

//	public List<String> getAllUsersnames() {
//		List<String> usernames = new ArrayList<>();
//		for (User user : allUsers) {
//			usernames.add(user.getUsername());
//		}
//		return usernames;
//	}

	public void addUser(User user) {
		if (this.allUsers == null) {
			this.allUsers = new ArrayList<>();
		}
		this.allUsers.add(user);
		if (!user.getChats().contains(this)) {
			user.addChat(this);
		}
	}

	public void removeUser(User user) {
		if (user != null) {
			this.allUsers.remove(user);
			if (user.getChats().contains(this)) {
				user.removeChat(this);
			}
		}
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(message);
		if (message.getChat() == null) {
			message.setChat(this);
		}
	}

	public void removeMessage(Message message) {
		if (this.messages != null) {
			this.messages.remove(message);
			if (message.getChat().creatingUser.getUsername().equals(this.creatingUser.getUsername())) {
				message.setChat(null);
			}
		}
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
		return "Chat [id=" + id + ", enabled=" + enabled + ", title=" + title + ", description=" + description
				+ ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated + "]";
	}

}
