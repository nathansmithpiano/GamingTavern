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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean enabled;

	private String name;

	private String studio;

	private LocalDateTime created;

	private LocalDateTime updated;

	@Column(name = "image_url")
	private String imageUrl;

	private String url;

	@JsonIgnore
	@OneToMany(mappedBy = "game")
	private List<Server> servers;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "rating_id")
	private Rating rating;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "meetup_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "meetup_id"))
	private List<Meetup> meetups;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "alias_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "alias_id"))
	private List<Alias> aliases;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "clan_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "clan_id"))
	private List<Clan> clans;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "platform_game", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
	private List<Platform> platforms;

	public Game() {
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

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public void addMeetup(Meetup meetup) {
		if (this.meetups == null) {
			this.meetups = new ArrayList<>();
		}
		this.meetups.add(meetup);
		meetup.addGame(this);
	}

	public void removeMeetup(Meetup meetup) {
		if (meetup != null) {
			this.meetups.remove(meetup);
			meetup.removeGame(this);
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
		alias.addGame(this);
	}

	public void removeAlias(Alias alias) {
		if (alias != null) {
			this.aliases.remove(alias);
			alias.removeGame(this);
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
		clan.addGame(this);
	}

	public void removeClan(Clan clan) {
		if (clan != null) {
			this.clans.remove(clan);
			clan.removeGame(this);
		}
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
		server.setGame(this);
	}

	public void removeServer(Server server) {
		if (server != null) {
			this.servers.remove(server);
			server.setGame(null);
		}
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public void addPlatform(Platform platform) {
		if (this.platforms == null) {
			this.platforms = new ArrayList<>();
		}
		this.platforms.add(platform);
		platform.addGame(this);
	}

	public void removePlatform(Platform platform) {
		if (platform != null) {
			this.platforms.remove(platform);
			platform.removeGame(this);
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
		Game other = (Game) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", enabled=" + enabled + ", name=" + name + ", studio=" + studio + ", created="
				+ created + ", updated=" + updated + ", imageUrl=" + imageUrl + ", url=" + url + ", rating=" + rating
				+ "]";
	}

}
