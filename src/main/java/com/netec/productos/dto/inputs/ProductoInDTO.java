package com.netec.productos.dto.inputs;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductoInDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4041399829056120601L;

	private Integer id;

	@NotBlank(message = "Ingrese nombre")
	private String nombre;

	private String descripcion;

	@NotNull(message = "Ingrese precio")
	private BigDecimal precio;

}
