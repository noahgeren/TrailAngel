package com.noahgeren.trailangel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahgeren.trailangel.domain.Trail;
import com.noahgeren.trailangel.services.ParkService;

@RestController
@RequestMapping("/trails")
public class TrailController {

	@Autowired
	ParkService parkService;
	
	@GetMapping("/all")
	public List<Trail> getTrails() {
		return parkService.findAllTrails();
	}
	
}
