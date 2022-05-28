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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Game;
import com.skilldistillery.gaminghub.entities.Server;
import com.skilldistillery.gaminghub.services.GameService;


@RestController
@CrossOrigin({ "*", "http://localhost" })
public class GameController {

	@Autowired
	private GameService gameSvc;

	@GetMapping("games")
	public List<Game> index(Principal principal) {
		List<Game> games = gameSvc.index();
		return games;
	}

	@GetMapping("games/{gameId}")
	public Game show(Principal principal, HttpServletResponse resp, @PathVariable int gameId) {
		Game game = gameSvc.getGameById(gameId);
		if (game == null) {
			resp.setStatus(404);
		}
		return game;
	}

	@PostMapping("games")
	public Game create(@RequestBody Game game, Principal principal) {
		return gameSvc.createGame(game);
	}

	@PutMapping("games/{gameId}")
	public Game update(@RequestBody Game game, @PathVariable int gameId, Principal principal) {
		return gameSvc.updateGame(game, gameId);
	}

	@DeleteMapping("games/{gameId}")
	public void destroy(HttpServletResponse resp, @PathVariable int gameId, Principal principal) {
		Game game = gameSvc.getGameById(gameId);
		if(game != null && game.getServers()!= null && game.getServers().size()>0) {
			for(Server server: game.getServers()) {
				if(server.getGame()!= null && server.getGame().getId()== gameId) {
					server.setGame(null);
					game.removeServer(server);
				}
			}
		}
		if (gameSvc.deleteGame(gameId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
