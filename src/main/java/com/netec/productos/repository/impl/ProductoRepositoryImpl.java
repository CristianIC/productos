package com.netec.productos.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.netec.productos.entities.Producto;
import com.netec.productos.repository.custom.CustomRepositoryImpl;
import com.netec.productos.repository.interf.ProductoRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository(value = "productoRepository")
public class ProductoRepositoryImpl extends CustomRepositoryImpl<Producto, Integer> implements ProductoRepository {

	@Override
	public List<Producto> getAllOrderAscByNombre() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> root = query.from(Producto.class);
		query.select(root).orderBy(builder.asc(root.get("nombre")));
		return this.entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Producto> getPaginacion(int currentPage, int paseSize) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> root = query.from(Producto.class);
		query.select(root).orderBy(builder.asc(root.get("nombre")));
		return this.entityManager.createQuery(query).setFirstResult((currentPage - 1) * paseSize)
				.setMaxResults(paseSize).getResultList();
	}

}
