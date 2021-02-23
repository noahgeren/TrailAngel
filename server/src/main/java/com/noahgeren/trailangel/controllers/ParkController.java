package com.noahgeren.trailangel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahgeren.trailangel.domain.Park;
import com.noahgeren.trailangel.services.ParkService;

@RestController
@RequestMapping("/parks")
public class ParkController {
	
	@Autowired
	ParkService parkService;
	
	@GetMapping("/all")
	public List<Park> getAllParks() {
		return parkService.findAllParks();
	}

}
