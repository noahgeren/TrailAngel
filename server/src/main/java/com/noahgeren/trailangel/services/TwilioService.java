package com.noahgeren.trailangel.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {
	
	private static final PhoneNumber FROM = new PhoneNumber("+14159655996");
	
	@Value("${TWILIO_ACCOUNT_SID}")
	private String accountSid;
	
	@Value("${TWILIO_AUTH_TOKEN}")
	private String authToken;
	
	@PostConstruct
	public void init() {
		Twilio.init(accountSid, authToken);
	}
	
	public boolean sendText(String to, String body) {
		return Message.creator(new PhoneNumber(to), FROM, body).create().getErrorCode() == null;
	}

}
