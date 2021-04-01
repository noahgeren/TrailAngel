package com.noahgeren.trailangel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.ParkRepository;
import com.noahgeren.trailangel.dao.TrailRepository;
import com.noahgeren.trailangel.domain.Park;
import com.noahgeren.trailangel.domain.Trail;

@Service
public class ParkService {

	@Autowired
	ParkRepository parkRepo;
	
	@Autowired
	TrailRepository trailRepo;
	
	public List<Park> findAllParks() {
		return parkRepo.findAll();
	}
	
	public List<Trail> findAllTrails() {
		return trailRepo.findAll();
	}
	
}
