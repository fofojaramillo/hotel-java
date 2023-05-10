package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservaDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}

	public void guardar(Reserva nuevaReserva) {

		reservaDAO.guardar(nuevaReserva);

	}

	public String seeklast() {

		return reservaDAO.seekLast();

	}

	public List<Reserva> buscar(String numeroReserva) {
		return this.reservaDAO.buscar(numeroReserva);
	}

	public int eliminar(int id) {
		return reservaDAO.eliminar(id);
	}

	public int modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		return reservaDAO.modificar(id, fechaE, fechaS, valor, formaPago);
	}

}
