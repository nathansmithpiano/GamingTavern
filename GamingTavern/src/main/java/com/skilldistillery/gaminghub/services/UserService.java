package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.User;

public interface UserService {
	
	List<User> index ();

	User getUserById(int userId);
	
	User getUserByUsername(String username);
	
	User createUser(User user);
	
	User updateUser(User user, int userId);
	
	boolean deleteUser(int userId );
	
	
	

}