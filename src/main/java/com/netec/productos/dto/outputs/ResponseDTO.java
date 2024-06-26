package com.netec.productos.dto.outputs;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7255159456542043155L;

	private boolean status;

	private int codeStatus;

	private String message;

	private T data;

	public ResponseDTO(boolean status, int codeStatus, String message) {
		this.status = status;
		this.codeStatus = codeStatus;
		this.message = message;
	}

	public ResponseDTO(boolean status, int codeStatus, String message, T data) {
		this.status = status;
		this.codeStatus = codeStatus;
		this.message = message;
		this.data = data;
	}
}
