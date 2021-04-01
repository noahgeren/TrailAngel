package com.noahgeren.trailangel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Trail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Double length;
	
	private Integer parkId;
	
	public Trail(String name, Double length, Integer parkId) {
		this.name = name;
		this.length = length;
		this.parkId = parkId;
	}

}
