package com.skilldistillery.gaminghub.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rating")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "rating")
	private List<Game> games;

	public Rating() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {
		if (this.games == null) {
			this.games = new ArrayList<>();
		}
		this.games.add(game);
		game.setRating(this);
	}

	public void removeGame(Game game) {
		if (game != null) {
			this.games.remove(game);
			game.setRating(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
