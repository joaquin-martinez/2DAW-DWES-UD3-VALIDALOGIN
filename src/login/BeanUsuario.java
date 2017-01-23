package login;

import java.io.*;


public class BeanUsuario implements Serializable {

	private String login;
	private String clave;
	private String nombre;
	private String causaError;

	
	public BeanUsuario() {

		this.login = "" ;
		this.clave = "" ;
		this.nombre = "" ;
		this.causaError = "" ;
		
	}
	

	
	public BeanUsuario(String login, String clave, String nombre, String causaError) {

		this.login = login;
		this.clave = clave;
		this.nombre = nombre;
		this.causaError = causaError;
	}

	
	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getLogin() {
		return login;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public String getClave() {
		return clave;
	}

	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setcausaError(String causaError) {
		this.causaError = causaError;
	}

	
	public String getcausaError() {
		return causaError;
	}

	
}
