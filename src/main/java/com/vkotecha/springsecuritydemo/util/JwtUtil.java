package com.vkotecha.springsecuritydemo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Vishal Kotecha
 */
@Component
public class JwtUtil {

	private static final String SECRET = "secret";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(userName)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 30 * 30 * 10))
			.signWith(SignatureAlgorithm.HS256, SECRET)
			.compact();
		
	}

	public String extractUserName(String jwt) {
		return Jwts.parser()
		.setSigningKey(SECRET)
		.parseClaimsJws(jwt)
		.getBody().getSubject();
	}
	
	public boolean isTokenExpired(String jwt){
		return Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt)
				.getBody()
				.getExpiration()
				.before(new Date());
	}
	
	public boolean isValidToken(String jwt, UserDetails userDetails) {
			String userName = extractUserName(jwt);
			return userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
	}

}

