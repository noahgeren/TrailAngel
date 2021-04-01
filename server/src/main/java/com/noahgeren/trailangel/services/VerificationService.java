package com.noahgeren.trailangel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noahgeren.trailangel.dao.VerificationRepository;
import com.noahgeren.trailangel.domain.Verification;

@Service
public class VerificationService {
	
	private static final int CODE_LENGTH = 6;
	private static final String CODE_ALPHABET = "ABDEGJKLMNOPQRVWXYZ0123456789";
	
	@Autowired
	VerificationRepository verificationRepo;
	
	@Autowired
	TwilioService twilioService;
	
	public boolean verify(Verification verification) {
		if(verification.getPhoneNumber() == null || verification.getCode() == null) {
			return false;
		}
		Verification storedVerification = verificationRepo.findById(verification.getPhoneNumber()).orElse(null);
		if(storedVerification != null && verification.getCode().equalsIgnoreCase(storedVerification.getCode())) {
			verificationRepo.delete(storedVerification);
			return true;
		}
		return false;
	}
	
	public boolean sendVerification(String phoneNumber) {
		if(phoneNumber == null || !phoneNumber.startsWith("+1")) { // TODO: Check if valid phone number here
			return false;
		}
		final Verification v = verificationRepo.save(new Verification(phoneNumber, generateCode()));
//		System.out.println(v.getCode());
//		return true;
		return v != null ? twilioService.sendText(phoneNumber, "Trail Angel Verification Code: " + v.getCode()) : false;
	}
	
	private String generateCode() {
		StringBuilder code = new StringBuilder();
		for(int i = 0; i < CODE_LENGTH; i++) {
			code.append(CODE_ALPHABET.charAt((int) (Math.random() * CODE_ALPHABET.length())));
		}
		return code.toString();
	}

}
