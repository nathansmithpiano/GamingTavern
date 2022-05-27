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
	}

	@Test
	@DisplayName("Platform MTM games mapping")
	void test2() {
		platform = em.find(Platform.class, 1);
		assertNotNull(platform);
		assertNotNull(platform.getGames());
		assertTrue(platform.getGames().size() > 0);
		assertEquals(1, platform.getGames().get(0).getMeetups().get(0).getId());
		// test both sides and no duplicates
		int expectedMatches = platform.getGames().size();
		int matches = 0;

		// each of server's clans m = s c = game
		for (Game games : platform.getGames()) {
			// each of the server's clan's servers
			for (Platform gamePlatform : games.getPlatform()) {
				// verify valid data
				if (gamePlatform.getName().equals(platform.getName())) {
					matches++;
				}
			}
		}
		assertEquals(expectedMatches, matches);
	}

}
