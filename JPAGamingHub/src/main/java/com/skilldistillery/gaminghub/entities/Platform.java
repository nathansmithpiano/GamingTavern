package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Platform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean enabled;
	private String name;
	private String type;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	@ManyToMany
	@JoinTable(name = "alias_platform",
	joinColumns = @JoinColumn(name = "platform_id"), 
	inverseJoinColumns = @JoinColumn(name = "alias_id"))
	private List<Alias> aliases;
	
	@ManyToMany
	@JoinTable(name = "platform_game",
	joinColumns = @JoinColumn(name = "platform_id"),
	inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;

	public Platform() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public List<Alias> getAliases() {
		return aliases;
	}

	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
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
		Platform other = (Platform) obj;

		return id == other.id;

	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", enabled=" + enabled + ", name=" + name + ", type=" + type + ", description="

				+ description + ", created=" + created + ", updated=" + updated + "]";
	}

}
