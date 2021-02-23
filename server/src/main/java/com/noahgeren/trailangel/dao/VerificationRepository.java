package com.noahgeren.trailangel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.Verification;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, String> {

}
