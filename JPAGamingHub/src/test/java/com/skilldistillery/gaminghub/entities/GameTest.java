package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Game game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAGamingHub");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		game = em.find(Game.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Game mapping")
	void test_game_mapping() {
		
//		SELECT * FROM game WHERE id=1;
//		+----+-----------+---------+----------------------------------+--------+-------------------------------------------------------------------------------+------------------------------------------------------------------------+---------------------+---------------------+
//		| id | rating_id | enabled | name                             | studio | image_url                                                                     | url                                                                    | created             | updated             |
//		+----+-----------+---------+----------------------------------+--------+-------------------------------------------------------------------------------+------------------------------------------------------------------------+---------------------+---------------------+
//		|  1 |         3 |       1 | Counter-Strike: Global Offensive | Valve  | https://cdn.cloudflare.steamstatic.com/steam/apps/730/header.jpg?t=1641233427 | https://store.steampowered.com/app/730/CounterStrike_Global_Offensive/ | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+-----------+---------+----------------------------------+--------+-------------------------------------------------------------------------------+------------------------------------------------------------------------+---------------------+---------------------+
		
		assertNotNull(game);
		assertNotNull(game.getName());
		assertEquals("Counter-Strike: Global Offensive", game.getName());
	}
	
	@Test
	@DisplayName("Testing ManyToMany game(Many) ---> Alias(Many)")
	void test2() {
		game = em.find(Game.class, 1);
		assertNotNull(game);
		assertNotNull(game.getAliases());
		assertTrue(game.getAliases().size() > 0);
		assertEquals(1, game.getAliases().get(0)
				.getGames().get(0).getId());
		// test both sides and no duplicates
		int expectedMatches = game.getAliases().size();
		int matches = 0;
		// s = game c = alias
		// each of server's clans
		for(Alias alias : game.getAliases()) {
			// each of the server's clan's servers
			for(Game aliasGame : alias.getGames()) {
				// verify valid data
				if(aliasGame.getName().equals(game.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	}
	
	@Test
	@DisplayName("Testing ManyToMany game(Many) ---> meetup(Many)")
	void test3() {
		game = em.find(Game.class, 1);
		assertNotNull(game);
		assertNotNull(game.getMeetups());
		assertTrue(game.getMeetups().size() > 0);
		assertEquals(1, game.getMeetups().get(0).getGames().get(0).getId());
		
		// test both sides and no duplicates
		int expectedMatches = game.getMeetups().size();
		int matches = 0;
	
		// each of server's clans
		for(Meetup meetup : game.getMeetups()) {
			// each of the server's clan's servers
			for(Meetup gameMeetup : game.getMeetups()) {
				// verify valid data
				if(gameMeetup.getName().equals(meetup.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	}
	
	@Test
	@DisplayName("Testing ManyToMany game(Many) ---> clans(Many)")
	void test4() {
		game = em.find(Game.class, 1);
		assertNotNull(game);
		assertNotNull(game.getClans());
		assertTrue(game.getClans().size() > 0);
		assertEquals(1, game.getClans().get(0).getGames().get(0).getId());
		
		// test both sides and no duplicates
		int expectedMatches = game.getClans().size();
		int matches = 0;
	
		// each of server's clans
		for(Clan clan : game.getClans()) {
			// each of the server's clan's servers
			for(Game clanGame : clan.getGames()) {
				// verify valid data
				if(clanGame.getName().equals(game.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	}
	
	@Test
	@DisplayName("Testing OneToMany game(one) ----> servers(many) ")
	void test5() {
		assertNotNull(game);
		assertNotNull(game.getServers());		
		assertTrue(game.getServers().size()>0);
		// test both sides and no duplicates
		int matches = 0;
		int expectedMatches = game.getServers().size();
	
//		each of the user's meetups
		for(Server servers: game.getServers()) {
			// verify valid data
			if(servers.getGame().getName().equals(game.getName())) {
				matches++;
			}
		}
	
		assertEquals(expectedMatches, matches);
	}
		
	}
