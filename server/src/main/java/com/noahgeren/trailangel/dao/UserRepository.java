package com.noahgeren.trailangel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noahgeren.trailangel.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
