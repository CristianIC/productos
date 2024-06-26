package com.netec.productos.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netec.productos.dto.inputs.ProductoInDTO;
import com.netec.productos.dto.outputs.PaginationDTO;
import com.netec.productos.dto.outputs.ProductoDTO;
import com.netec.productos.entities.Producto;
import com.netec.productos.enums.EstadoEnum;
import com.netec.productos.exceptions.ValidParameterException;
import com.netec.productos.mappers.ProductoMapper;
import com.netec.productos.repository.interf.ProductoRepository;
import com.netec.productos.service.intef.ProductoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "productoService")
public class ProductoServiceImpl implements ProductoService {

	@Qualifier(value = "productoRepository")
	private final ProductoRepository productoRepository;

	private int pageSize = 10;

	@Transactional
	@Override
	public void save(ProductoInDTO inDTO) {
		Producto entity = ProductoMapper.toEntity(inDTO);
		entity.setEstado(EstadoEnum.Disponible);
		this.productoRepository.save(entity);
	}

	@Transactional
	@Override
	public ProductoDTO update(ProductoInDTO inDTO) {
		if (Objects.isNull(inDTO.getId())) {
			throw new ValidParameterException("Establezca id");
		}
		Optional<Producto> op = this.productoRepository.getById(inDTO.getId());
		if (op.isPresent()) {
			op.get().setNombre(inDTO.getNombre());
			op.get().setDescripcion(inDTO.getDescripcion());
			op.get().setPrecio(inDTO.getPrecio());
			;
			Producto entity = this.productoRepository.update(op.get());
			return ProductoMapper.toDto(entity);
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public ProductoDTO getById(Integer id) {
		Optional<Producto> op = this.productoRepository.getById(id);
		if (op.isPresent()) {
			return ProductoMapper.toDto(op.get());
		}
		return null;
	}

	@Transactional
	@Override
	public boolean delete(Integer id) {
		Optional<Producto> op = this.productoRepository.getById(id);
		if (op.isPresent()) {
			this.productoRepository.delete(op.get());
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductoDTO> getAll() {
		List<Producto> lista = this.productoRepository.getAllOrderAscByNombre();
		return ProductoMapper.toDtos(lista);
	}

	@Transactional(readOnly = true)
	@Override
	public PaginationDTO<ProductoDTO> getPaginacion(int currentPage) {
		long totalElements = this.productoRepository.countAll();
		if (totalElements == 0) {
			return new PaginationDTO<>();
		}
		List<Producto> listaEntities = this.productoRepository.getPaginacion(currentPage, pageSize);
		List<ProductoDTO> listaDTOs = ProductoMapper.toDtos(listaEntities);
		return new PaginationDTO<>(currentPage, totalElements, pageSize, listaDTOs);
	}

}
