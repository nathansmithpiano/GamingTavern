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

}
