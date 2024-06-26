package com.netec.productos.mappers;

import java.util.List;

import com.netec.productos.dto.inputs.ProductoInDTO;
import com.netec.productos.dto.outputs.ProductoDTO;
import com.netec.productos.entities.Producto;

public class ProductoMapper {
	
	public static Producto toEntity(ProductoInDTO inDTO) {
		Producto entity = new Producto();
		entity.setId(inDTO.getId());
		entity.setNombre(inDTO.getNombre().strip());
		entity.setDescripcion(inDTO.getDescripcion());
		entity.setPrecio(inDTO.getPrecio());
		return entity;
	}
	
	public static ProductoDTO toDto(Producto entity) {
		ProductoDTO dto = new ProductoDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setDescripcion(entity.getDescripcion());
		dto.setPrecio(entity.getPrecio());
		dto.setEstado(entity.getEstado());
		return dto;
	}
	
	public static List<ProductoDTO> toDtos(List<Producto> entities) {
		return entities.stream().map(ProductoMapper::toDto).toList();
	}

}
