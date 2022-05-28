package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.User;

public interface UserService {
	
	List<User> index ();

	User getUserById(int userId);
	
	User createUser(User user);
	
	User updateUser(User user, int userId);
	
	boolean deleteUser(String username, int userId );
	
	

}