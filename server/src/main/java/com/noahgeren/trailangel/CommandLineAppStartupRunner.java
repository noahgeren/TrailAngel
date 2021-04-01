package com.noahgeren.trailangel;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noahgeren.trailangel.dao.ParkRepository;
import com.noahgeren.trailangel.dao.TrailRepository;
import com.noahgeren.trailangel.dao.UserRepository;
import com.noahgeren.trailangel.domain.Park;
import com.noahgeren.trailangel.domain.Trail;
import com.noahgeren.trailangel.domain.User;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	ParkRepository parkRepo;
	
	@Autowired
	TrailRepository trailRepo;

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new User("417-773-9631"));
		List<Park> parks = parkRepo.saveAll(Arrays.asList(new Park("Yosemite"), new Park("Mark Twain")));
		List<Trail> trails = trailRepo.saveAll(Arrays.asList(new Trail("Bridalveil Falls", 4.2, 1), new Trail("Wildflower Trail", 10d, 2)));
	}

}
