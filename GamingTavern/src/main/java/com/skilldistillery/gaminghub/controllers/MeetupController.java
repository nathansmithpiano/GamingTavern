package com.skilldistillery.gaminghub.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gaminghub.entities.Meetup;
import com.skilldistillery.gaminghub.services.MeetupService;


@RestController
@CrossOrigin({ "*", "http://localhost" })
@RequestMapping("api")
public class MeetupController {

	@Autowired
	private MeetupService meetupSvc;

	@GetMapping("meetups")
	public List<Meetup> index(Principal principal) {
		List<Meetup> meetups = meetupSvc.index();
		return meetups;
	}

	@GetMapping("meetups/{meetupId}")
	public Meetup show(Principal principal, HttpServletResponse resp, @PathVariable int meetupId) {
		Meetup meetup = meetupSvc.getMeetupById(meetupId);
		if (meetup == null) {
			resp.setStatus(404);
		}
		return meetup;
	}

	@PostMapping("meetups")
	public Meetup create(@RequestBody Meetup meetup, Principal principal) {
		return meetupSvc.createMeetup(meetup);
	}

//	@PutMapping("meetups/{meetupId}")
//	public Meetup update(@PathVariable int meetupId, @RequestBody Meetup meetup,@PathVariable String username, Principal principal) {
//		return meetupSvc.updateMeetup(username, meetup, meetupId);
//	}

//	@DeleteMapping("meetups/{meetupId}")
//	public void destroy(HttpServletResponse resp, @PathVariable String username, @PathVariable int meetupId, Principal principal) {
//		if(meetupSvc.deleteMeetup(username, meetupId)) {
//			resp.setStatus(204);
//		}else {
//			resp.setStatus(404);
//		}
//	}

}