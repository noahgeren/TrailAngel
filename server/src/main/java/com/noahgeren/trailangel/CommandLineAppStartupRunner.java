package com.noahgeren.trailangel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noahgeren.trailangel.dao.UserRepository;
import com.noahgeren.trailangel.domain.User;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new User("417-773-9631"));
	}

}
