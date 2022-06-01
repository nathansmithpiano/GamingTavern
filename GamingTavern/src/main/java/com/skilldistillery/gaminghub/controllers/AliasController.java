package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Alias;
import com.skilldistillery.gaminghub.services.AliasService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
@RequestMapping("api")
public class AliasController {

	@Autowired
	private AliasService aliasSvc;

	@GetMapping("aliases")
	public List<Alias> index(Principal principal) {
		List<Alias> aliases = aliasSvc.index();
		return aliases;
	}

	@GetMapping("aliases/{aliasId}")
	public Alias show(Principal principal, HttpServletResponse resp, @PathVariable int aliasId) {
		Alias alias = aliasSvc.getAliasById(aliasId);
		if (alias == null) {
			resp.setStatus(404);
		}
		return alias;
	}

	
	@GetMapping("aliases/user/{username}")
	public List<Alias> getbyUsername(Principal principal, HttpServletResponse resp, @PathVariable String username) {
		List<Alias> aliases = aliasSvc.getAliasesByUsername(username);
		if (aliases == null) {
			resp.setStatus(404);
		}
		return aliases;
	}

}

