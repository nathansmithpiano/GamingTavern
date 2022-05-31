package com.skilldistillery.gaminghub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Equipment;
import com.skilldistillery.gaminghub.repositories.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipRepo;

	@Override
	public List<Equipment> index() {
		return equipRepo.findAll();
	}

	@Override
	public Equipment getEquipmentById(int equipmentId) {
		Optional<Equipment> op = equipRepo.findById(equipmentId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

}
