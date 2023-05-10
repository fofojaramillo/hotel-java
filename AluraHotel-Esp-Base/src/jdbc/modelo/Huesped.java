package jdbc.modelo;

import java.sql.Date;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String Nacionalidad;
	private String telefono;
	private int numeroReserva;
	
	
	

	public Huesped(String Nombre, String Apellido, String Telefono, Date fechaN, String Nacionalidad, int numeroReserva) {
		this.nombre = Nombre;
		this.apellido = Apellido;
		this.telefono = Telefono;
		this.fechaNacimiento = fechaN;
		this.Nacionalidad = Nacionalidad;
		this.numeroReserva = numeroReserva;
	}
	
	
	public Huesped(int id, String Nombre, String Apellido, Date fechaN, String Nacionalidad, String Telefono, int numeroReserva) {
		this.id = id;
		this.nombre = Nombre;
		this.apellido = Apellido;
		this.telefono = Telefono;
		this.fechaNacimiento = fechaN;
		this.Nacionalidad = Nacionalidad;
		this.numeroReserva = numeroReserva;
	}


	public int getNumeroReserva() {
		return numeroReserva;
	}


	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getId() {
		return id;
	}
	
}
