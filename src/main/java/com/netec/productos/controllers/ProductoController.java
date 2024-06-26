package com.netec.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netec.productos.dto.inputs.ProductoInDTO;
import com.netec.productos.dto.outputs.PaginationDTO;
import com.netec.productos.dto.outputs.ProductoDTO;
import com.netec.productos.dto.outputs.ResponseDTO;
import com.netec.productos.service.intef.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/productos")
public class ProductoController {

	@Qualifier(value = "productoService")
	private final ProductoService productoService;

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<List<ProductoDTO>>> getAll() {
		List<ProductoDTO> lista = this.productoService.getAll();
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<List<ProductoDTO>> response = new ResponseDTO<>(true, status.value(), "Operacion exitosa", lista);
		return new ResponseEntity<>(response, status);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<String>> save(@RequestBody(required = true) @Valid ProductoInDTO inDTO) {
		this.productoService.save(inDTO);
		HttpStatus status = HttpStatus.CREATED;
		ResponseDTO<String> response = new ResponseDTO<>(true, status.value(), "Operacion existosa");
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<ProductoDTO>> update(@RequestBody(required = true) @Valid ProductoInDTO inDTO) {
		ProductoDTO dto = this.productoService.update(inDTO);
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<ProductoDTO> response = new ResponseDTO<>(true, status.value(), "Operacion existosa", dto);
		return new ResponseEntity<>(response, status);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<ProductoDTO>> getByID(@PathVariable(value = "id", required = true) Integer id) {
		ProductoDTO dto = this.productoService.getById(id);
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<ProductoDTO> response = new ResponseDTO<>(true, status.value(), "Operacion existosa", dto);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable(value = "id", required = true) Integer id) {
		boolean dto = this.productoService.delete(id);
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<Boolean> response = new ResponseDTO<>(true, status.value(), "Operacion existosa", dto);
		return new ResponseEntity<>(response, status);
	}

	@GetMapping(path = "/page/{numberPage}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<PaginationDTO<ProductoDTO>>> getPagination(
			@PathVariable(value = "numberPage", required = true) int numberPage) {
		PaginationDTO<ProductoDTO> pagination = this.productoService.getPaginacion(numberPage);
		HttpStatus status = HttpStatus.OK;
		ResponseDTO<PaginationDTO<ProductoDTO>> response = new ResponseDTO<>(true, status.value(), "Operacion exitosa",
				pagination);
		return new ResponseEntity<>(response, status);
	}

}
