package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huesped;

public class HuespedController {

	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}
	
	public void guardar(Huesped nuevoHuesped) {
		
		huespedDAO.guardar(nuevoHuesped);
		
	}


	public List<Huesped> buscar(String apellido) {
		return this.huespedDAO.buscar(apellido);
	}
	
	public int eliminar(int id) {
		return huespedDAO.eliminar(id);
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaN, String nacionalidad,
			String telefono, Integer id_reserva) {
		return huespedDAO.modificar(id, nombre, apellido, fechaN, nacionalidad, telefono, id_reserva);
	}

}
