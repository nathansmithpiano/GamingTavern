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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("User mapping")
	void test_user_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	void test_user_alias_mapping() {
		
//		SELECT * FROM user JOIN alias ON alias.user_id = user.id WHERE user.id = 2;
//		+----+---------+-----------+---------------+--------------------------------------------------------------+---------------------------+------------+-------------+-----------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+------+---------+---------+---------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//		| id | enabled | role      | username      | password                                                     | email                     | first_name | middle_name | last_name | description | image_url                                                                          | created             | updated             | id   | user_id | enabled | name    | description | image_url                                                                          | created             | updated             |
//		+----+---------+-----------+---------------+--------------------------------------------------------------+---------------------------+------------+-------------+-----------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+------+---------+---------+---------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//		|  2 |       1 | ROLE_USER | bamboogateway | $2a$10$LNtqBOXd./fZWzfKaAj40uqdwZ2FX0KI2JUKSDLBVs3efyTCBvf6a | coleman.burrows@gmail.com | Coleman    |             | Burrows   |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 | 1955 |       2 |       1 | Timothy |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		|  2 |       1 | ROLE_USER | bamboogateway | $2a$10$LNtqBOXd./fZWzfKaAj40uqdwZ2FX0KI2JUKSDLBVs3efyTCBvf6a | coleman.burrows@gmail.com | Coleman    |             | Burrows   |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 | 2150 |       2 |       1 | Greyson |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		|  2 |       1 | ROLE_USER | bamboogateway | $2a$10$LNtqBOXd./fZWzfKaAj40uqdwZ2FX0KI2JUKSDLBVs3efyTCBvf6a | coleman.burrows@gmail.com | Coleman    |             | Burrows   |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 | 3988 |       2 |       1 | Wigglz  |             | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+---------+-----------+---------------+--------------------------------------------------------------+---------------------------+------------+-------------+-----------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+------+---------+---------+---------+-------------+------------------------------------------------------------------------------------+---------------------+---------------------+
		
		user = em.find(User.class, 2);
		assertNotNull(user);
		assertEquals("bamboogateway", user.getUsername());
		assertNotNull(user.getAliases());
		assertEquals(3, user.getAliases().size());
		
		for (Alias alias : user.getAliases()) {
			assertEquals("bamboogateway", alias.getUser().getUsername());
		}
	}

}
