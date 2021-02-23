package com.noahgeren.trailangel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.Park;

@Repository
public interface ParkRepository extends JpaRepository<Park, Integer> {

}
