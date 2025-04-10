package com.reddit.user_service;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	private JwtProvider jwtProvider;
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
		super();
		this.jwtProvider = jwtProvider;
		this.userDetailsService = userDetailsService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getJwtFromRequest(request);
		if(StringUtils.hasText(jwt) && jwtProvider.validate(jwt)) {
			String username = jwtProvider.getUsernameFromJWT(jwt);
			UserDetails jwtUserDetails = (UserDetails) userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUserDetails,null,jwtUserDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerRequest = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerRequest) && bearerRequest.startsWith("Bearer ")) {
			return bearerRequest.substring(7);
		}
		return bearerRequest;
	}

}
