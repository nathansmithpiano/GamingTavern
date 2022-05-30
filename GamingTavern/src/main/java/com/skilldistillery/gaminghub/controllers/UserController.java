package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
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

	@GetMapping("users/{userId}")
	public User show(Principal principal, HttpServletResponse resp, @PathVariable int userId) {
		User user = userSvc.getUserById(userId);
		if (user == null) {
			resp.setStatus(404);
		}
		return user;
	}
	
	@GetMapping("username/{username}")
	public String getUserByUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		User user = userSvc.getUserByUsername(username);
		if (user == null) {
			resp.setStatus(404);
		}
		return user.getUsername();
	}

	@PostMapping("users")
	public User create(@RequestBody User user, Principal principal) {
		return userSvc.createUser(user);
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
