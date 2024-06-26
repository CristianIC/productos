package com.netec.productos.dto.outputs;

import java.io.Serializable;
import java.math.BigDecimal;

import com.netec.productos.enums.EstadoEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3795450754393431361L;

	private Integer id;

	private String nombre;

	private String descripcion;

	private BigDecimal precio;

	private EstadoEnum estado;

}
