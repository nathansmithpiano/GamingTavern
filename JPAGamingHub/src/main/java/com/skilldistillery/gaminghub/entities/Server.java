package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.List;

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
	
}
