package com.noahgeren.trailangel.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.Hike;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {
	
	public List<Hike> findByUserAndEndTimeAfterOrderByStartTimeAsc(String user, LocalDateTime endTime);
	
	public List<Hike> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
	
	public List<Hike> findByEndTimeBetween(LocalDateTime start, LocalDateTime end);

}
