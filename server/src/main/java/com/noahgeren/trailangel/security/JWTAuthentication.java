package com.noahgeren.trailangel.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.noahgeren.trailangel.domain.User;

@Component
public class JWTAuthentication {
	
	private static final int EXPIRATION_DAYS = 10;

	@Value("${jwt.secretkey}")
	private String secretKey;
	
	public String generateToken(User user) {
		return JWT.create()
                .withSubject(user.getPhoneNumber())
                .withExpiresAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("UTC-6")).plusDays(EXPIRATION_DAYS).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
	}

}
