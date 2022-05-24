package com.skilldistillery.gaminghub.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.gaminghub.services.UserService;

class UserServiceTest {
	
	@Autowired
	private UserService userSvc;
	
	private User user;
	private final int USER_ID = 1;
	
	@BeforeEach
	void setUp() throws Exception {
		user = userSvc.getUserById(USER_ID);
		assertNotNull(user);
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
	}
	

	@Test
	@DisplayName("getUserById")
	void test() {
		
	}

}
