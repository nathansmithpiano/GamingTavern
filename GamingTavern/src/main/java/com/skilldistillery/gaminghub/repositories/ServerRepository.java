package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Server;

public interface ServerRepository extends JpaRepository<Server, Integer> {
	
	Server findByName(String name);


}
