package com.noahgeren.trailangel.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Verification {

	@Id
	private String phoneNumber;
	
	private String code;
	
	@CreationTimestamp
	private LocalDateTime created;
	
	public Verification(String phoneNumber, String code) {
		this.phoneNumber = phoneNumber;
		this.code = code;
	}
	
}
