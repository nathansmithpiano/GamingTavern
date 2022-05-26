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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "server")
public class Server {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game games;
	private boolean enabled;
	private String name;
	private String type;
	private String ip;
	private String url;
	private String password;
	private int capacity;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;
	
	@ManyToMany
	@JoinTable(
	        name = "alias_server", 
	        joinColumns = @JoinColumn(name = "server_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "alias_id") 
	    )
	private List<Alias> alias;
	@ManyToMany
	@JoinTable(
	        name = "clan_server", 
	        joinColumns = @JoinColumn(name = "server_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "clan_id") 
	    )
	private List<Clan> clans;
	
	public Server() {
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Alias> getAlias() {
		return alias;
	}

	public void setAlias(List<Alias> alias) {
		this.alias = alias;
	}
	
	public List<Clan> getClans() {
		return clans;
	}

	public void setClans(List<Clan> clans) {
		this.clans = clans;
	}
	
	public Game getGames() {
		return games;
	}

	public void setGames(Game games) {
		this.games = games;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, capacity, created, description, enabled, id, ip, name, password, type, updated, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		return Objects.equals(alias, other.alias) && capacity == other.capacity
				&& Objects.equals(created, other.created) && Objects.equals(description, other.description)
				&& enabled == other.enabled && id == other.id && Objects.equals(ip, other.ip)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(type, other.type) && Objects.equals(updated, other.updated)
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", enabled=" + enabled + ", name=" + name + ", type=" + type + ", ip=" + ip
				+ ", url=" + url + ", password=" + password + ", capacity=" + capacity + ", description=" + description
				+ ", created=" + created + ", updated=" + updated + ", alias=" + alias + "]";
	}
	
	
	
}
