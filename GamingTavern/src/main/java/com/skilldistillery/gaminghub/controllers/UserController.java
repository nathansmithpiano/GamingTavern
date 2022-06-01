package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Alias;
import com.skilldistillery.gaminghub.entities.Game;
import com.skilldistillery.gaminghub.entities.Location;
import com.skilldistillery.gaminghub.entities.Meetup;
import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userSvc;

	// SMOKE TEST ONLY, DELETE/COMMENT OUT LATER
	@GetMapping("test/users/{userId}")
	public User getUserForTest(@PathVariable Integer userId, HttpServletResponse res) {
		User user = userSvc.getUserById(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@GetMapping("users")
	public List<User> index(Principal principal) {
		List<User> users = userSvc.index();
		return users;
	}

	@GetMapping("users/{username}")
	public User getByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
		}
		return user;
	}
	
	@GetMapping("users/{username}/friends")
	public List<User> getFriendsByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
			return null;
		}
		return userSvc.getUserByUsername(username).getFriends();
	}
	
	@GetMapping("users/{username}/blockedusers")
	public List<User> getBlockedUsersByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
			return null;
		}
		return userSvc.getUserByUsername(username).getBlockedUsers();
	}
	
	@GetMapping("users/{username}/games")
	public List<Game> getGamesByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
			return null;
		}
		List<Game> games = new ArrayList<>();
		for (Alias alias : user.getAliases()) {
			for (Game game : alias.getGames()) {
				games.add(game);
			}
		}
		return games;
	}
	
	@GetMapping("users/{username}/locations")
	public List<Location> getLocationsByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
			return null;
		}
		return user.getLocations();
	}
	
	@GetMapping("users/{username}/meetups")
	public List<Meetup> getMeetupsByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
			return null;
		}
		return user.getMeetups();
	}
	
	@PostMapping("users")
	public User create(@RequestBody User user, HttpServletResponse resp, Principal principal) {
		User newUser = userSvc.createUser(user);
		if (newUser == null) {
			resp.setStatus(409);
			return null;
		}
		resp.setStatus(201);
		return newUser;
	}

	@PutMapping("users/{userId}")
	public User update(@RequestBody User user, @PathVariable int userId, Principal principal) {
		return userSvc.updateUser(user, userId);
	}

	@DeleteMapping("users/{userId}")
	public void destroy(HttpServletResponse resp, @PathVariable int userId, Principal principal) {
		if (userSvc.deleteUser(userId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
