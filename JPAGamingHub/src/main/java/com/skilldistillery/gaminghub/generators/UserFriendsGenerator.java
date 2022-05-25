package com.skilldistillery.gaminghub.generators;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserFriendsGenerator {

	int firstUserId = 2;
	int lastUserId = 2001;
	int minFriends = 0;
	int maxFriends = 31;
	int usersWithFriends = 479;

	public static void main(String[] args) {
		UserFriendsGenerator app = new UserFriendsGenerator();
		app.run();
	}

	private void run() {
		
		Set<String> output = new LinkedHashSet<>();
		
		for (int i = 0; i < usersWithFriends; i++) {
			// choose random userId for person with friends
			int randomUserId = getRandomUserId();
			
			// create Set of friendIds (this prevents duplicates but may reduce # friends)
			Set<Integer> friendIds = new LinkedHashSet<>();
			
			// choose a random number of friends for this user
			int randomNumFriends = (int) Math.round( (Math.random() * (maxFriends - minFriends)) + minFriends);
			for (int j = 0; j < randomNumFriends; j++) {
				
				// choose a random friend userId
				int randomFriendId = randomUserId;
				
				// make sure userId and friendId are not the same
				while (randomUserId == randomFriendId) {
					randomFriendId = getRandomUserId();
				}
				
				// add to Set (will not add if duplicate)
				friendIds.add(randomFriendId);
			}
			
			// add to output, two for each (must be friends on both sides)
			for (Integer friendId : friendIds) {
				output.add(randomUserId + "," + friendId);
				output.add(friendId + "," + randomUserId);
			}
			
		}
		
		for (String str : output) {
			System.out.println(str);
		}
		
		System.out.println("*** TOTAL ROWS: " + output.size());
	}

	private int getRandomUserId() {
		return (int) Math.round((Math.random() * (lastUserId - firstUserId)) + firstUserId);
	}

}
