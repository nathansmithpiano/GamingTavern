package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Alias;

public interface AliasService {

	Alias getAliasById(int aliasId);

	Alias updateAlias(String user, Alias alias, int aliasId);

	Alias createAlias(Alias alias);

	boolean deleteAlias(String username, int aliasId);

}
