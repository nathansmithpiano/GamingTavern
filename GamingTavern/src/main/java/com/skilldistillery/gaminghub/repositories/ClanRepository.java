package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Clan;


public interface ClanRepository extends JpaRepository<Clan, Integer> {
	
	Clan findByName(String name);

}
