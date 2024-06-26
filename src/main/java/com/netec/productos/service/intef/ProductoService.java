package com.netec.productos.service.intef;

import java.util.List;

import com.netec.productos.dto.inputs.ProductoInDTO;
import com.netec.productos.dto.outputs.PaginationDTO;
import com.netec.productos.dto.outputs.ProductoDTO;

public interface ProductoService {

	void save(ProductoInDTO inDTO);

	ProductoDTO update(ProductoInDTO inDTO);

	ProductoDTO getById(Integer id);

	boolean delete(Integer id);

	List<ProductoDTO> getAll();

	PaginationDTO<ProductoDTO> getPaginacion(int currentPage);

}
