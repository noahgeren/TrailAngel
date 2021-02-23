package com.noahgeren.trailangel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = -1407836428037473490L;

	@Id
	private String phoneNumber;
	
	private String name;
	
	private String trailName;
	
	public User(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public User(String phoneNumber, String name, String trailName) {
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.trailName = trailName;
	}


}
