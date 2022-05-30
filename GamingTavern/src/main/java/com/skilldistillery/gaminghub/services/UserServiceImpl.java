package com.skilldistillery.gaminghub.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override

	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int userId) {
		Optional<User> op = userRepo.findById(userId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user, int userId) {
		Optional<User> op = userRepo.findById(userId);
		User users = null;
		if (op.isPresent()) {
			users = op.get();
			users.setEnabled(user.isEnabled());
			users.setRole(user.getRole());
			users.setUsername(user.getUsername());
			users.setPassword(user.getPassword());
			users.setEmail(user.getEmail());
			users.setFirstName(user.getFirstName());
			users.setMiddleName(user.getMiddleName());
			users.setLastName(user.getLastName());
			users.setDescription(user.getDescription());
			users.setImageUrl(user.getImageUrl());
			users.setCreated(user.getCreated());		
			users.setUpdated(user.getUpdated());		
			userRepo.saveAndFlush(users);
		}
		return users;
	}

	@Override
	public boolean deleteUser(int userId) {
		boolean deleted = false;
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			deleted = true;
		}
		return deleted;	}
}
