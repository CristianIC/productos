package com.netec.productos.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.netec.productos.entities.Usuario;
import com.netec.productos.entities.UsuarioRol;
import com.netec.productos.repository.custom.CustomRepositoryImpl;
import com.netec.productos.repository.interf.UsuarioRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Repository(value = "usuarioRepository")
public class UsuarioRepositoryImpl extends CustomRepositoryImpl<Usuario, Integer> implements UsuarioRepository {

	@Override
	public Optional<Usuario> getUsuarioWithRoles(String username) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		Fetch<Usuario, UsuarioRol> usuarioRol = root.fetch("usuarioRoles", JoinType.LEFT);
		usuarioRol.fetch("role", JoinType.LEFT);
		query.select(root).where(builder.equal(builder.lower(root.get("username")), username.strip().toLowerCase()));
		return this.entityManager.createQuery(query).getResultList().stream().findFirst();
	}

}
