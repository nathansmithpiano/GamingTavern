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

import com.skilldistillery.gaminghub.entities.Server;
import com.skilldistillery.gaminghub.services.ServerService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
@RequestMapping("api")
public class ServerController {
	
	@Autowired
	private ServerService servSvc;
	
	@GetMapping("servers")
	public List<Server> index(Principal principal){
		List<Server> servers = servSvc.index();
		return servers;
	}
	
	@GetMapping("servers/{serverId}")
	public Server show(Principal principal, HttpServletResponse resp, @PathVariable int serverId){
		Server server = servSvc.getServerById(serverId);
		if(server == null) {
			resp.setStatus(404);
		}
		return server;
	}
	
}
