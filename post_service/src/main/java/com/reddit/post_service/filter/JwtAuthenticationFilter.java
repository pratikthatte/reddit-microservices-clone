package com.reddit.post_service.filter;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reddit.post_service.exception.PostServiceException;
import com.reddit.post_service.service.UserServiceClient;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
		private final UserServiceClient userServiceClient;
		private final UserDetailsService userDetailsService;
		public JwtAuthenticationFilter(UserServiceClient userServiceClient, UserDetailsService userDetailsService) {
			super();
			this.userServiceClient = userServiceClient;
			this.userDetailsService = userDetailsService;
		}
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			String jwt = getJwtFromRequest(request);
			validateJwt(jwt,request);
			filterChain.doFilter(request, response);		
		}
		private void validateJwt(String jwt,HttpServletRequest request) {
			try{
				 String username =Jwts.parser().setSigningKey(getPublicKey()).build().parseSignedClaims(jwt).getPayload().getSubject();
				 UserDetails jwtUserDetails = User.withUsername(username)
				            .password("")
				            .authorities("ROLE_USER")
				            .build();				 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtUserDetails,jwt,jwtUserDetails.getAuthorities());
				 authenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			catch(JwtException | IllegalArgumentException e){
				throw new PostServiceException("Error validating JWT",e);
			}
			
		}
		private Key getPublicKey() {
			try {
				String keyString = userServiceClient.getPublicKey();
				byte[] decodedKey = Base64.getDecoder().decode(keyString);
		        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		        return keyFactory.generatePublic(keySpec);
			}
			catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
				throw new PostServiceException("Error validating JWT "+e);
			}	
		}
		private String getJwtFromRequest(HttpServletRequest request) {
			String bearerRequest = request.getHeader("Authorization");
			if(StringUtils.hasText(bearerRequest) && bearerRequest.startsWith("Bearer ")) {
				return bearerRequest.substring(7);
			}
			return bearerRequest;
		}
}
