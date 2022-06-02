package com.skilldistillery.gaminghub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

	Equipment findByName(String name);
	List<Equipment> findByUsersUsername(String username);
}
