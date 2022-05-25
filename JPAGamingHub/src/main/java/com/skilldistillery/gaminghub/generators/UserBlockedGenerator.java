package com.skilldistillery.gaminghub.generators;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserBlockedGenerator {

	int firstUserId = 2;
	int lastUserId = 2001;
	int minBlocks = 0;
	int maxBlocks = 13;
	int usersWithBlocks = 87;

	public static void main(String[] args) {
		UserBlockedGenerator app = new UserBlockedGenerator();
		app.run();
	}

	private void run() {
		
		Set<String> output = new LinkedHashSet<>();
		
		for (int i = 0; i < usersWithBlocks; i++) {
			// choose random userId for person with blocks
			int randomUserId = getRandomUserId();
			
			// create Set of friendIds (this prevents duplicates but may reduce # blocks)
			Set<Integer> blockedIds = new LinkedHashSet<>();
			
			// choose a random number of blocks for this user
			int randomNumBlocks = (int) Math.round( (Math.random() * (maxBlocks - minBlocks)) + minBlocks);
			for (int j = 0; j < randomNumBlocks; j++) {
				
				// choose a random blocked userId
				int randomBlockId = randomUserId;
				
				// make sure userId and blockId are not the same
				while (randomUserId == randomBlockId) {
					randomBlockId = getRandomUserId();
				}
				
				// add to Set (will not add if duplicate)
				blockedIds.add(randomBlockId);
			}
			
			// add to output, two for each (must be blocked on both sides)
			for (Integer blockId : blockedIds) {
				output.add(randomUserId + "," + blockId);
				output.add(blockId + "," + randomUserId);
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
