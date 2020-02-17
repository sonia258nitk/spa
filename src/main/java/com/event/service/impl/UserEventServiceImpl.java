package com.event.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.dao.EventUsersRepository;
import com.event.domain.EventData;
import com.event.domain.EventUsers;
import com.event.domain.ReturnResult;
import com.event.service.UserEventService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserEventServiceImpl.
 */
@Service
public class UserEventServiceImpl implements UserEventService {
	
	
	/** The event repository. */
	@Autowired
	private EventUsersRepository eventRepository;
	
	/**
	 * Gets the event users data.
	 *
	 * @param eventUsers the event users
	 * @return the event users data
	 */
	@Override
	public ReturnResult getEventUsersData(EventUsers eventUsers) {
		
		List<EventData> dataForAnEvent=eventRepository.findByEventName(eventUsers.getEventName());
		List<EventData> eventDataToSave=new ArrayList<>();
		if(dataForAnEvent.isEmpty()) {
			eventUsers.getUsers().forEach(user->{
				eventDataToSave.add(new EventData(user, eventUsers.getEventName(), 1, LocalDateTime.now()));
			});
		
		}
		else {
			Set<String> existingUsersForAnEvent=new HashSet<>();
			dataForAnEvent.forEach(data->{
				existingUsersForAnEvent.add(data.getUser());
			});
			
			getNewUsersForAnEventInRequest(eventUsers.getUsers(), existingUsersForAnEvent).forEach(user->{
				eventDataToSave.add(new EventData(user, eventUsers.getEventName(), 1, LocalDateTime.now()));
			});
			
			getMissedUsersForAnEventInRequest(eventUsers.getUsers(),existingUsersForAnEvent).forEach(user->{
				eventDataToSave.add(new EventData(user, eventUsers.getEventName(), 0, LocalDateTime.now()));
			});
			
		}
		/**
		 * Collect all the event data in a list and save it in the database
		 */
		List<EventData> savedEventData=eventRepository.saveAll(eventDataToSave);
		List<String> registeredUsers=new ArrayList<>();
		
		/**
		 * After saving the data in the database. Get the information that was saved and then filter
		 * the registered and unregistered entries from the saved data.
		 */
		
		/**
		 * filter the registered users
		 */
		savedEventData.forEach(data->{
			if(data.getStatus()==1) {
				registeredUsers.add(data.getUser());
			}
		});
		
		/**
		 * Filter the unregistred Users
		 */
		List<String> unRegisteredUsers=new ArrayList<>();
		savedEventData.forEach(data->{
			if(data.getStatus()==0) {
				unRegisteredUsers.add(data.getUser());
			}
		});
		ReturnResult result=new ReturnResult();
		result.setRegisteredList(registeredUsers);
		result.setUnRegisteredList(unRegisteredUsers);
		return result;
	}
	
	/**
	 * This method creates a set of users which are new in the request. Compares the existing users in database
	 *
	 * @param usersFromRequest the users from request
	 * @param usersFromDatabase the users from database
	 * @return the new users for an event in request
	 */
	private Set<String> getNewUsersForAnEventInRequest(Set<String> usersFromRequest,Set<String> usersFromDatabase){
		Set<String> newUsers= usersFromRequest.stream().filter(user->!usersFromDatabase.contains(user)).collect(Collectors.toSet());
		return newUsers;
	}
	
	/**
	 * This method finds the missing user in the request to mark it as unregistred in the database.
	 *
	 * @param usersFromRequest the users from request
	 * @param usersFromDatabase the users from database
	 * @return the missed users for an event in request
	 */
	private Set<String> getMissedUsersForAnEventInRequest(Set<String> usersFromRequest,Set<String> usersFromDatabase){
		Set<String> missedUsers= usersFromDatabase.stream().filter(user->!usersFromRequest.contains(user)).collect(Collectors.toSet());
		return missedUsers;
	}

	@Override
	public List<EventData> getAllEvents() {
		List<EventData> data=eventRepository.findAll();
		return data;
	}

}
