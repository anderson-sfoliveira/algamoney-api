package com.example.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.algamoney.api.model.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
		
		System.out.println("UsuarioSistema -> UsuarioSistema()");
	}

	public Usuario getUsuario() {
		System.out.println("UsuarioSistema -> getUsuario()");
		return usuario;
	}

}