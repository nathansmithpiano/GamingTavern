package com.skilldistillery.gaminghub.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Meetup;
import com.skilldistillery.gaminghub.entities.User;
import com.skilldistillery.gaminghub.repositories.MeetupRepository;
import com.skilldistillery.gaminghub.repositories.UserRepository;

@Service
public class MeetupServiceImpl implements MeetupService{
	
	@Autowired
	private MeetupRepository meetRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Meetup getMeetupById(int meetupId) {
		Optional<Meetup> op = meetRepo.findById(meetupId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}
	
	@Override
	public Meetup createMeetup(Meetup meetup) {
		return meetRepo.saveAndFlush(meetup);
	}

	

	@Override
	public Meetup updateMeetup(String location, Meetup meetup, int meetupId) {
		Optional<Meetup> op = meetRepo.findById(meetupId);
		if (op.isPresent()) {
			Meetup result = op.get();
			if (result.getLocations().equals(location)) {
				meetup.setId(meetupId);
				return meetRepo.saveAndFlush(meetup);

			}
		}

		return null;
	}

	@Override
	public boolean deleteMeetup(String username, int meetupId) {
		Optional<Meetup> op = meetRepo.findById(meetupId);
		if(op.isPresent()) {
			Meetup result = op.get();
			if(result.getId() == (meetupId)) {
				meetRepo.deleteById(meetupId);
				op = meetRepo.findById(meetupId);
				return !op.isPresent();
			}
		}
		return false;
	}


}
