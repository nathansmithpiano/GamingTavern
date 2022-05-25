package com.skilldistillery.gaminghub.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endorsement")
public class Endorsement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "image_url")
	private String imageUrl;
	private LocalDateTime created;
	private LocalDateTime updated;

	public Endorsement() {
		super();
	}

	public Endorsement(int id, String name, String imageUrl, LocalDateTime created, LocalDateTime updated) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.created = created;
		this.updated = updated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
		Endorsement other = (Endorsement) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Endorsement [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", created=" + created
				+ ", updated=" + updated + "]";
	}

}
