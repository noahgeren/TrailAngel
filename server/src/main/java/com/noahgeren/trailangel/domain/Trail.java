package com.noahgeren.trailangel.domain;

import javax.persistence.Column;
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
	
	private Integer parkId;
	
	private Double latitude, longitude;
	
	private Double length;
	
	private Double hikeTime;
	
	@Column(columnDefinition = "VARCHAR(1024)")
	private String description;
	
	public Trail(String name, Integer parkId, Double latitude, Double longitude, Double length, Double hikeTime, String description) {
		this.name = name;
		this.parkId = parkId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.length = length;
		this.hikeTime = hikeTime;
		this.description = description;
	}

}
