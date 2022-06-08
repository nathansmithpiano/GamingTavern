package com.skilldistillery.gaminghub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.gaminghub.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	@Query("SELECT username FROM User u")
	List<String> findAllUsernames();

}
