package jdbc.modelo;

import java.sql.Date;

import javax.swing.JTextField;

public class Reserva {
	
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String valor;
	private String formaPago;
	
	

	public Reserva(Date FechaE, Date FechaS, String valor, String formaPago) {
		this.fechaE=FechaE;
		this.fechaS=FechaS;
		this.valor=valor;
		this.formaPago=formaPago;
	}

	public Reserva(int id, Date fechaE, Date fechaS, String Valor, String metodo) {
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.valor = Valor;
		this.formaPago = metodo;
	}

	public Date getFechaE() {
		return fechaE;
	}
	public void setFechaE(Date fechaE) {
		this.fechaE = fechaE;
	}
	public Date getFechaS() {
		return fechaS;
	}
	public void setFechaS(Date fechaS) {
		this.fechaS = fechaS;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public Integer getId() {
		return id;
	}


}
