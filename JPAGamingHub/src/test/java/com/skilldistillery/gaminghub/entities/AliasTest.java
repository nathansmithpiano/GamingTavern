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

class AliasTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Alias alias;

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
		alias = em.find(Alias.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Alias mapping")
	void test_alias_mapping() {
		assertNotNull(alias);
		assertEquals("Baya", alias.getName());
		assertEquals(1619, alias.getUser().getId());
	}
	
//	---------------------+---------------------+---------------------+
//	| id | user_id | enabled | name | description | image_url                                                                          | created             | updated             |
//	+----+---------+---------+------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//	|  1 |    1619 |       1 | Baya |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//	+----+---------+---------+------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+


}
