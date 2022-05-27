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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alias")
public class Alias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private boolean enabled;
	private String name;
	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	private LocalDateTime created;
	private LocalDateTime updated;

	@OneToMany(mappedBy = "alias")
	private List<Clan> clans;

	@ManyToMany
	@JoinTable(name = "alias_platform", joinColumns = @JoinColumn(name = "alias_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
	private List<Platform> platforms;

	@ManyToMany
	@JoinTable(name = "alias_game", joinColumns = @JoinColumn(name = "alias_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;

	@ManyToMany
	@JoinTable(name = "alias_server", joinColumns = @JoinColumn(name = "alias_id"), inverseJoinColumns = @JoinColumn(name = "server_id"))
	private List<Server> servers;

	@ManyToMany
	@JoinTable(name = "alias_clan", joinColumns = @JoinColumn(name = "alias_id"), inverseJoinColumns = @JoinColumn(name = "clan_id"))
	private List<Clan> clan;

	@ManyToMany
	@JoinTable(name = "meetup_alias", joinColumns = @JoinColumn(name = "meetup_id"), inverseJoinColumns = @JoinColumn(name = "alias_id"))
	private List<Meetup> meetups;

	public Alias() {
		super();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Clan> getClans() {
		return clans;
	}

	public void setClans(List<Clan> clans) {
		this.clans = clans;
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<Clan> getClan() {
		return clan;
	}

	public void setClan(List<Clan> clan) {
		this.clan = clan;
	}

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
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
		Alias other = (Alias) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Alias [id=" + id + ", user=" + user + ", enabled=" + enabled + ", name=" + name + ", description="
				+ description + ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated + "]";
	}

}