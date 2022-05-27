package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.User;

public interface UserService {

	User getUserById(int userId);
	
	User createUser(User user);
	
	User updateUser(String username, User user, int userId);
	
	boolean deleteUser(String username, int userId );
	
	

}