/**
 * Encapsula la informaci�n de error que pueda producirse mientras
 * se ejecuta la aplicaci�n.
 * C�digos de error y descripci�n
 * C�digo   Descripci�n
 * 1 -      El login y clave de usuario buscados no se han encontrado o son err�neos.
 * 2 -      Error en acceso de archivo de datos de usuarios.
 * 3 -      El login ya est� registrado. Int�ntelo con otro.
 */
package login;

import java.io.*;

/**
 * @author Eduardo A. Ponce
 *
 */
@SuppressWarnings("serial")
public class BeanError extends Exception implements Serializable{
	private int codError;
	private String mensError;
	private Exception excepcion = null;
	  
	  public BeanError(int codError, String mensError)
	  {
	    super(mensError);
	    this.setCodError(codError);
	    this.setMensError(mensError);
	  }
	  
	  public BeanError(int codError, String mensError, Exception excepcion)
	  {
	    super(mensError);
	    this.setCodError(codError);
	    this.setMensError(mensError);
	    this.setExcepcion(excepcion);
	  }

	/**
	 * @param codError the codError to set
	 */
	private void setCodError(int codError) {
		this.codError = codError;
	}

	/**
	 * @return the codError
	 */
	@SuppressWarnings("unused")
	public int getCodError() {
		return codError;
	}

	/**
	 * @param mensError the mensError to set
	 */
	private void setMensError(String mensError) {
		this.mensError = mensError;
	}

	/**
	 * @return the mensError
	 */
	@SuppressWarnings("unused")
	public String getMensError() {
		return mensError;
	}

	/**
	 * @param excepcion the excepcion to set
	 */
	private void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}

	/**
	 * @return the excepcion
	 */
	@SuppressWarnings("unused")
	public Exception getExcepcion() {
		return excepcion;
	}

}
