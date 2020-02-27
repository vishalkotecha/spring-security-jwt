package com.vkotecha.springsecuritydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vkotecha.springsecuritydemo.config.DomainUserDetailService;
import com.vkotecha.springsecuritydemo.model.AuthenticationRequest;
import com.vkotecha.springsecuritydemo.model.AuthenticationResponse;
import com.vkotecha.springsecuritydemo.util.JwtUtil;

/**
 * @author Vishal Kotecha
 */
@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DomainUserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest req) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(req.getUsername());
		
		String token = jwtUtil.generateToken(userDetails.getUsername());
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
}