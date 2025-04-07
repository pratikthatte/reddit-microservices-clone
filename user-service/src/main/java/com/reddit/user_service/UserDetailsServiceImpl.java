package com.reddit.user_service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = (User) userRepository.findByUserName(username).orElseThrow(() -> new UserServiceException("Username not found: "+username));
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPassword(),true,true, true, true, getAuthorites("USER"));
	}
	private Collection<? extends GrantedAuthority> getAuthorites(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}
}