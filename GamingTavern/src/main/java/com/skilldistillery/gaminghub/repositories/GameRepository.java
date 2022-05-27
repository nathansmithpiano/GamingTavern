package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	
	Game findByName(String name);

}
