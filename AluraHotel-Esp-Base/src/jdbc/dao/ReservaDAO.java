package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Reserva;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva nuevaReserva) {

		try {
			PreparedStatement pstm;

			pstm = con.prepareStatement(

					"INSERT INTO RESERVAS (FechaEntrada, FechaSalida, Valor, FormadePago) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (pstm) {
				pstm.setDate(1, nuevaReserva.getFechaE());
				pstm.setDate(2, nuevaReserva.getFechaS());
				pstm.setString(3, nuevaReserva.getValor());
				pstm.setString(4, nuevaReserva.getFormaPago());

				pstm.execute();

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String seekLast() {
		String numeroReserva = null;

		try {
			PreparedStatement pstm = con.prepareStatement("SELECT id FROM reservas ORDER BY id DESC LIMIT 1");
			ResultSet resultSet = pstm.executeQuery();

			if (resultSet.next()) {
				numeroReserva = resultSet.getString("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numeroReserva;
	}

	public List<Reserva> buscar(String numeroReserva) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		
		try {
			PreparedStatement pstm;

			if (numeroReserva.isEmpty()) {
			
			pstm = con.prepareStatement(
					"SELECT * FROM RESERVAS");
			pstm.execute();
			
			transformarResultSetEnReserva(reservas, pstm);
			}else {
				pstm = con.prepareStatement("SELECT * FROM RESERVAS WHERE id = ?");
			    pstm.setString(1, numeroReserva);
			    pstm.execute();
			}
			
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}

	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1),
						rst.getDate(2), rst.getDate(3),
						rst.getString(4), rst.getString(5));
				reservas.add(reserva);
			}
		}
		
	}

	public int eliminar(Integer id) {
		
		desvincular(id);
		
	    try {
	        final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

	        try (statement) {
	            statement.setInt(1, id);

	            statement.execute(); 

	            int updateCount = statement.getUpdateCount();
	            return updateCount;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	private void desvincular(int id) {
		try {
	        final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET id_reserva = NULL WHERE id_reserva = ?");

	        try (statement) {
	            statement.setInt(1, id);

	            statement.execute(); 
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
		
	}

	public int modificar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE RESERVAS SET "
                    + " FechaEntrada = ?, "
                    + " FechaSalida = ?,"
                    + " Valor = ?,"
                    + " FormadePago=?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setDate(1, fechaE);
                statement.setDate(2, fechaS);
                statement.setString(3, valor);
                statement.setString(4, formaPago);
                statement.setInt(5, id);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



	
	

}
