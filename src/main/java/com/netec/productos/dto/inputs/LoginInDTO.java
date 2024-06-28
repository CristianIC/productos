package com.netec.productos.dto.inputs;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginInDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4561524884277950046L;

	private String username;

	private String password;

}
