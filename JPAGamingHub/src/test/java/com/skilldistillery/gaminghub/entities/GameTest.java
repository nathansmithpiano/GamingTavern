package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
