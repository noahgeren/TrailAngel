package com.noahgeren.trailangel.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.HikeRepository;
import com.noahgeren.trailangel.domain.Hike;

@Service
public class HikeService {
	
	@Autowired
	HikeRepository hikeRepo;

	public Hike save(final Hike hike) {
		return hikeRepo.save(hike);
	}
	
	public Hike findById(final Long id) {
		return hikeRepo.findById(id).orElse(null);
	}
	
	public void deleteById(final Long id) {
		hikeRepo.deleteById(id);
	}
	
	public List<Hike> findByUser(final String user) {
		return hikeRepo.findByUserAndEndTimeAfterOrderByStartTimeAsc(user, LocalDateTime.now());
	}
	
	public List<Hike> findByStartTime(final LocalDateTime start, final LocalDateTime end) {
		return hikeRepo.findByStartTimeBetween(start, end);
	}
	
	public List<Hike> findByEndTime(final LocalDateTime start, final LocalDateTime end) {
		return hikeRepo.findByEndTimeBetween(start, end);
	}
	
}
