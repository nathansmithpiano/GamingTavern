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

@Entity
public class Platform {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean enabled;
	private String name;
	private String type;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;
	@ManyToMany
	@JoinTable(
	        name = "alias_platform", 
	        joinColumns = @JoinColumn(name = "alias_id") , 
	        inverseJoinColumns =  @JoinColumn(name = "platform_id") 
	    )
	private List<Alias> alias;
	
	public Platform() {
		super();
	}

}
