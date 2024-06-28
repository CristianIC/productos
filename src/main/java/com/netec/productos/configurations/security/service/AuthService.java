package com.netec.productos.configurations.security.service;

import com.netec.productos.dto.inputs.LoginInDTO;
import com.netec.productos.dto.outputs.AuthResponseDTO;

public interface AuthService {

	AuthResponseDTO login(LoginInDTO inDDTO);

}
