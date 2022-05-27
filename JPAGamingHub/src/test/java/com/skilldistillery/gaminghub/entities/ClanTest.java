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
		
	}
	
//	// MANY TO MANY RELATIONSHIP TEMPLATE
//	@Test
//	@DisplayName("Test clan server")
//	void test_clan_server() {
//	
//		// find example with more than one in list
//		server = em.find(Server.class, 15);
//		assertNotNull(server);
//		assertNotNull(server.getClans());
//		assertTrue(server.getClans().size() > 0);
//	
//		// test both sides and no duplicates
//		int expectedMatches = server.getClans().size();
//		int matches = 0;
//	
//		// each of server's clans
//		for(Clan clan : server.getClans()) {
//			// each of the server's clan's servers
//			for(Server clanServer : clan.getServers()) {
//				// verify valid data
//				if(clanServer.getName().equals(server.getName())) {
//					matches++;
//				}
//			}
//		}
//		
//		assertEquals(expectedMatches, matches);
//	}
//	
//	// ONE TO MANY RELATIONSHIP TEMPLATE
//	@DisplayName("User 1:m Meetup")
//	@Test
//	void test_user_meetup() {
//	
//		// find example with more than one in list
//		SELECT * from meetup WHERE user_id=398;
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		| id | timezone_id | user_id | name         | date                | capacity | description | created             | updated             |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		|  1 |           8 |     398 | Free for all | 2022-05-03 20:00:00 |       36 |             | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		user = em.find(User.class, 398);
//		assertNotNull(user);
//		assertNotNull(user.getMeetups());
//		assertTrue(user.getMeetups().size() > 0);
//	
//		// test both sides and no duplicates
//		int matches = 0;
//		int expectedMatches = user.getMeetups().size();
//	
//		each of the user's meetups
//		for(Meetup meetup: user.getMeetups()) {
//			// verify valid data
//			if(meetup.getUser().getUsername().equals(user.getUsername())) {
//				matches++;
//			}
//		}
//	
//		assertEquals(expectedMatches, matches);
//	}
	

}
