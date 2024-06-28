package com.netec.productos.configurations.security.service;

import com.netec.productos.dto.inputs.LoginInDTO;
import com.netec.productos.dto.outputs.AuthResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

	AuthResponseDTO login(LoginInDTO inDDTO);
	
	AuthResponseDTO refreshToken(HttpServletRequest request);

}
