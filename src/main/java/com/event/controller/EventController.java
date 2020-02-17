package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.domain.EventData;
import com.event.domain.EventUsers;
import com.event.domain.ReturnResult;
import com.event.service.UserEventService;

@RestController
public class EventController {
	
	@Autowired
	UserEventService eventService;
	
	@GetMapping("/data")
	public List<EventData> getUserDetails() {
		return eventService.getAllEvents();
	}
	
	@PostMapping("/data")
	public ReturnResult getUserDetails(@RequestBody EventUsers eventUsersRequest) {
		return eventService.getEventUsersData(eventUsersRequest);
	}

}
