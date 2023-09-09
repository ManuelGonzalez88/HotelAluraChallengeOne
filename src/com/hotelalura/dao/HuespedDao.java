package com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotelalura.modelo.Huesped;

public class HuespedDao {
	
	final private Connection con;
	
	public HuespedDao(Connection con) {	
		this.con = con; 
	}
	
	public void guardarHuesped(Huesped huesped) {
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes "
					+ "(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva)"
					+ "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			try (statement) {
				
				java.util.Date utilDate = huesped.getFecha_de_nacimiento();
			    java.sql.Date entrada = new java.sql.Date(utilDate.getTime());
				
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, entrada);
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId_reserva());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet) {
					
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format(
								"Se registro correctamente el huesped numero %s", 
								huesped.getId()));
					}
					
				}
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> cargarHuespedes() {
		List<Huesped> huespedes = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM huespedes ");
			
			huespedes = ejecutarBusqueda(statement, huespedes);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	}

	public List<Huesped> buscarHuesped(String apellido) {
		List<Huesped> huespedes = new ArrayList<>();
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM huespedes WHERE apellido = ? ");
			statement.setString(1, apellido);
			
			huespedes = ejecutarBusqueda(statement, huespedes);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return huespedes;
	} 
	
	private List<Huesped> ejecutarBusqueda(PreparedStatement statement,List<Huesped> huespedes) throws SQLException{
		try(statement) {
			statement.execute();
			
			final ResultSet resultSet = statement.getResultSet();
			
			try(resultSet) {
				
				while(resultSet.next()) {
					
					java.sql.Date sqlDate = resultSet.getDate("fecha_de_nacimiento");
					java.util.Date fechaNacimiento = new java.util.Date(sqlDate.getTime());
					
					Huesped huesped= new Huesped(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							fechaNacimiento,
							resultSet.getString("nacionalidad"),
							resultSet.getString("telefono"),
							resultSet.getInt("id_reserva"));
					huespedes.add(huesped);
					
				}
			}
		}
		return huespedes;
	}

	public void editar(Huesped huesped) {
		
		try {
			
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huespedes SET nombre = ?, apellido = ?, fecha_de_nacimiento = ?, "
					+ "nacionalidad = ?, telefono = ? WHERE id = ?");
			
			try(statement) {
				
				java.util.Date utilDate = huesped.getFecha_de_nacimiento();
				java.sql.Date fecha = new java.sql.Date(utilDate.getTime());
				
				statement.setString(1,huesped.getNombre());
				statement.setString(2,huesped.getApellido());
				statement.setDate(3,fecha);
				statement.setString(4,huesped.getNacionalidad());
				statement.setString(5,huesped.getTelefono());
				statement.setInt(6, huesped.getId());
				
				statement.execute();
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}

