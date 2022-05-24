package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
