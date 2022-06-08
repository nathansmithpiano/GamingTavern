package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
