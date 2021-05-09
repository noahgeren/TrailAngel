package com.noahgeren.trailangel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.EmergencyContactRepository;
import com.noahgeren.trailangel.domain.EmergencyContact;
import com.noahgeren.trailangel.domain.User;

@Service
public class EmergencyContactService {
	
	@Autowired
	EmergencyContactRepository contactRepo;

	public EmergencyContact save(final EmergencyContact contact) {
		return contactRepo.save(contact);
	}
	
	public EmergencyContact findById(final Long id) {
		return contactRepo.findById(id).orElse(null);
	}
	
	public void deleteById(final Long id) {
		contactRepo.deleteById(id);
	}
	
	public List<EmergencyContact> findByUser(final String user) {
		return contactRepo.findByUserOrderByIdAsc(user);
	}

}
