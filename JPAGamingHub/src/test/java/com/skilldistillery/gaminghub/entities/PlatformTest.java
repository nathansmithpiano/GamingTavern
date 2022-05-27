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

class PlatformTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Platform platform;

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
		platform = em.find(Platform.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Platform mapping")
	void test_platform_mapping() {
		assertNotNull(platform);
		assertEquals("Steam", platform.getName());
		assertEquals("Library", platform.getType());
		
		//+----+---------+-------+---------+------------------------+-----------+---------------------+---------------------+
		//| id | enabled | name  | type    | description            | image_url | created             | updated             |
		//+----+---------+-------+---------+------------------------+-----------+---------------------+---------------------+
		//|  1 |       1 | Steam | Library | Game Store and Library |           | 2020-04-10 18:30:00 | 2020-04-11 18:30:00 |
		//+----+---------+-------+---------+------------------------+-----------+---------------------+---------------------+

	}
	
	@DisplayName("Platform m:m Game")
	@Test
	void test_platform_game_mapping() {
		
//		SELECT platform_id, COUNT(*) FROM platform_game GROUP BY platform_id ORDER BY COUNT(*) DESC;
//		+-------------+----------+
//		| platform_id | COUNT(*) |
//		+-------------+----------+
//		|           2 |       10 |
		platform = em.find(Platform.class, 2);
		assertNotNull(platform);
		assertNotNull(platform.getGames());
		assertTrue(platform.getGames().size() > 0);
		
		// test both sides and no duplicates
		int matches = 0;
		int expectedMatches = platform.getGames().size();
		
		// each of the platform's games
		for (Game game : platform.getGames()) {
			// each of the game's platforms
			for (Platform gamePlatform : game.getPlatforms()) {
				if (gamePlatform.getName().equals(platform.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	}
		
}
