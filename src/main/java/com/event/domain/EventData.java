package com.event.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventData {
	
		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String user;
	
	private String eventName;
	private int status;
	private LocalDateTime lastUpdatedTimeStamp;
	
	public EventData() {
		// TODO Auto-generated constructor stub
	}
		
	public EventData(String user, String eventName, int status, LocalDateTime lastUpdatedTimeStamp) {
		super();
		this.user = user;
		this.eventName = eventName;
		this.status = status;
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}



	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	public void setLastUpdatedTimeStamp(LocalDateTime lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	
	
}
