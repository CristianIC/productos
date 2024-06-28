package com.netec.productos.configurations.security.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.netec.productos.configurations.security.jwt.JwtService;
import com.netec.productos.dto.inputs.LoginInDTO;
import com.netec.productos.dto.outputs.AuthResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "authService")
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;

	@Qualifier(value = "userDetailsServiceImpl")
	private final UserDetailsService userDetailsService;

	@Qualifier(value = "jwtService")
	private final JwtService jwtService;

	@Override
	public AuthResponseDTO login(LoginInDTO inDTO) {
		String username = inDTO.getUsername();
		String password = inDTO.getPassword();
		Authentication authentication;

		authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		String token = this.jwtService.generateAccessToken(userDetails);
		String refreshToken = this.jwtService.generateRefreshToken(userDetails);

		return new AuthResponseDTO(username, token, refreshToken);
	}

	@Override
	public AuthResponseDTO refreshToken(HttpServletRequest request) {
		String token = null;
		String authHeder = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (Objects.nonNull(authHeder) && authHeder.startsWith("Bearer ")) {
			token = authHeder.substring(7);
		}
		String username = this.jwtService.extractUsernameFromToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
				null, userDetails.getAuthorities());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		String accessToken = this.jwtService.generateAccessToken(userDetails);
		String refreshToken = this.jwtService.generateRefreshToken(userDetails);

		return new AuthResponseDTO(username, accessToken, refreshToken);
	}

}
