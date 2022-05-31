package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Meetup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private LocalDateTime date;
	private int capacity;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "timezone_id")
	private Timezone timezone;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "meetup_location", joinColumns = @JoinColumn(name = "meetup_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
	private List<Location> locations;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "meetup_alias", joinColumns = @JoinColumn(name = "alias_id"), inverseJoinColumns = @JoinColumn(name = "meetup_id"))
	private List<Alias> aliases;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "meetup_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "meetup_id"))
	private List<Game> games;

	public Meetup() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timezone getTimezone() {
		return timezone;
	}

	public void setTimezone(Timezone timezone) {
		this.timezone = timezone;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location location) {
		if (this.locations == null) {
			this.locations = new ArrayList<>();
		}
		this.locations.add(location);
		location.addMeetup(this);
	}

	public void removeLocation(Location location) {
		if (location != null) {
			this.locations.remove(location);
			location.removeMeetup(this);
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
	}

	public void removeAlias(Alias alias) {
		if (alias != null) {
			this.aliases.remove(alias);
			alias.removeMeetup(this);
		}
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {
		if (this.games == null) {
			this.games = new ArrayList<>();
		}
		this.games.add(game);
		game.addMeetup(this);
	}

	public void removeGame(Game game) {
		if (game != null) {
			this.games.remove(game);
			game.removeMeetup(this);
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
		Meetup other = (Meetup) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Meetup [id=" + id + ", name=" + name + ", date=" + date + ", capacity=" + capacity + ", description="
				+ description + ", created=" + created + ", updated=" + updated + ", timezone=" + timezone + "]";
	}

}