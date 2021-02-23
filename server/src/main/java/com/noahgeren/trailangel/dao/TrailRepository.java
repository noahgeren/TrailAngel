package com.noahgeren.trailangel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.Trail;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Integer> {

}
