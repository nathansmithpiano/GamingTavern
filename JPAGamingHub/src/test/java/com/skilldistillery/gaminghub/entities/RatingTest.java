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

class RatingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rating rating;

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
		rating = em.find(Rating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Rating mapping")
	void test_rating_mapping() {

//		SELECT * FROM rating WHERE id=1;
//		+----+------+-------------+
//		| id | name | description |
//		+----+------+-------------+
//		|  1 | E    | Everybody   |
//		+----+------+-------------+

		assertNotNull(rating);
		assertEquals("Everybody", rating.getDescription());
	}

	@Test
	@DisplayName("Rating 1:m Game")
	void test_rating_game_apping() {

//		SELECT game.id, rating_id FROM game JOIN rating ON rating.id = game.rating_id;
//		+----+-----------+
//		| id | rating_id |
//		+----+-----------+
//		|  2 |         4 |

		rating = em.find(Rating.class, 4);
		assertNotNull(rating);
		assertNotNull(rating.getGames());
		assertTrue(rating.getGames().size() > 0);

		// test both sides and no duplicates
		int matches = 0;
		int expectedMatches = rating.getGames().size();

		// each of the rating's games
		for (Game game : rating.getGames()) {
			// verify valid data
			if (game.getRating().getName().equals(rating.getName())) {
				matches++;
			}
		}

		assertEquals(expectedMatches, matches);
	}

}
