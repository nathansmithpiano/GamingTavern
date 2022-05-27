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
	
	@Test
	@DisplayName("Meetup MTM location mapping")
	void test() {
		meetup = em.find(Meetup.class, 1);
		assertNotNull(meetup);
		assertNotNull(meetup.getLocations());
		assertTrue(meetup.getLocations().size() > 0);
		assertEquals(1, meetup.getLocations().get(0)
				.getMeetups().get(0).getId());
		// test both sides and no duplicates
		int expectedMatches = meetup.getLocations().size();
		int matches = 0;
	
		// each of server's clans
		for(Location location : meetup.getLocations()) {
			// each of the server's clan's servers
			for(Meetup locationMeetup : location.getMeetups()) {
				// verify valid data
				if(locationMeetup.getName().equals(meetup.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
		
	}
	
	@Test
	@DisplayName("Meetup MTM alias mapping")
	void test2() {
		meetup = em.find(Meetup.class, 1);
		assertNotNull(meetup);
		assertNotNull(meetup.getAliases());
		assertTrue(meetup.getAliases().size() > 0);
		assertEquals(1, meetup.getAliases().get(0)
				.getMeetups().get(0).getId());
		// test both sides and no duplicates
		int expectedMatches = meetup.getAliases().size();
		int matches = 0;
	
		// each of server's clans s = meetup c = alias
		for(Alias alias : meetup.getAliases()) {
			// each of the server's clan's servers
			for(Meetup aliasMeetup : alias.getMeetups()) {
				// verify valid data
				if(aliasMeetup.getName().equals(meetup.getName())) {
					matches++;
				}
			}
		}
		
		assertEquals(expectedMatches, matches);
	}
	
	@Test
	@DisplayName("Meetup MTM games mapping")
	void test3() {
		meetup = em.find(Meetup.class, 1);
		assertNotNull(meetup);
		assertNotNull(meetup.getGames());
		assertTrue(meetup.getGames().size() > 0);
		assertEquals(1, meetup.getGames().get(0)
				.getMeetups().get(0).getId());
		// test both sides and no duplicates
		int expectedMatches = meetup.getGames().size();
		int matches = 0;
	
		// each of server's clans m = s c = game
		for(Game games : meetup.getGames()) {
			// each of the server's clan's servers
			for(Meetup gameMeetup : games.getMeetups()) {
				// verify valid data
				if(gameMeetup.getName().equals(meetup.getName())) {
					matches++;
				}
			}
		}
		assertEquals(expectedMatches, matches);
	}
	
//	@Test
//	@DisplayName("Testing ManyToMany Meetup ---> User")
//	void test4() {
//		meetup = em.find(Meetup.class, 1);
//		assertNotNull(meetup);
//		assertNotNull(meetup.getUser());
//		assertTrue(meetup.getUser().size() > 0);
//	}
	
//	@Test
//	@DisplayName("Testing ManyToMany Meetup ---> Timezone")
//	void test5() {
//		meetup = em.find(Meetup.class, 1);
//		assertNotNull(meetup);
//		assertNotNull(meetup.getTimezone());
//		assertTrue(meetup.getTimezone().size() > 0);
//	}
	
//	@DisplayName("User --> Meetup OneToMany Mapping")
//	@Test
//	void test_user_to_meetup_mapping() {
////		SELECT * from meetup WHERE user_id=398;
////		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
////		| id | timezone_id | user_id | name         | date                | capacity | description | created             | updated             |
////		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
////		|  1 |           8 |     398 | Free for all | 2022-05-03 20:00:00 |       36 |             | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
////		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		user = em.find(User.class, 398);
//		assertNotNull(user);
//		assertNotNull(user.getMeetups());
//		assertTrue(user.getMeetups().size() > 0);
//		int matches = 0;
//		int expectedMatches = user.getMeetups().size();
//		for(Meetup meetup: user.getMeetups()) {
//			if(meetup.getUser().getUsername().equals(user.getUsername())) {
//				matches++;
//			}
//		}
//		assertEquals(expectedMatches, matches);
//	}
}
