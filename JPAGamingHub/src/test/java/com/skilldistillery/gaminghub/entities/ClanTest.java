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

class ClanTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Clan clan;

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
		clan = em.find(Clan.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Clan mapping")
	void test_clan_mapping() {
		assertNotNull(clan);
		assertEquals("Axon", clan.getName());
		assertEquals("Chads and Chaddettes", clan.getDescription());
		
		//+----+------------+---------+------+----------------------+---------------------+---------------------+
		//| id | creator_id | enabled | name | description          | created             | updated             |
		//+----+------------+---------+------+----------------------+---------------------+---------------------+
		//|  1 |          1 |       0 | Axon | Chads and Chaddettes | 2022-05-25 18:30:00 | 2022-05-25 18:30:00 |
		//+----+------------+---------+------+----------------------+---------------------+---------------------+
	
	}
	
//	SELECT clan_id, COUNT(*) FROM clan_game GROUP BY clan_id ORDER BY COUNT(*) DESC;
//	+---------+----------+
//	| clan_id | COUNT(*) |
//	+---------+----------+
//	|      15 |        1 |
	
	@Test
	@DisplayName("Test Clan Game ")
	void test_clan_game() {
		// find example with more than one in list
		clan = em.find(Clan.class, 15);
		assertNotNull(clan);
		assertNotNull(clan.getGames());
		assertTrue(clan.getGames().size() > 0);
//	
		// test both sides and no duplicates
		int expectedMatches = clan.getGames().size();
		int matches = 0;
	
		// each of server's clans
		for(Game game : clan.getGames()) {
			// each of the server's clan's servers
			for(Clan gameClan : game.getClans()) {
				// verify valid data
				if(gameClan.getName().equals(clan.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	
	}
}


