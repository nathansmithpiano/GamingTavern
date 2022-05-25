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

class MessageTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Message message;

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
		message = em.find(Message.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("Message mapping")
	void test_message_mapping() {
		
//		SELECT * FROM message WHERE id=1;
//		+----+-------------------+-------------------+------------------------+-----------------+---------------------+
//		| id | chat_user_chat_id | chat_user_user_id | replying_to_message_id | contents        | created             |
//		+----+-------------------+-------------------+------------------------+-----------------+---------------------+
//		|  1 |                 1 |                 1 |                   NULL | Hello to User 2 | 2022-05-25 15:00:00 |
//		+----+-------------------+-------------------+------------------------+-----------------+---------------------+
		
		assertNotNull(message);
		assertNotNull(message.getContents());
		assertEquals("Hello to User 2", message.getContents());
		
	}
	

}
