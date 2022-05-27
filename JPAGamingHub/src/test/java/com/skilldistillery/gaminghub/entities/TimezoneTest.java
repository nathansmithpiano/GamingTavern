package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimezoneTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Timezone timezone;

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
		timezone = em.find(Timezone.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Timezone entity mapping")
	void test() {
		assertNotNull(timezone);
		assertNotNull(timezone.getLocale());
		assertEquals("Military", timezone.getLocale());
		// 1 | A | 1 | Military | Alpha Time Zone 
	}

	@Test
	@DisplayName("Testing OneToMany Timezone ---> Meetup")
	void test2() {
		timezone = em.find(Timezone.class, 106);
		assertNotNull(timezone);
		assertNotNull(timezone.getMeetups());
		assertTrue(timezone.getMeetups().size() > 0);
//		assertEquals("106", timezone.getMeetups());
//	 test both sides and no duplicates
	int matches = 0;
	int expectedMatches = timezone.getMeetups().size();

//	each of the user's meetups
	for(Meetup meetup: timezone.getMeetups()) {
		// verify valid data
		if(meetup.getTimezone().getId()==(timezone.getId())) {
			matches++;
		}
	}

	assertEquals(expectedMatches, matches);
//	SELECT * FROM meetup WHERE id = 3;
//	+----+-------------+---------+-----------+---------------------+----------+-------------+---------------------+---------------------+
//	| id | timezone_id | user_id | name      | date                | capacity | description | created             | updated             |
//	+----+-------------+---------+-----------+---------------------+----------+-------------+---------------------+---------------------+
//	|  3 |         106 |     958 | Mini golf | 2022-05-17 17:30:00 |       12 |             | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//	+----+-------------+---------+-----------+---------------------+----------+-------------+---------------------+---------------------+
}
	
	@Test
	@DisplayName("Testing OneToMany Timezone ---> Location")
	void test3() {
		timezone = em.find(Timezone.class, 3);
		assertNotNull(timezone);
		assertNotNull(timezone.getLocations());
		assertTrue(timezone.getLocations().size() > 0);
//	 test both sides and no duplicates
	int matches = 0;
	int expectedMatches = timezone.getLocations().size();

//	each of the user's location
	for(Location location : timezone.getLocations()) {
		// verify valid data
		if(location.getTimezone().getId()==(timezone.getId())) {
			matches++;
		}
	}

	assertEquals(expectedMatches, matches);
//	SELECT id, timezone_id FROM location ORDER BY timezone_id;
//	+------+-------------+
//	| id   | timezone_id |
//	+------+-------------+
//	|   21 |           3 |
}
}
