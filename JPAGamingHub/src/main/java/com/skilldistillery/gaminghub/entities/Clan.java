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
import javax.persistence.Table;

@Entity
@Table(name = "clan")
public class Clan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private Alias alias;
	private boolean enabled;
	private String name;
	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	@ManyToMany
	@JoinTable(
	        name = "alias_clan", 
	        joinColumns = @JoinColumn(name = "clan_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "alias_id") 
	    )
	private List<Alias> alia;
	
	@ManyToMany
	@JoinTable(
	        name = "clan_server", 
	        joinColumns = @JoinColumn(name = "clan_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "server_id") 
	    )
	private List<Server> servers;
	@ManyToMany
	@JoinTable(
	        name = "clan_game", 
	        joinColumns = @JoinColumn(name = "clan_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "game_id") 
	    )
	private List<Game> games;
	
	public Clan() {
		super();
	}

	public Clan(int id, Alias alias, boolean enabled, String name, String description, String imageUrl,
			LocalDateTime created, LocalDateTime updated) {
		super();
		this.id = id;
		this.alias = alias;
		this.enabled = enabled;
		this.name = name;
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

	public Alias getAlias() {
		return alias;
	}

	public void setAlias(Alias alias) {
		this.alias = alias;
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
	
	public List<Alias> getAlia() {
		return alia;
	}

	public void setAlia(List<Alias> alia) {
		this.alia = alia;
	}
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
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
		Clan other = (Clan) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Clan [id=" + id + ", alias=" + alias + ", enabled=" + enabled + ", name=" + name + ", description="
				+ description + ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated + "]";
	}

}
