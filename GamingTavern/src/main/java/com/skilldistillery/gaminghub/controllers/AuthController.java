package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost" })

public class AuthController {

	@Autowired
	private AuthService authSvc;

	@PostMapping(path = "/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		if (user == null) {
			res.setStatus(400);
		}
		user = authSvc.register(user);
		return user;
	}
	
	@GetMapping(path = "/authenticate")
	public User authenticate(Principal principal) {
		return authSvc.getUserByUsername(principal.getName());
	}

}
