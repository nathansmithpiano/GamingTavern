package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "server")
public class Server {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Game id
	
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
	
	public Server() {
		super();
	}
	
}
