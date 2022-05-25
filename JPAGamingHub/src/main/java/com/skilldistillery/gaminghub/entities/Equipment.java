package com.skilldistillery.gaminghub.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String model;
	private String description;
	
	@ManyToMany
	@JoinTable(name = "user_equipment",
	joinColumns = @JoinColumn(name = "equipment_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id") 
			)
	private List<User> users;

	public Equipment() {
		super();
	}

	public void addUser(User user) {
		if (users == null) {
		users = new ArrayList<>();
		}
		if (user != null) {
		users.add(user);
		user.addEquipment(this);
		}
		}

		public void removeUser(User user) {
		if (user != null) {
		users.remove(user);
		user.removeEquipment(this);
		}
}
}
