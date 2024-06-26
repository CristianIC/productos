package com.netec.productos.exceptions.advice;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.netec.productos.dto.outputs.AtributoValidationDTO;
import com.netec.productos.dto.outputs.ResponseDTO;
import com.netec.productos.exceptions.ValidParameterException;

@RestControllerAdvice
public class ResponseExcepcion {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO<List<AtributoValidationDTO>>> handledMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseDTO<List<AtributoValidationDTO>> response = new ResponseDTO<>(false, status.value(),
				"Complete los correctamente", this.obtenerErrorsCampos(ex.getBindingResult().getFieldErrors()));
		return new ResponseEntity<>(response, status);
	}

	private List<AtributoValidationDTO> obtenerErrorsCampos(List<FieldError> errores) {
		return errores.stream().map(errorField -> {
			AtributoValidationDTO dto = new AtributoValidationDTO();
			dto.setNombreCampo(errorField.getField());
			dto.setMessage(errorField.getDefaultMessage());
			return dto;
		}).toList();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseDTO<List<AtributoValidationDTO>>> handledConstraintViolationException(
			ConstraintViolationException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseDTO<List<AtributoValidationDTO>> response = new ResponseDTO<>(false, status.value(), ex.getMessage());
		return new ResponseEntity<>(response, status);
	}

	@ExceptionHandler(ValidParameterException.class)
	public ResponseEntity<ResponseDTO<String>> handledValidParameterException(ValidParameterException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseDTO<String> response = new ResponseDTO<>(false, status.value(), ex.getMessage());
		return new ResponseEntity<>(response, status);
	}

}
