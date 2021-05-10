package com.noahgeren.trailangel.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahgeren.trailangel.domain.Hike;
import com.noahgeren.trailangel.domain.User;
import com.noahgeren.trailangel.dto.GeneralResponse;
import com.noahgeren.trailangel.services.HikeService;
import com.noahgeren.trailangel.services.UserService;

@RestController
@RequestMapping("/hike")	
public class HikeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HikeService hikeService;
	
	@GetMapping("/all")
	public List<Hike> findAllUserHikes(final Principal principal) {
		if(principal != null) {
			return hikeService.findByUser(principal.getName());
		}
		return null;
	}
	
	@PostMapping("/save")
	public Hike saveHike(final Principal principal, @RequestBody final Hike hike) {
		if(principal == null) return null;
		if(hike.getId() != null) {
			final Hike oldHike = hikeService.findById(hike.getId());
			if(oldHike != null && !oldHike.getUser().equals(principal.getName())) {
				return null;
			}
			hike.setUser(oldHike.getUser());
		} else {
			hike.setUser(principal.getName());
		}
		System.out.println("Saving " + hike.toString());
		return hikeService.save(hike);
	}
	
	@PostMapping("/delete")
	public Hike deleteHike(final Principal principal, @RequestBody Hike hike) {
		if(principal == null) return null;
		if(hike.getId() != null) {
			hike = hikeService.findById(hike.getId());
			if(hike != null && hike.getUser().equals(principal.getName())) {
				hikeService.deleteById(hike.getId());
				return hike;
			}
		}
		return null;
	}

}
