package com.skilldistillery.gaminghub.entities;

import java.time.LocalDate;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clan")
public class Clan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnoreProperties({"User","clans","platforms", "games", "servers", "meetups"})
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private Alias creatorAlias;

	private boolean enabled;
	
	private String name;
	
	private String description;

	@Column(name = "image_url")
	private String imageUrl;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate created;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate updated;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "alias_clan", joinColumns = @JoinColumn(name = "clan_id"), inverseJoinColumns = @JoinColumn(name = "alias_id"))
	private List<Alias> aliases;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "clan_server", joinColumns = @JoinColumn(name = "clan_id"), inverseJoinColumns = @JoinColumn(name = "server_id"))
	private List<Server> servers;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "clan_game", joinColumns = @JoinColumn(name = "clan_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;
	

	public Clan() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alias getCreatorAlias() {
		return creatorAlias;
	}

	public void setCreatorAlias(Alias alias) {
		this.creatorAlias = alias;
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

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDate updated) {
		this.updated = updated;
	}

	public List<Alias> getAliases() {
		return aliases;
	}

	public void addAlias(Alias alias) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<>();
		}
		this.aliases.add(alias);
		alias.addClan(this);
	}

	public void removeAlias(Alias alias) {
		if (alias != null) {
			this.aliases.remove(alias);
			alias.removeClan(this);
		}
	}

	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public void addServer(Server server) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
		}
		this.servers.add(server);
		server.addClan(this);
	}

	public void removeServer(Server server) {
		if (server != null) {
			this.servers.remove(server);
			server.removeClan(this);
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
		game.addClan(this);
	}

	public void removeGame(Game game) {
		if (game != null) {
			this.games.remove(game);
			game.removeClan(this);
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
		Clan other = (Clan) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Clan [id=" + id + ", alias=" + creatorAlias + ", enabled=" + enabled + ", name=" + name + ", description="
				+ description + ", imageUrl=" + imageUrl + ", created=" + created + ", updated=" + updated
				+ ", aliases=" + aliases + ", servers=" + servers + ", games=" + games + "]";
	}

}
