package com.hotelalura.controller;

import java.util.List;

import com.hotelalura.dao.ReservaDao;
import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelo.Reserva;

public class ReservaController {
	
	private ReservaDao reservaDao;
	
	public ReservaController() {
		this.reservaDao = new ReservaDao(new ConnectionFactory().recuperarConexion());
	};
	
	public void guardarReserva(Reserva reserva) {
		reservaDao.guardarReserva(reserva);
	}
	
	public List<Reserva> cargarReservas(){
		return reservaDao.cargarReservas();
	}

	public List<Reserva> buscarReserva(String id) {
		 return reservaDao.buscarReserva(id);
	}

	public int eliminar(Integer id) {
		return reservaDao.eliminar(id);
	}

}
