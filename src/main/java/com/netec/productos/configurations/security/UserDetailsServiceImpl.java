package com.netec.productos.configurations.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.netec.productos.entities.Usuario;
import com.netec.productos.enums.EstadoUEnum;
import com.netec.productos.service.intef.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Qualifier(value = "usuarioServiceImpl")
	private final UsuarioService usuarioService;

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = this.usuarioService.getUser(username);

		boolean estadoUsuario = usuario.getEstado().equals(EstadoUEnum.ACTIVO);

		return new User(usuario.getUsername(), usuario.getPassword(), estadoUsuario, true, true, true,
				this.getGrantedAuthorities(usuario));

	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Usuario usuario) {
		Set<GrantedAuthority> authorities = usuario.getUsuarioRoles().stream()
				.map(usuarioRol -> new SimpleGrantedAuthority("ROLE_".concat(usuarioRol.getRole().getNombre())))
				.collect(Collectors.toSet());
		return authorities;
	}

}
