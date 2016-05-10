package co.edu.udea.iw.web.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO para servicio web
 * para no traer todos los datos de la BD
 * @author milena.cardenasa
 *
 */
@XmlRootElement
public class ClienteDTOWs {
	
	String cedula;
	String nombre;
	String apellido;
	String email;
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

}
