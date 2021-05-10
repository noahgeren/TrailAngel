package com.noahgeren.trailangel.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahgeren.trailangel.domain.EmergencyContact;
import com.noahgeren.trailangel.services.EmergencyContactService;
import com.noahgeren.trailangel.services.UserService;

@RestController
@RequestMapping("/contact")
public class EmergencyContactController {

	@Autowired
	UserService userService;
	
	@Autowired
	EmergencyContactService contactService;
	
	@GetMapping("/all")
	public List<EmergencyContact> findAllUserContacts(final Principal principal) {
		if(principal != null) {
			return contactService.findByUser(principal.getName());
		}
		return null;
	}
	
	@PostMapping("/save")
	public EmergencyContact saveContact(final Principal principal, @RequestBody final EmergencyContact contact) {
		if(principal == null) return null;
		if(contact.getId() != null) {
			final EmergencyContact oldContact = contactService.findById(contact.getId());
			if(oldContact != null && !oldContact.getUser().equals(principal.getName())) {
				return null;
			}
			contact.setUser(oldContact.getUser());
		} else {
			contact.setUser(principal.getName());
		}
		return contactService.save(contact);
	}
	
	@PostMapping("/delete")
	public EmergencyContact deleteContact(final Principal principal, @RequestBody EmergencyContact contact) {
		if(principal == null) return null;
		if(contact.getId() != null) {
			contact = contactService.findById(contact.getId());
			if(contact != null && contact.getUser().equals(principal.getName())) {
				contactService.deleteById(contact.getId());
				return contact;
			}
		}
		return null;
	}

	
}
