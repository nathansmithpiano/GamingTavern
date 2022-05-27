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

public class ServerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Server server;

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
		server = em.find(Server.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

//	SELECT name FROM SERVER where id=1;
//	+--------------+
//	| name         |
//	+--------------+
//	| The Gauntlet |
//	+--------------+
	
	@Test
	@DisplayName("Server mapping")
	void test_user_mapping() {
		assertNotNull(server);
		assertEquals("The Gauntlet", server.getName());
		
	}
	
	@Test
	void test_alias_server() {
		assertNotNull(server.getAlias());
		assertEquals("Baya", server.getAlias().get(0).getName());
		assertEquals(1619, server.getAlias().get(0).getUser().getId());
		
//		+----------+-----------+ 
//		| alias_id | server_id |
//		+----------+-----------+
//		|        1 |         1 |
//		+----------+-----------+
		
//		+----+---------+---------+------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//		| id | user_id | enabled | name | description | image_url                                                                          | created             | updated             |
//		+----+---------+---------+------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//		|  1 |    1619 |       1 | Baya |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+---------+---------+------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
		
	}
	@Test
	@DisplayName("Test clan server")
	void test_clan_server() {
		// find example with more than one in list
		server = em.find(Server.class, 15);
		assertNotNull(server);
		assertNotNull(server.getClans());
		assertTrue(server.getClans().size() > 0);
	
		// test both sides and no duplicates
		int expectedMatches = server.getClans().size();
		int matches = 0;
	
		// each of server's clans
		for(Clan clan : server.getClans()) {
			// each of the server's clan's servers
			for(Server clanServer : clan.getServers()) {
				// verify valid data
				if(clanServer.getName().equals(server.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	
		
		
		
//		 SELECT server_id, COUNT(*) FROM clan_server GROUP BY server_id ORDER BY COUNT(*) DESC;
//		 +-----------+----------+
//		 | server_id | COUNT(*) |
//		 +-----------+----------+
//		 |        15 |        1 |
		
//		+---------+-----------+
//		| clan_id | server_id |
//		+---------+-----------+
//		|       1 |         1 |

//		+----+------------+---------+------+----------------------++---------------------+---------------------+
//		| id | creator_id | enabled | name | description          | created             | updated             |
//		+----+------------+---------+------+----------------------++---------------------+---------------------+
//		|  1 |          1 |       0 | Axon | Chads and Chaddettes || 2022-05-25 18:30:00 | 2022-05-25 18:30:00 |
//		+----+------------+---------+------+----------------------+---------------------+----
	}
	
	@Test
		void test_server_game_mapping() {
		assertNotNull(server.getGame());
		assertEquals("Stellaris", server.getGame().getName());
		assertEquals(3, server.getGame().getRatingId());
		
		
		
//		+----+-----------+---------+-----------+----------------------------+----------------------------------------------------------------------------------+----------------------------------------------------------+---------------------+---------------------+----+---------+---------+--------------+------+---------------+------+----------+----------+------------------+---------------------+---------------------+
//		| id | rating_id | enabled | name      | studio                     | image_url                                                                        | url                                                      | created             | updated             | id | game_id | enabled | name         | type | ip            | url  | password | capacity | description      | created             | updated             |
//		+----+-----------+---------+-----------+----------------------------+----------------------------------------------------------------------------------+----------------------------------------------------------+---------------------+---------------------+----+---------+---------+--------------+------+---------------+------+----------+----------+------------------+---------------------+---------------------+
//		| 30 |         3 |       1 | Stellaris | Paradox Development Studio | https://cdn.cloudflare.steamstatic.com/steam/apps/281990/header.jpg?t=1652979714 | https://www.paradoxinteractive.com/games/stellaris/about | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |  1 |      30 |       0 | The Gauntlet | Game | 27.173.141.18 |      | password |       20 | Fraggin n Baggin | 2022-04-10 18:30:00 | 2022-04-10 18:30:00 |
//		+----+-----------+---------+-----------+----------------------------+----------------------------------------------------------------------------------+----------------------------------------------------------+---------------------+---------------------+----------+----------------------------------------------------------------------------------+----------------------------------------------------------+---------------------+---------------------+
//	
	}

	
}
