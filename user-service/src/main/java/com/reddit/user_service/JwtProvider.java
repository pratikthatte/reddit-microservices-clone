package com.reddit.user_service;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
	private KeyStore keystore;
	
	@PostConstruct
	public void init() {
		try {
			keystore = KeyStore.getInstance("JKS");
			InputStream resourceStream = getClass().getResourceAsStream("/redditclonejwtkeystore.jks");
			char[] pwdArray = "password".toCharArray();
			keystore.load(resourceStream, pwdArray);
		}
		catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e){
			throw new UserServiceException("Error loading keystore",e);
		}
	}
	public String generateToken(Authentication authenticate) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
		return Jwts.builder().subject(principal.getUsername()).signWith(getPrivateKey()).compact();
	}
	private Key getPrivateKey() {
		try {
			return keystore.getKey("jwtkey", "password".toCharArray());
		}
		catch(KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
			throw new UserServiceException("Error loading private key from keystore",e);
		}
	}
	public Key getPublicKey() {
		try {
			return keystore.getCertificate("jwtkey").getPublicKey();
		}
		catch(KeyStoreException e) {
			throw new UserServiceException("Error loading public key from keystore",e);
		}
	}
	public boolean validate(String jwt) {
		try{
			Jwts.parser().setSigningKey(getPublicKey()).build().parseSignedClaims(jwt);
			return true;
		}
		catch(JwtException | IllegalArgumentException e){
			throw new UserServiceException("Error validating JWT",e);
		}
	}
	public String getUsernameFromJWT(String jwt) {
		return Jwts.parser().setSigningKey(getPublicKey()).build().parseSignedClaims(jwt).getPayload().getSubject();
	}
}
