package com.skilldistillery.gaminghub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	List<Chat> findByAllUsersUsername(String username);

}
