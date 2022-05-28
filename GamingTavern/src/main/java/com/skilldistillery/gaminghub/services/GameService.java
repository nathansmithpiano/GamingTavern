package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Game;

public interface GameService {
	
	Game getGameById(int gameId);
	
	Game createGame(Game game);
	
	Game updateGame(String name, Game game, int gameId);
	
	boolean deleteGame(String name, int gameId);

}
