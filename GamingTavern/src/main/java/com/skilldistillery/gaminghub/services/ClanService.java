package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Clan;
import com.skilldistillery.gaminghub.repositories.ClanInformationDTO;

public interface ClanService {
	
	List<Clan> index();
	
	Clan getClanById(int clanId);
	
	Clan getClanByName(String name);
	
	Clan createClan(Clan clan);
	
	Clan updateClan(Clan clan, int clanId);
	
	boolean deleteClan(int clanId);
	
	List<ClanInformationDTO> getClanData();


}
