package com.skilldistillery.gaminghub.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Timezone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String abbreviation;

	private Double offset;

	private String locale;

	private String description;
<<<<<<< HEAD
	
	@JsonIgnore
	@OneToMany(mappedBy = "timezone")
	private List<Meetup> meetups;
	
=======

	@JsonIgnore
	@OneToMany(mappedBy = "timezone")
	private List<Meetup> meetups;

>>>>>>> ac5768c592865b39d9a76f056b99d8afbc521c3a
	@JsonIgnore
	@OneToMany(mappedBy = "timezone")
	private List<Location> locations;

	public Timezone() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Double getOffset() {
		return offset;
	}

	public void setOffset(Double offset) {
		this.offset = offset;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		meetup.setTimezone(this);
	}

	public void removeMeetup(Meetup meetup) {
		if (meetup != null) {
			this.meetups.remove(meetup);
//			meetup.setTimezone(null);
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location location) {
		if (this.locations == null) {
			this.locations = new ArrayList<>();
		}
		this.locations.add(location);
		location.setTimezone(this);
	}

	public void removeLocation(Location location) {
		if (location != null) {
			this.locations.remove(location);
//			location.setTimezone(null);
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
		Timezone other = (Timezone) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Timezone [id=" + id + ", abbreviation=" + abbreviation + ", offset=" + offset + ", locale=" + locale
				+ ", description=" + description;
	}

}
