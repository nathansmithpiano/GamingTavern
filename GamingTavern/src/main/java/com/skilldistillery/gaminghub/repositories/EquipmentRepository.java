package com.skilldistillery.gaminghub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gaminghub.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

	Equipment findByName(String name);
}
