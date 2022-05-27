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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String password;
	private boolean enabled;
	private String role;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "image_url")
	private String imageUrl;

	private LocalDateTime created;
	private LocalDateTime updated;

	@OneToMany(mappedBy = "user")
	private List<Alias> aliases;

	@OneToMany(mappedBy = "user")
	private List<Meetup> meetups;

	@ManyToMany
	@JoinTable(name = "user_location", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
	private List<Location> locations;

	// all chats a User is in
	@ManyToMany
	@JoinTable(name = "chat_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
	private List<Chat> chats;

	// all the chats a User has created
	@OneToMany(mappedBy = "creatingUser")
	private List<Chat> createdChats;

	@ManyToMany
	@JoinTable(name = "user_equipment", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipments;

	@ManyToMany
	@JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<User> friends;

	@ManyToMany
	@JoinTable(name = "blocked_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "blocked_user_id"))
	private List<User> blockedUsers;

	@OneToMany(mappedBy = "endorsingUser")
	private List<UserEndorsement> sentUserEndorsements;

	@OneToMany(mappedBy = "endorsedUser")
	private List<UserEndorsement> userReceivedEndorsements;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<Chat> getCreatedChats() {
		return createdChats;
	}

	public void setCreatedChats(List<Chat> createdChats) {
		this.createdChats = createdChats;
	}

	public void addCreatedChat(Chat createdChat) {
		if (this.createdChats == null) {
			this.createdChats = new ArrayList<>();
		}
		this.createdChats.add(createdChat);
		if (createdChat.getCreatingUser() == null) {
			createdChat.setCreatingUser(this);
		}
	}

	public void removeCreatedChat(Chat createdChat) {
		if (createdChat != null) {
			this.chats.remove(createdChat);
			if (createdChat.getCreatingUser().getUsername().equals(this.getUsername())) {
				createdChat.setCreatingUser(null);
			}
		}
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

	public void addChat(Chat chat) {
		if (this.chats == null) {
			this.chats = new ArrayList<>();
		}
		this.chats.add(chat);
		if (!chat.getAllUsers().contains(this)) {
			chat.addUser(this);
		}
	}

	public void removeChat(Chat chat) {
		if (chat != null) {
			this.chats.remove(chat);
			if (chat.getAllUsers().contains(this)) {
				chat.removeUser(this);
			}
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location location) {
		if (locations == null) {
			locations = new ArrayList<>();
		}
		if (location != null) {
			locations.add(location);
			location.addUser(this);
		}
	}

	public void removeLocation(Location location) {
		if (location != null) {
			locations.remove(location);
			location.removeUser(this);
		}
	}

	public List<Alias> getAliases() {
		return aliases;
	}

	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}

	public void addAlias(Alias alias) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<>();
		}
		this.aliases.add(alias);
		if (!alias.getUser().equals(this)) {
			alias.setUser(this);
		}
	}

	public void removeAlias(Alias alias) {
		if (alias != null) {
			this.aliases.remove(alias);
			if (alias.getUser().getUsername().equals(this.getUsername())) {
				alias.setUser(null);
			}
		}
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public void addEquipment(Equipment equipment) {
		if (this.equipments == null) {
			this.equipments = new ArrayList<>();
		}
		equipments.add(equipment);
		if (this.equipments != null) {
			equipment.addUser(this);
		}
	}

	public void removeEquipment(Equipment equipment) {
		if (equipment != null) {
			this.equipments.remove(equipment);
			equipment.removeUser(this);
		}
	}

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public void addFriend(User friend) {
		if (this.friends == null) {
			this.friends = new ArrayList<>();
		}
		this.friends.add(friend);
		friend.addFriend(this);
	}

	public void removeFriend(User friend) {
		if (friend != null) {
			this.friends.remove(friend);
			friend.removeFriend(this);
		}
	}

	public List<User> getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(List<User> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}

	public void addBlockedUser(User blockedUser) {
		if (this.blockedUsers == null) {
			this.blockedUsers = new ArrayList<>();
		}
		this.blockedUsers.add(blockedUser);
	}

	public void removeBlockedUser(User blockedUser) {
		if (blockedUser != null) {
			this.blockedUsers.remove(blockedUser);
		}
	}

	public List<UserEndorsement> getSentUserEndorsements() {
		return sentUserEndorsements;
	}

	public void setSentUserEndorsements(List<UserEndorsement> sentUserEndorsements) {
		this.sentUserEndorsements = sentUserEndorsements;
	}

	public void addSentUserEndorsement(UserEndorsement sentUserEndorsement) {
		if (this.sentUserEndorsements == null) {
			this.sentUserEndorsements = new ArrayList<>();
		}
		this.sentUserEndorsements.add(sentUserEndorsement);
		sentUserEndorsement.setEndorsingUser(this);
	}

	public void removeSentUserEndorsement(UserEndorsement sentUserEndorsement) {
		if (sentUserEndorsement != null) {
			this.sentUserEndorsements.remove(sentUserEndorsement);
			sentUserEndorsement.setEndorsingUser(null);
		}
	}

	public List<UserEndorsement> getUserReceivedEndorsements() {
		return userReceivedEndorsements;
	}

	public void setUserReceivedEndorsements(List<UserEndorsement> userReceivedEndorsements) {
		this.userReceivedEndorsements = userReceivedEndorsements;
	}

	public void addUserReceivedEndorsement(UserEndorsement userReceivedEndorsement) {
		if (this.userReceivedEndorsements == null) {
			this.userReceivedEndorsements = new ArrayList<>();
		}
		this.userReceivedEndorsements.add(userReceivedEndorsement);
		userReceivedEndorsement.setEndorsedUser(this);
	}

	public void removeUserReceivedEndorsement(UserEndorsement userReceivedEndorsement) {
		if (userReceivedEndorsement != null) {
			this.userReceivedEndorsements.remove(userReceivedEndorsement);
			userReceivedEndorsement.setEndorsedUser(null);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated + "]";
	}

}