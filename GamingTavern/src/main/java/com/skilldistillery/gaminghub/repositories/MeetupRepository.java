package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Meetup;

public interface MeetupRepository extends JpaRepository<Meetup, Integer> {

}
