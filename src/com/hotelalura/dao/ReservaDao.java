package com.hotelalura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotelalura.modelo.Reserva;

public class ReservaDao {

	final private Connection con;

	public ReservaDao(Connection con) {
		this.con = con;
	}

	public void guardarReserva(Reserva reserva) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago) " + "VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {

				java.util.Date utilDate = reserva.getFechaEntrada();
				java.sql.Date entrada = new java.sql.Date(utilDate.getTime());

				java.util.Date utilDate2 = reserva.getFechaSalida();
				java.sql.Date salida = new java.sql.Date(utilDate2.getTime());

				statement.setDate(1, entrada);
				statement.setDate(2, salida);
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(
								String.format("Se registro correctamente la reserva numero %s",
										reserva.getId()));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> cargarReservas() {
		List<Reserva> reservas = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas ");

			reservas = ejecutarBusqueda(statement, reservas);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	public List<Reserva> buscarReserva(String id) {

		List<Reserva> reservas = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT * FROM reservas WHERE id = ? ");
			statement.setString(1, id);

			reservas = ejecutarBusqueda(statement, reservas);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}
	
	private List<Reserva> ejecutarBusqueda(PreparedStatement statement, List<Reserva> reservas) throws SQLException{
		
		try (statement) {
			statement.execute();

			final ResultSet resultSet = statement.getResultSet();

			try (resultSet) {
				while (resultSet.next()) {

					java.sql.Date sqlDate = resultSet.getDate("fechaEntrada");
					java.util.Date entrada = new java.util.Date(sqlDate.getTime());
					java.sql.Date sqlDate2 = resultSet.getDate("fechaSalida");
					java.util.Date salida = new java.util.Date(sqlDate2.getTime());

					

					Reserva reserva = new Reserva(resultSet.getInt("id"),
							entrada, 
							salida,
							resultSet.getString("valor"), 
							resultSet.getString("formaPago"));
					reservas.add(reserva);
				}
			}
		}
		
		return reservas;
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void editar(Reserva reserva) {
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE reservas SET fechaEntrada = ?, fechaSalida = ?, valor = ?,"
					+ " formaPago = ? WHERE id = ?");
			
			try(statement) {
				
				java.util.Date utilDate = reserva.getFechaEntrada();
				java.sql.Date entrada = new java.sql.Date(utilDate.getTime());
				
				java.util.Date utilDate2 = reserva.getFechaSalida();
				java.sql.Date salida = new java.sql.Date(utilDate2.getTime());
				
				statement.setDate(1, entrada);
				statement.setDate(2, salida);
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());
				
				statement.execute();
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
