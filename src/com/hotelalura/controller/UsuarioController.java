package com.hotelalura.controller;

import java.util.List;

import com.hotelalura.dao.UsuarioDao;
import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelo.Usuario;

public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioController() {
		this.usuarioDao = new UsuarioDao(new ConnectionFactory().recuperarConexion());
	}

	public List<Usuario> listarUsuarios() {
		return usuarioDao.listarUsuarios();
	}
}
