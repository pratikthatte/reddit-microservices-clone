package com.reddit.user_service;

public class AuthenticationResponse {
	String authenticationToken;
	String username;
	public AuthenticationResponse(String authenticationToken, String username) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
	}
	/**
	 * @return the authenticationToken
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
}
