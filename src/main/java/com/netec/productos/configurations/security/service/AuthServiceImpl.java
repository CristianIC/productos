package com.netec.productos.configurations.security.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.netec.productos.configurations.security.jwt.JwtService;
import com.netec.productos.dto.inputs.LoginInDTO;
import com.netec.productos.dto.outputs.AuthResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "authService")
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	
	@Qualifier(value = "jwtService")
	private final JwtService jwtService;

	@Override
	public AuthResponseDTO login(LoginInDTO inDTO) {
		String username = inDTO.getUsername();
		String password = inDTO.getPassword();
		Authentication authentication;
		
		authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String token = this.jwtService.generateToken(userDetails);
		
		return new AuthResponseDTO(username, token);
	}

}
