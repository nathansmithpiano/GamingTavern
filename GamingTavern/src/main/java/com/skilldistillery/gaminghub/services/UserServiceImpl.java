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
	public User getUserById(int userId) {
		Optional<User> op = userRepo.findById(userId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	

	@Override
	public User updateUser(String username, User user, int userId) {
		Optional<User> op = userRepo.findById(userId);
		if (op.isPresent()) {
			User result = op.get();
			if (result.getUsername().equals(username)) {
				user.setId(userId);
				return userRepo.saveAndFlush(user);

			}
		}

		return null;
	}

	@Override
	public boolean deleteUser(String username, int userId) {
		Optional<User> op = userRepo.findById(userId);
		if(op.isPresent()) {
			User result = op.get();
			if(result.getUsername().equals(username)) {
				userRepo.deleteById(userId);
				op = userRepo.findById(userId);
				return !op.isPresent();
			}
		}
		return false;
	}

}
