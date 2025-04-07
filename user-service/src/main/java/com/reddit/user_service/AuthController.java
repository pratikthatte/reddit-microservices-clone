package com.reddit.user_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {
	private AuthService authService;
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	@PostMapping("/signup")
	public ResponseEntity<String> singup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<String>("User signup successful.",HttpStatus.OK);
	}
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	@GetMapping("/publicKey")
	public ResponseEntity<String> getPublicKey(){
		return ResponseEntity.ok(authService.getPublicKey());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable Long userId){
		return ResponseEntity.ok(authService.getUserById(userId));
	}
	@GetMapping("/current-user-id")
	public ResponseEntity<Long> getCurrentUserId(){
		return ResponseEntity.ok(authService.getCurrentUserId());
	}
}
