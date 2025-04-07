package com.reddit.user_service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	private UserDto mapToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmail(user.getUserEmail());
		return userDto;
	}
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, com.reddit.user_service.JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setUserEmail(registerRequest.getEmail());
		user.setUserPassword(encodePassword(registerRequest.getPassword()));
		user.setCreationTime(Instant.now());
		userRepository.save(user);
		
	}
	@Transactional
	public User getUser() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} 
		else {
			username = principal.toString();
		}
		return userRepository.findByUserName(username).orElseThrow(() -> new UserServiceException("User error security context"));
	}
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken = jwtProvider.generateToken(authenticate);
		return new AuthenticationResponse(authenticationToken,loginRequest.getUsername());	
	}
	public String getPublicKey() {
		Key publicKey = jwtProvider.getPublicKey();
		String publicKeyEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		return publicKeyEncoded;
	}
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserServiceException("Invalid User ID"+userId));
		return mapToDto(user);
	}
	public Long getCurrentUserId() {
		return getUser().getUserId();
	}
	
}
