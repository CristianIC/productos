package com.netec.productos.repository.interf;

import java.util.List;

import com.netec.productos.entities.Producto;
import com.netec.productos.repository.custom.CustomRepository;

public interface ProductoRepository extends CustomRepository<Producto, Integer> {
	
	List<Producto> getAllOrderAscByNombre();
	
	List<Producto> getPaginacion(int currentPage, int paseSize);

}
