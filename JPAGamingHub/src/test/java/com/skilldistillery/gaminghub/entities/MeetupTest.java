package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.*;
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

class MeetupTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Meetup meetup;

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
		meetup = em.find(Meetup.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Meetup mapping")
	void test() {
		assertNotNull(meetup);
		assertNotNull(meetup.getName());
		assertEquals("Free for all", meetup.getName());
	}



	void test_meetup_mapping() {
		
//		SELECT * FROM meetup WHERE id=1;
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		| id | timezone_id | user_id | name         | date                | capacity | description | created             | updated             |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		|  1 |           8 |     398 | Free for all | 2022-05-03 20:00:00 |       36 |             | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
		
		assertNotNull(meetup);
		assertNotNull(meetup.getName());
		assertEquals("Free for all", meetup.getName());
		
	}
	

}
