package com.noahgeren.trailangel.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.noahgeren.trailangel.domain.EmergencyContact;
import com.noahgeren.trailangel.domain.Hike;
import com.noahgeren.trailangel.domain.User;
import com.noahgeren.trailangel.services.EmergencyContactService;
import com.noahgeren.trailangel.services.HikeService;
import com.noahgeren.trailangel.services.TwilioService;
import com.noahgeren.trailangel.services.UserService;

@Component
public class HikeTasks {
	
	private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");
	private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("h:mm a M/d/yy");
	
	@Autowired
	TwilioService twilioService;
	
	@Autowired
	HikeService hikeService;
	
	@Autowired
	EmergencyContactService contactService;
	
	@Autowired
	UserService userService;
	
	private List<Long> lastSentStarting;
	
	private List<Long> lastSentEnding;

	@Scheduled(fixedRate = 30_000)
	public void sendScheduledHikeTexts() {
		final LocalDateTime start = LocalDateTime.now();
		final LocalDateTime end = LocalDateTime.now().plusSeconds(45);
		final List<Hike> startingHikes = hikeService.findByStartTime(start, end);
		final List<Hike> endingHikes = hikeService.findByEndTime(start, end);
		final List<Long> sentStarting = new ArrayList<>(startingHikes.size());
		final List<Long> sentEnding = new ArrayList<>(endingHikes.size());
		for(final Hike hike : startingHikes) {
			if(lastSentStarting.contains(hike.getId())) continue;
			final User user = userService.findUser(hike.getUser());
			final List<EmergencyContact> contacts = contactService.findByUser(hike.getUser());
			for(final EmergencyContact contact : contacts) {
				String startTime = dateTimeFormat.format(hike.getStartTime());
				String endTime = dateTimeFormat.format(hike.getEndTime());
				if(hike.getStartTime().toLocalDate().isEqual(hike.getEndTime().toLocalDate())) {
					startTime = timeFormat.format(hike.getStartTime());
					endTime = timeFormat.format(hike.getEndTime());
				}
				twilioService.sendText(contact.getPhoneNumber(), String.format("TrailAngel - %s (%s) is hiking %s at %s and should be done at %s.", user.getName(), user.getTrailName(), hike.getTrailName(), startTime, endTime));
				sentStarting.add(hike.getId());
			}
		}
		for(final Hike hike : endingHikes) {
			if(lastSentEnding.contains(hike.getId())) continue;
			final User user = userService.findUser(hike.getUser());
			final List<EmergencyContact> contacts = contactService.findByUser(hike.getUser());
			for(final EmergencyContact contact : contacts) {
				twilioService.sendText(contact.getPhoneNumber(), 
						String.format(
								"TrailAngel - %s (%s) should be finishing hiking %s soon. If you do not hear from them soon and cannot contact them, please consider contacting emergency services.",
								user.getName(), user.getTrailName(), hike.getTrailName()));
				sentEnding.add(hike.getId());
			}
		}
		lastSentStarting = sentStarting;
		lastSentEnding = sentEnding;
	}
	
}
