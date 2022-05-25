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

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="rating_id")
	private int ratingId;
	
	private boolean enabled;
	
	private String name;
	
	private String studio;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private String url;
	
	@ManyToMany
	@JoinTable(name = "meetup_game",
	joinColumns =  @JoinColumn(name = "game_id"),
	inverseJoinColumns =  @JoinColumn(name = "meetup_id") 
							)
	private List<Meetup> meetups;
	
	
	@ManyToMany
	@JoinTable(
	        name = "alias_game", 
	        joinColumns = @JoinColumn(name = "game_id"), 
	        inverseJoinColumns =  @JoinColumn(name = "alias_id") 
	    )
	private List<Alias> alias;

	public Game() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
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

	public List<Meetup> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meetup> meetups) {
		this.meetups = meetups;
	}

	public List<Alias> getAlias() {
		return alias;
	}

	public void setAlias(List<Alias> alias) {
		this.alias = alias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, created, enabled, id, imageUrl, meetups, name, ratingId, studio, updated, url);
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
		return Objects.equals(alias, other.alias) && Objects.equals(created, other.created) && enabled == other.enabled
				&& id == other.id && Objects.equals(imageUrl, other.imageUrl) && Objects.equals(meetups, other.meetups)
				&& Objects.equals(name, other.name) && ratingId == other.ratingId
				&& Objects.equals(studio, other.studio) && Objects.equals(updated, other.updated)
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", ratingId=" + ratingId + ", enabled=" + enabled + ", name=" + name + ", studio="
				+ studio + ", created=" + created + ", updated=" + updated + ", imageUrl=" + imageUrl + ", url=" + url
				+ ", meetups=" + meetups + ", alias=" + alias + "]";
	}

	
	
	

	
}
