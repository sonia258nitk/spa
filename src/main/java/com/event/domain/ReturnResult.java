package com.event.domain;

import java.util.List;

public class ReturnResult {

	private List<String> registeredList;
	private List<String> unRegisteredList;
	
	public List<String> getRegisteredList() {
		return registeredList;
	}
	
	public List<String> getUnRegisteredList() {
		return unRegisteredList;
	}
	
	public void setRegisteredList(List<String> registeredList) {
		this.registeredList = registeredList;
	}
	
	public void setUnRegisteredList(List<String> unRegisteredList) {
		this.unRegisteredList = unRegisteredList;
	}
	
}
