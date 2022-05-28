package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
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

//	@GetMapping("users")
//	public Set<Users> index(Principal principal) {
//		return userSvc.index(principal.getName());
//	}

	@GetMapping("users/{tid}")
	public User show(Principal principal, HttpServletResponse resp, @PathVariable int userId) {
		User user = userSvc.getUserById(userId);
		if (user == null) {
			resp.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public User create(@RequestBody User user, Principal principal) {
		return userSvc.createUser(user);
	}

	@PutMapping("users/{tid}")
	public User update(@RequestBody User user, @PathVariable int userId, Principal principal) {
		return userSvc.updateUser(principal.getName(), user, userId);
	}

	@DeleteMapping("users/{tid}")
	public void destroy(HttpServletResponse resp, @PathVariable int userId, Principal principal) {
		if (userSvc.deleteUser(principal.getName(), userId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
