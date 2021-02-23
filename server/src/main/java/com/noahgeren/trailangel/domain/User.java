package com.noahgeren.trailangel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	private String phoneNumber;
	
	private String name;
	
	public User(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}
