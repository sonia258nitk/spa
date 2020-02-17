package com.event.domain;

import java.util.List;
import java.util.Set;

public class EventUsers {

	private String eventName;
	private Set<String> users;
	
	public String getEventName() {
		return eventName;
	}
	
	public Set<String> getUsers() {
		return users;
	}
	
	
	 public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	 
	 public void setUsers(Set<String> users) {
		this.users = users;
	}
}
