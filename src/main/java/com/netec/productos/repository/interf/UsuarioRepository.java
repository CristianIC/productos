package com.netec.productos.repository.interf;

import java.util.Optional;

import com.netec.productos.entities.Usuario;
import com.netec.productos.repository.custom.CustomRepository;

public interface UsuarioRepository extends CustomRepository<Usuario, Integer> {

	Optional<Usuario> getUsuarioWithRoles(String username);

}
