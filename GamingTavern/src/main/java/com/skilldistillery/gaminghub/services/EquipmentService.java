package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Equipment;

public interface EquipmentService {

	List<Equipment> index();

	Equipment getEquipmentById(int equipmentId);
}
