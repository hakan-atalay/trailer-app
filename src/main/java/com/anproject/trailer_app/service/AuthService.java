package com.anproject.trailer_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.AuthRequestDTO;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	public String authenticateAndGetToken(AuthRequestDTO authRequestDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDto.getNickname(), authRequestDto.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequestDto.getNickname());
		} else {
			throw new UsernameNotFoundException("Kullanıcı adı veya şifre hatalı.");
		}
	}
}
