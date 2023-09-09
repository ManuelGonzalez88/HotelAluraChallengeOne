package com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotelalura.modelo.Usuario;

public class UsuarioDao {
	
	final private Connection con;
	
	public UsuarioDao(Connection con) {
		this.con = con;
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM usuarios"
					);
			try(statement) {
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet) {
					while (resultSet.next()) {
						Usuario usuario = new Usuario(resultSet.getString("usuario"),
								resultSet.getString("contraseña"));
						
						usuarios.add(usuario);
						
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

}
