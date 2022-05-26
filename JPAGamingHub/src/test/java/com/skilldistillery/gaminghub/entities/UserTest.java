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
	
	@DisplayName("User Alias oTm mapping")
	@Test
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
	
	@DisplayName("User Friend mapping")
	@Test
	void test_user_friend_mapping() {
		
//		SELECT user_id, COUNT(*) FROM user_friend GROUP BY user_id ORDER BY COUNT(*) DESC;
//		+---------+----------+
//		| user_id | COUNT(*) |
//		+---------+----------+
//		|     675 |       70 |
		
		// user has friends
		user = em.find(User.class, 675);
		assertNotNull(user);
		assertNotNull(user.getFriends());
		assertEquals(70, user.getFriends().size());
		
		// user has each friend only one time
		int matchCount = 0;
		for (User usersFriend : user.getFriends()) {
			
			// user's friend has friends
			assertNotNull(usersFriend.getFriends());
			assertTrue(usersFriend.getFriends().size() > 0);
			
			// find match by usernames
			for (User friendsFriend : usersFriend.getFriends()) {
				if (friendsFriend.getUsername().equals(user.getUsername())) {
					matchCount++;
				}
			}
		}
		
		// number of times user's friends have this user as a friend
		assertEquals(70, matchCount);
	}
	
	@DisplayName("UserFriend ID")
	@Test
	void test_user_friend_id() {
		
		// find user with only 1 friend via user_friend table
		// composite key is basically user.friend.id ( user.user.{id1, id2} )
		
//		SELECT * FROM user_friend WHERE user_id = 2;
//		+---------+-----------+---------------------+
//		| user_id | friend_id | created             |
//		+---------+-----------+---------------------+
//		|       2 |      1957 | 2019-01-20 19:37:35 |
//		+---------+-----------+---------------------+
		
		// create new composite id as above
		UserFriendId id = new UserFriendId();
		id.setUserId(2);
		id.setFriendId(1957);
		
		// this works just like em.find(class, int id)
		UserFriend userFriend = em.find(UserFriend.class, id);
		assertNotNull(userFriend);
		
		// toString puts "T" where there was a space in mysql
		assertEquals("2019-01-20T19:37:35", userFriend.getCreated().toString());
	}
	
	@DisplayName("User --> Chat Mapping")
	@Test
	void test_user_to_chat_mapping() {

//		SELECT id, created_by_user_id, enabled, title, description FROM chat;
//		+----+--------------------+---------+--------------------+------------------------------+
//		| id | created_by_user_id | enabled | title              | description                  |
//		+----+--------------------+---------+--------------------+------------------------------+
//		|  1 |                  1 |       1 | Hell's Angels Chat | Guild Chat for Hell's Angels |
//		|  2 |                  7 |       1 | Hell's Angels Chat | Guild Chat for Hell's Angels |
//		+----+--------------------+---------+--------------------+------------------------------+
		user = em.find(User.class, 1);
		assertNotNull(user);
		assertNotNull(user.getChats());
		int matches = 0;
		int expectedMatches = user.getChats().size();
		for(Chat chat: user.getChats()) {
			assertNotNull(chat.getUsers());
			assertTrue(chat.getUsers().size() > 0);
			for(User chatUser: chat.getUsers()) {
				if(chatUser.getUsername().equals(user.getUsername())) {
					matches++;
				}
			}
		}
		assertEquals(expectedMatches, matches);
		}
	
	@DisplayName("User --> Location ManyToMany Mapping")
	@Test
	void test_user_to_location_mapping() {
//		SELECT user_id, COUNT(*) FROM user_location GROUP BY user_id ORDER BY COUNT(*) DESC;
//		+---------+----------+
//		| user_id | COUNT(*) |
//		+---------+----------+
//		|    1024 |        2 |
		
		user = em.find(User.class, 1024);
		assertNotNull(user);
		assertNotNull(user.getLocations());
		assertTrue(user.getLocations().size() > 0);
		int matches = 0;
		for(Location location: user.getLocations()) {
			assertNotNull(location.getUsers());
			assertTrue(location.getUsers().size() > 0);
			for(User locationUser: location.getUsers()) {
				if(locationUser.getUsername().equals(user.getUsername())) {
					matches++;
				}
			}
		}
		assertEquals(2, matches);
	}
	
	@DisplayName("User --> Equipment ManyToMany Mapping")
	@Test
	void test_user_to_equipment_mapping() {
//		SELECT user_id, count(*) FROM user_equipment GROUP BY user_id ORDER BY count(*) desc;
//		+---------+----------+
//		| user_id | count(*) |
//		+---------+----------+
//		|      16 |        3 |
		
		user = em.find(User.class, 16);
		assertNotNull(user);
		assertNotNull(user.getEquipments());
		assertTrue(user.getEquipments().size() > 0);
		int matches = 0;
		for(Equipment equipment: user.getEquipments()) {
			assertNotNull(equipment.getUsers());
			assertTrue(equipment.getUsers().size() > 0);
			for(User equipmentUser: equipment.getUsers()) {
				if(equipmentUser.getFirstName().equals(user.getFirstName())) {
					matches++;
				}
			}
		}
		assertEquals(3, matches);
	}
	
	@DisplayName("User --> Meetup OneToMany Mapping")
	@Test
	void test_user_to_meetup_mapping() {
//		SELECT * from meetup WHERE user_id=398;
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		| id | timezone_id | user_id | name         | date                | capacity | description | created             | updated             |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
//		|  1 |           8 |     398 | Free for all | 2022-05-03 20:00:00 |       36 |             | 2022-05-24 18:30:00 | 2022-05-24 18:30:00 |
//		+----+-------------+---------+--------------+---------------------+----------+-------------+---------------------+---------------------+
		user = em.find(User.class, 398);
		assertNotNull(user);
		assertNotNull(user.getMeetups());
		assertTrue(user.getMeetups().size() > 0);
		int matches = 0;
		int expectedMatches = user.getMeetups().size();
		for(Meetup meetup: user.getMeetups()) {
			if(meetup.getUser().getUsername().equals(user.getUsername())) {
				matches++;
			}
		}
		assertEquals(expectedMatches, matches);
	}
	
	
	
	
	
}
