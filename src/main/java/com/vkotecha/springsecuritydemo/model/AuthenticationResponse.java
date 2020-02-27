package com.vkotecha.springsecuritydemo.model;

/**
 * @author Vishal Kotecha
 */
public class AuthenticationResponse {

	private String token;

	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
