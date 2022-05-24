package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String abbreviation;
	private String city;
	
	@Column(name = "state_province")
	private String stateProvince;
	
	private Integer zip;
	private String country;
	private String timezone;
	private LocalDateTime created;
	private LocalDateTime updated;

}
