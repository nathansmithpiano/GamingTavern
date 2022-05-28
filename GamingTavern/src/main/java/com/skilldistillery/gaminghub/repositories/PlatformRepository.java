package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Platform;
import com.skilldistillery.gaminghub.entities.User;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{

	Platform findByName(String Name);
}
