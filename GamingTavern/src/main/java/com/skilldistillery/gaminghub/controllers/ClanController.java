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

import com.skilldistillery.gaminghub.entities.Alias;
import com.skilldistillery.gaminghub.entities.Clan;
import com.skilldistillery.gaminghub.repositories.ClanInformationDTO;
import com.skilldistillery.gaminghub.services.AliasService;
import com.skilldistillery.gaminghub.services.ClanService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class ClanController {

	@Autowired
	private ClanService clanSvc;

	@Autowired
	private AliasService aliasSvc;
	
	@GetMapping("clans/data")
	public List<ClanInformationDTO> data(){
		return clanSvc.getClanData();
	}

	@GetMapping("clans")
	public List<Clan> index(Principal principal) {
		List<Clan> clans = clanSvc.index();
		return clans;
	}

	@GetMapping("clans/{clanId}")
	public Clan show(Principal principal, HttpServletResponse resp, @PathVariable int clanId) {
		Clan clan = clanSvc.getClanById(clanId);
		if (clan == null) {
			resp.setStatus(404);
		}
		return clan;
	}
	
	@GetMapping("name/{name}")
	public String getClanByName(Principal principal, HttpServletResponse resp, @PathVariable String name) {
		Clan clan = clanSvc.getClanByName(name);
		if (clan == null) {
			resp.setStatus(404);
		}
		return clan.getName();
	}

	@PostMapping("clans")
	public Clan create(@RequestBody Clan clan, Principal principal, 
			HttpServletResponse resp) {
		Alias alias = aliasSvc.getAliasById(clan.getCreatorAlias().getId());
		if (alias == null) {
			resp.setStatus(404);
			return null;
		}
		clan.setCreatorAlias(alias);
		return clanSvc.createClan(clan);

	}

	@PutMapping("clans/{clanId}")
	public Clan update(@RequestBody Clan clans, @PathVariable int clanId, Principal principal) {
		return clanSvc.updateClan(clans, clanId);
	}

	@DeleteMapping("clans/{clanId}")
	public void destroy(HttpServletResponse resp, @PathVariable int clanId, Principal principal) {
		if (clanSvc.deleteClan(clanId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
