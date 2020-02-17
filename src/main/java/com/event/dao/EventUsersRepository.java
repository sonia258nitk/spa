package com.event.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.domain.EventData;

public interface EventUsersRepository extends JpaRepository<EventData, String> {

	List<EventData> findByEventName(String eventName);
	
	List<EventData> findAll();
}
