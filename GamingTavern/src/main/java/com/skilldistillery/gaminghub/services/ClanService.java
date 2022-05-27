package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Clan;

public interface ClanService {
	
	Clan getClanById(int clanId);
	
	Clan createClan(Clan clan);
	
	Clan updateClan(String name, Clan clan, int clanId);
	
	boolean deleteClan(String name, int clanId);


}
