package com.noahgeren.trailangel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.UserRepository;
import com.noahgeren.trailangel.domain.User;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepo;

	public User findUser(String phoneNumber) {
		return userRepo.findById(phoneNumber).orElse(null);
	}
	
	public User createUser(String phoneNumber) {
		return userRepo.save(new User(phoneNumber));
	}
	
	public User saveUser(User user) {
		if(user == null || user.getPhoneNumber() == null) return null;
		return userRepo.save(user);
	}
	
}
