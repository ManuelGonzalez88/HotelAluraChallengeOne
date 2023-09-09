package com.hotelalura.controller;

import java.util.List;

import com.hotelalura.dao.HuespedDao;
import com.hotelalura.factory.ConnectionFactory;
import com.hotelalura.modelo.Huesped;

public class HuespedController {
	
	private HuespedDao huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDao(new ConnectionFactory().recuperarConexion());
	}
	
	public void guardarHuesped(Huesped huesped) {
		huespedDao.guardarHuesped(huesped);
	}

	public List<Huesped> cargarHuespedes() {
		return huespedDao.cargarHuespedes();
	}

	public List<Huesped> buscarHuesped(String apellido) {
		return huespedDao.buscarHuesped(apellido);
	}

	public void editar(Huesped huesped) {
		huespedDao.editar(huesped);
	}
}
