package com.noahgeren.trailangel.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahgeren.trailangel.domain.User;
import com.noahgeren.trailangel.domain.Verification;
import com.noahgeren.trailangel.dto.GeneralResponse;
import com.noahgeren.trailangel.security.JWTAuthentication;
import com.noahgeren.trailangel.services.UserService;
import com.noahgeren.trailangel.services.VerificationService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	JWTAuthentication authentication;
	
	@Autowired
	UserService userService;
	
	@Autowired
	VerificationService verificationService;
	
	@PostMapping("/login/request")
	public GeneralResponse requestLogin(final @RequestBody(required = true) User user) {
		return new GeneralResponse()
				.set("sent", verificationService.sendVerification(user.getPhoneNumber()));
	}
	
	@PostMapping("/login")
	public GeneralResponse login(final @RequestBody(required = true) Verification verification) throws IOException {
		final boolean verified = verificationService.verify(verification);
		final GeneralResponse response = new GeneralResponse().set("login", verified);
		if(verified) {
			User user = userService.findUser(verification.getPhoneNumber());
			response.set("setup", user == null);
			if(user == null) {
				user = userService.createUser(verification.getPhoneNumber());
			}
			response.set("token", authentication.generateToken(user));
		}
		return response;
	}
	
	@PostMapping("/update")
	public GeneralResponse setupAccount(final Principal principal, final @RequestBody(required = true) User user) {
		user.setPhoneNumber(principal.getName());
		final User savedUser = userService.saveUser(user);
		return new GeneralResponse().set("saved", savedUser != null);
	}
	
	@GetMapping("/info")
	public User getUserInfo(final Principal principal, final TimeZone timezone) {
		System.out.println(timezone);
		return userService.findUser(principal.getName());
	}

}
