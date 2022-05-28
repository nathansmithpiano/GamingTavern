package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Clan;

public interface ClanService {
	
	List<Clan> index();
	
	Clan getClanById(int clanId);
	
	Clan createClan(Clan clan);
	
	Clan updateClan(Clan clan, int clanId);
	
	boolean deleteClan(int clanId);


}
