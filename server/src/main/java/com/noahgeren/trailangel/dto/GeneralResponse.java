package com.noahgeren.trailangel.dto;

import java.util.HashMap;

public class GeneralResponse extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 4973673594211444744L;
	
	public GeneralResponse() {
		super();
	}
	
	public GeneralResponse set(String key, Object value) {
		put(key, value);
		return this;
	}

}