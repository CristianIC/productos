package com.netec.productos.repository.custom;

import java.io.Serializable;
import java.util.Optional;

public interface CustomRepository<T, K extends Serializable> {

	void save(T entity);

	T update(T entity);

	Optional<T> getById(K id);

	Long countAll();
	
	void delete(T entity);

}
