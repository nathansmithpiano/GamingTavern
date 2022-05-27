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
	@Test
	void test_alias_platform_mapping() {
		assertNotNull(platform.getAliases());
		assertEquals(10, platform.getAliases().size());
		assertEquals("Baya", platform.getAliases().get(0).getName());
		
	}
	
	@Test
	void test_platform_game_mapping() {
		assertNotNull(platform.getGames());
		assertEquals(7, platform.getGames().size());
		assertEquals("Counter-Strike: Global Offensive", platform.getGames().get(0).getName());
		
	}
		
}
