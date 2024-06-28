package com.netec.productos.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netec.productos.configurations.security.service.AuthService;
import com.netec.productos.dto.inputs.LoginInDTO;
import com.netec.productos.dto.outputs.AuthResponseDTO;
import com.netec.productos.dto.outputs.ResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class LoginController {
	
	@Qualifier(value = "authService")
	private final AuthService authService;
	
	
	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody(required = true) LoginInDTO inDTO ) {
		AuthResponseDTO authResponseDTO = this.authService.login(inDTO);
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<AuthResponseDTO> response = new ResponseDTO<>(true, status.value(), "Acceso autorizado", authResponseDTO);
		return new ResponseEntity<>(response, status);
	}

}
