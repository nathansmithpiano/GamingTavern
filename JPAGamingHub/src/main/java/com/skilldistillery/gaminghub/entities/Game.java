package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.List;

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

	public List<Alias> getAlias() {
		return alias;
	}

	public void setAlias(List<Alias> alias) {
		this.alias = alias;
	}
	
	

	
}
