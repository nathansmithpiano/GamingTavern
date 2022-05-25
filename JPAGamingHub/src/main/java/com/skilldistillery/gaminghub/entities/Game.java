package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Game() {
		super();
	}

	
}
