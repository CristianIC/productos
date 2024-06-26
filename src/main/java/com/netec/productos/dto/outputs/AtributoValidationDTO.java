package com.netec.productos.dto.outputs;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtributoValidationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1795537941890612290L;

	private String nombreCampo;

	private String message;

}
