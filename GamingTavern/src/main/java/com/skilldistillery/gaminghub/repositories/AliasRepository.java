package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Alias;
import com.skilldistillery.gaminghub.entities.Meetup;

public interface AliasRepository extends JpaRepository<Alias, Integer> {

}
