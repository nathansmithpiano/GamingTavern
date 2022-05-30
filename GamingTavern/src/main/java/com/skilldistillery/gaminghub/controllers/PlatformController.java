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

import com.skilldistillery.gaminghub.entities.Platform;
import com.skilldistillery.gaminghub.services.PlatformService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class PlatformController {

	@Autowired
	private PlatformService platSvc;

	@GetMapping("platforms")
	public List<Platform> index(Principal principal) {
		List<Platform> platforms = platSvc.index();
		return platforms;
	}

	@GetMapping("platforms/{platformId}")
	public Platform show(Principal prinipal, HttpServletResponse resp, @PathVariable int platformId) {
		Platform platform = platSvc.getPlatformById(platformId);
		if (platform == null) {
			resp.setStatus(404);
		}
		return platform;
	}

}
