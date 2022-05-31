package com.skilldistillery.gaminghub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Alias;

public interface AliasRepository extends JpaRepository<Alias, Integer> {
	
	List<Alias> findByUserUsername(String username);

}
