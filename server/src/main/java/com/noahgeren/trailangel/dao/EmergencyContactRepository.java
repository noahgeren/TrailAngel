package com.noahgeren.trailangel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.EmergencyContact;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
	
	public List<EmergencyContact> findByUserOrderByIdAsc(String user);

}
