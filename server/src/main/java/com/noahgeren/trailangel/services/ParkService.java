package com.noahgeren.trailangel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.ParkRepository;
import com.noahgeren.trailangel.domain.Park;

@Service
public class ParkService {

	@Autowired
	ParkRepository parkRepo;
	
	public List<Park> findAllParks() {
		return parkRepo.findAll();
	}
	
}
