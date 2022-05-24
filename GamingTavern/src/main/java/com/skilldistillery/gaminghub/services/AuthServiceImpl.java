package com.skilldistillery.gaminghub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		String unecrypted = user.getPassword();
		String encrypted = encoder.encode(unecrypted);
		if (encrypted != null) {
			user.setPassword(encrypted);
			user.setEnabled(true);
			user.setRole("standard");
			User updatedUser = userRepo.saveAndFlush(user);
			if (updatedUser != null) {
				return user;
			} else {
				System.err.println("AuthServiceImpl register() error - userRepo did not update");
			}
		}
		user.setEnabled(false);
		System.err.println("AuthServiceImpl register() error - encrypted is null");
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
