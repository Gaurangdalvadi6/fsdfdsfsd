package com.management.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
 
@Component
public class JWTUtils {
	
	private final SecretKey Key;

	private static final Long EXPIRATION_TIME = 8640000L; // 24 HOURS
 
	public JWTUtils() {
		String secretString = "hfisa43bhas432ihgrhgdfga7y43rskjahdf78grwbjq78sagfabfdfsdjkbfas8934bakgfd8ygafd8r2guira87sfdabjdf738tasf";
		byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
		this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
	}
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(Key)
				.compact();
	}
	
	public String generateRefreshToken(HashMap<String, Object> claims,UserDetails userDetails) {
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(Key)
				.compact();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public <T> T extractClaims(String token,Function<Claims, T> claimsFunction){
		return claimsFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractClaims(token,Claims::getExpiration).before(new Date());
	}
	
	
}
