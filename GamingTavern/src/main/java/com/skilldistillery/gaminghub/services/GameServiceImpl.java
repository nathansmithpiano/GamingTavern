package com.skilldistillery.gaminghub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Game;
import com.skilldistillery.gaminghub.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Override
	public List<Game> index() {
		return gameRepo.findAll();
	}

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
	public Game updateGame(Game game, int gameId) {
		Optional<Game> op = gameRepo.findById(gameId);
		Game games = null;
		if (op.isPresent()) {
			games = op.get();
			games.setRating(game.getRating());
			games.setEnabled(game.isEnabled());
			games.setName(game.getName());
			games.setStudio(game.getStudio());
			games.setImageUrl(game.getImageUrl());
			games.setUrl(game.getUrl());
			games.setCreated(game.getCreated());
			games.setUpdated(game.getUpdated());
			gameRepo.saveAndFlush(games);
		}
		return games;
	}

	@Override
	public boolean deleteGame(int gameId) {
		boolean deleted = false;
		if(gameRepo.existsById(gameId)) {
			gameRepo.deleteById(gameId);
			deleted = true;
		}
		return deleted;	}



		
	

}
