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
import javax.persistence.Table;

@Entity
@Table(name = "server")
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

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

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToMany
	@JoinTable(name = "alias_server", joinColumns = @JoinColumn(name = "server_id"), inverseJoinColumns = @JoinColumn(name = "alias_id"))
	private List<Alias> aliases;

	@ManyToMany
	@JoinTable(name = "clan_server", joinColumns = @JoinColumn(name = "server_id"), inverseJoinColumns = @JoinColumn(name = "clan_id"))
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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
		alias.addServer(this);
	}

	public void removeAlias(Alias alias) {
		if (alias != null) {
			this.aliases.remove(alias);
			alias.removeServer(this);
		}
	}

	public List<Clan> getClans() {
		return clans;
	}

	public void setClans(List<Clan> clans) {
		this.clans = clans;
	}

	public void addClan(Clan clan) {
		if (this.clans == null) {
			this.clans = new ArrayList<>();
		}
		this.clans.add(clan);
		clan.addServer(this);
	}

	public void removeClan(Clan clan) {
		if (clan != null) {
			this.clans.remove(clan);
			clan.removeServer(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(ip);
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
		return Objects.equals(ip, other.ip);
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", enabled=" + enabled + ", name=" + name + ", type=" + type + ", ip=" + ip
				+ ", url=" + url + ", password=" + password + ", capacity=" + capacity + ", description=" + description
				+ ", created=" + created + ", updated=" + updated + "]";
	}

}
