package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Alias;

public interface AliasService {

	List<Alias> index();
	
	Alias getAliasById(int aliasId);
	
	List<Alias> index();
	
	List<Alias> getAliasesByUsername(String username);

	Alias updateAlias(String user, Alias alias, int aliasId);

	Alias createAlias(Alias alias);

	boolean deleteAlias(String username, int aliasId);

}
