package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Meetup;

public interface MeetupService {
	
	Meetup getMeetupById(int meetupId);
	
	public Meetup createMeetup(Meetup meetup);
	
	public Meetup updateMeetup(String username, Meetup meetup, int meetupId);
	
	public boolean deleteMeetup(String username, int meetupId);

}
