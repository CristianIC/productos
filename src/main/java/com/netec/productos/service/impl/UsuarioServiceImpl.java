package com.netec.productos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netec.productos.entities.Usuario;
import com.netec.productos.repository.interf.UsuarioRepository;
import com.netec.productos.service.intef.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Qualifier(value = "usuarioRepository")
	private final UsuarioRepository usuarioRepository;

	@Transactional(readOnly = true)
	@Override
	public Usuario getUser(String username) {
		Optional<Usuario> op = this.usuarioRepository.getUsuarioWithRoles(username);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

}
