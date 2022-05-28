package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Game;

public interface GameService {
	
	List<Game> index();
	
	Game getGameById(int gameId);
	
	Game createGame(Game game);
	
	Game updateGame(Game game, int gameId);
	
	boolean deleteGame(int gameId);

}
