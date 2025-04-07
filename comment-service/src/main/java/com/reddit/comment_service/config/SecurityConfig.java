package com.reddit.comment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.reddit.comment_service.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);;
		return http.build();
	}
}
