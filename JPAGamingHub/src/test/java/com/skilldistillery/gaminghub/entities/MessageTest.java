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
	
	@Test
	@DisplayName("Message self-join")
	void test_message_self_join() {
		
//		SELECT * FROM message WHERE replying_to_message_id = 7;
//		+----+-------------------+-------------------+------------------------+--------------+---------------------+
//		| id | chat_user_chat_id | chat_user_user_id | replying_to_message_id | contents     | created             |
//		+----+-------------------+-------------------+------------------------+--------------+---------------------+
//		|  8 |                 2 |                 8 |                      7 | User 8 here  | 2022-05-20 12:01:00 |
//		|  9 |                 2 |                 9 |                      7 | User 9 here  | 2022-05-20 12:02:00 |
//		| 10 |                 2 |                10 |                      7 | User 10 here | 2022-05-20 12:03:00 |
//		| 11 |                 2 |                11 |                      7 | User 11 here | 2022-05-20 12:04:00 |
//		| 12 |                 2 |                12 |                      7 | User 12 here | 2022-05-20 12:05:00 |
//		+----+-------------------+-------------------+------------------------+--------------+---------------------+
		
		message = em.find(Message.class, 7);
		assertNotNull(message);
		assertNotNull(message.getReplies());
		assertTrue(message.getReplies().size() > 0);
		
		for (Message reply : message.getReplies()) {
			assertEquals(message.getContents(), reply.getReplyingToMessage().getContents());
//			assertEquals(reply.getToUser().getUsername(), message.getFromUser().getUsername());
		}
	}
	

}
