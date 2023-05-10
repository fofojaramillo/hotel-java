package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huesped;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped nuevoHuesped) {

		try {
			PreparedStatement pstm;

			pstm = con.prepareStatement(

					"INSERT INTO HUESPEDES (nombre, apellido, Fecha_de_Nacimiento, Nacionalidad, Telefono, id_reserva) VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (pstm) {
				pstm.setString(1, nuevoHuesped.getNombre());
				pstm.setString(2, nuevoHuesped.getApellido());
				pstm.setDate(3, nuevoHuesped.getFechaNacimiento());
				pstm.setString(4, nuevoHuesped.getNacionalidad());
				pstm.setString(5, nuevoHuesped.getTelefono());
				pstm.setInt(6, nuevoHuesped.getNumeroReserva());

				pstm.execute();

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Huesped> buscar(String apellido) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		
		try {
			PreparedStatement pstm;

			if (apellido.isEmpty()) {
			
			pstm = con.prepareStatement(
					"SELECT * FROM HUESPEDES");
			pstm.execute();
			
			transformarResultSetEnHuesped(huespedes, pstm);
			}else {
				pstm = con.prepareStatement("SELECT * FROM HUESPEDES WHERE apellido = ?");
			    pstm.setString(1, apellido);
			    pstm.execute();
			}
			
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}

	private void transformarResultSetEnHuesped(List<Huesped> huespedes, PreparedStatement pstm) throws SQLException {
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				Huesped huesped = new Huesped(rst.getInt(1), rst.getString(2),rst.getString(3),
						rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				huespedes.add(huesped);
			}
		}
		
	}

	public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

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

	public int modificar(Integer id, String nombre, String apellido, Date fechaN, String nacionalidad,
			String telefono, Integer id_reserva) {
		try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE HUESPEDES SET "
                    + " nombre = ?, "
                    + " apellido = ?,"
                    + " Fecha_de_nacimiento = ?,"
                    + " Nacionalidad=?,"
                    + " Telefono=?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, fechaN);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id);


                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

}
