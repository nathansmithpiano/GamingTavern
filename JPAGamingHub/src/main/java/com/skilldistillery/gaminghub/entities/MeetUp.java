package com.skilldistillery.gaminghub.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meetup")
public class MeetUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate date;
	private String timezone;
	private Integer capacity;
	private String description;
	private LocalDateTime created;
	private LocalDateTime updated;

}
