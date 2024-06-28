package com.netec.productos.entities;

import org.hibernate.annotations.DynamicUpdate;

import com.netec.productos.enums.EstadoUEnum;

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
@Table(name = "roles", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "estado", nullable = false)
	private EstadoUEnum estado;

}
