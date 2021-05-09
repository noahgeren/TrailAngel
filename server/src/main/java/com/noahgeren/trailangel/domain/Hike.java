package com.noahgeren.trailangel.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Hike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String trailName;
	
	private Double latitude, longitude;
	
	private Double length;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	@JsonIgnore
	private String user;

}
