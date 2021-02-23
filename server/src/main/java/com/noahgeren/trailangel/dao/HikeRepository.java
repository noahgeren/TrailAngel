package com.noahgeren.trailangel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.Hike;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {

}
