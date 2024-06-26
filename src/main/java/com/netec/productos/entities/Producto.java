package com.netec.productos.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;

import com.netec.productos.enums.EstadoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "productos", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Producto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3832788177079330484L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio", nullable = false)
	private BigDecimal precio;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoEnum estado;

}
