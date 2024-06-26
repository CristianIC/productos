package com.netec.productos.dto.outputs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaginationDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3811794157260697094L;

	private int currentPage;

	private int numberOfElementsPerPage;

	private int pageSize;

	private long totalElements;

	private int totalPages;

	private List<T> elements = new ArrayList<>();

	public PaginationDTO(int currentPage, long totalElements, int numberOfElementsPerPage, List<T> elements) {
		this.currentPage = currentPage;
		this.totalElements = totalElements;
		this.numberOfElementsPerPage = numberOfElementsPerPage;
		this.elements = elements;
		this.calcularPageSize();
		this.calcularTotalPages();
	}

	private void calcularTotalPages() {
		this.totalPages = (int) (this.totalElements / this.numberOfElementsPerPage)
				+ (this.totalElements % this.numberOfElementsPerPage > 0 ? 1 : 0);
	}

	private void calcularPageSize() {
		this.pageSize = this.elements.size();
	}

}
