package com.event.service;

import java.util.List;

import com.event.domain.EventData;
import com.event.domain.EventUsers;
import com.event.domain.ReturnResult;

public interface UserEventService {

	public ReturnResult getEventUsersData(EventUsers eventUsers);
	
	public List<EventData> getAllEvents();
}
