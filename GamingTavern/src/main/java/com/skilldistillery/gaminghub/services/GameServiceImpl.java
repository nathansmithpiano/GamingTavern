package com.skilldistillery.gaminghub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.gaminghub.entities.Game;
import com.skilldistillery.gaminghub.repositories.GameRepository;


public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;
	

	@Override
	public Game getGameById(int gameId) {
		Optional<Game> op = gameRepo.findById(gameId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Game createGame(Game game) {
		return gameRepo.saveAndFlush(game);
	}

	@Override
	public Game updateGame(String name, Game game, int gameId) {
		Optional<Game> op = gameRepo.findById(gameId);
		if (op.isPresent()) {
			Game result = op.get();
			if (result.getName().equals(name)) {
				game.setId(gameId);
				return gameRepo.saveAndFlush(game);
			}
			}

			return null;
	}

	@Override
	public boolean deleteGame(String name, int gameId) {
		Optional<Game> op = gameRepo.findById(gameId);
		if(op.isPresent()) {
			Game result = op.get();
			if(result.getName().equals(name)) {
				gameRepo.deleteById(gameId);
				op = gameRepo.findById(gameId);
				return !op.isPresent();
			}
		}
		return false;
	}
		
	

}
