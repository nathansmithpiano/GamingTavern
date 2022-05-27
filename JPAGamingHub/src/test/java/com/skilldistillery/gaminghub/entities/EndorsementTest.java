package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Year;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndorsementTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Endorsement endo;

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
		endo = em.find(Endorsement.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Endorsement mapping")
	void test_endorsement_mapping() {
		assertNotNull(endo);
		assertEquals("Good communication", endo.getName());
		assertEquals(2022, endo.getCreated().getYear());
		assertEquals(5, endo.getCreated().getMonthValue());
		assertEquals(24, endo.getCreated().getDayOfMonth());
		
	}
	
}
//+----+--------------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//| id | name               | image_url                                                                          | created             | updated             |
//+----+--------------------+------------------------------------------------------------------------------------+---------------------+---------------------+
//|  1 | Good communication | https://skilldistillery.com/wp-content/uploads/2016/02/skilldistillery_website.png | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//+----+--------------------+------------------------------------------------------------------------------------+---------------------+---------------------+

