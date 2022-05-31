package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Equipment;
import com.skilldistillery.gaminghub.services.EquipmentService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class EquipmentController {

	@Autowired
	EquipmentService equipSvc;

	@GetMapping("equipments")
	public List<Equipment> index(Principal principal) {
		List<Equipment> equipments = equipSvc.index();
		return equipments;
	}

	@GetMapping("equipments/{equipmentId}")
	public Equipment show(Principal principal, HttpServletResponse resp, @PathVariable int equipmentId) {
		Equipment equipment = equipSvc.getEquipmentById(equipmentId);
		if (equipment == null) {
			resp.setStatus(404);
		}
		return equipment;
	}
}
