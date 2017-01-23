package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.modelo.beans.BeanError;



public class ValidaLogin extends HttpServlet {
	
	  private DataSource dsBdlogins;
	  private ServletContext sc; 
	  
		public void init(ServletConfig config) throws ServletException {
			// Imprescindible llamar a super.init(config) para tener acceso a la configuración
			// del servlet a nivel de contenedor web.
			    super.init(config);
			// En este punto se procedería a obtener las referencias a las fuentes de datos de la
			// aplicación. 
			    try {
			    	InitialContext initCtx = new InitialContext();
			    	setDsBdlogins((DataSource) initCtx.lookup("java:jboss/datasources/dsbdlogins"));
			    	if (getDsBdlogins()==null)
			    		System.out.println("dsBdlogins es null.");
			    	sc = config.getServletContext();
			    	// El datasource se almacena a nivel de aplicación.
			    	sc.setAttribute("dsBdlogins", getDsBdlogins());
			    } 
			    catch(NamingException ne)
			    {
			        // Si no se pudiera recuperar el recurso JNDI asociado al datasource,
			        // se envía un mensaje de error hacia la salida de log del servidor
			        // de aplicaciones.
			        System.out.println("Error: Método init(). tipo NamingException.");
			    } 
			}  
	  
		
		

	 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 		doPost(request,response);
	 		
	 	}
		
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Se obtiene el objeto de ámbito sesión
			HttpSession sesion = request.getSession();
		    // Obtener un objeto de ayuda para la solicitud
			boolean resultado = true;
			Connection conexion = null;
			Statement st = null;
			ResultSet rs = null;
			//Se debe implementar ajustándose al uso de datasource	
			String login, clave;
			
			//Si la accion es login, se valida el login.
			//Se obtienen login y clave
			login = request.getParameter("login");
			clave = request.getParameter("clave");
			try {
				conexion = DS.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("select login,clave from usuarios where login = '"+login+"'");
				if (rs.next()) {
					if (!rs.getString("clave").equals(clave)) {
						error = new BeanError(2,"La clave no coincide.");
						resultado = false;
					}
				}
				else
				{
					error = new BeanError(3,"El login no existe.");
					resultado = false;
				}
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos",e);
				resultado = false;

		      
		      // Añadir en la petición el modelo a visualizar
		      request.setAttribute("modelo",accion.getModelo());
		      
		      // Enviar la respuesta a la solicitud
		      RequestDispatcher rd = request.getRequestDispatcher(vista);
		      rd.forward(request,response);
		    }
		    else
		    {
		    // Si la ejecución ha generado un error, procesarlo mediante el gestor centralizado de errores
		      gesError(accion.getVista(),accion.getError(),request,response);
		    }
		    
		}


		
		
		
		public void destroy() {
		      // Elimina el datasource del ámbito de aplicación, liberando todos los
		      // recursos que tuviera asignados.
		      sc.removeAttribute("dsBdlogins");
		      // Elimina el ámbito de aplicación.
		      sc = null;
			}

		
		
		
		
		
		
		  private void gesError(String vistaError, BeanError excepcion, HttpServletRequest request, HttpServletResponse response)
				    throws ServletException, IOException
				  {
				    RequestDispatcher rd = request.getRequestDispatcher(vistaError);
				    request.setAttribute("error",excepcion);
				    rd.forward(request,response);
				  }

		

		public DataSource getDsBdlogins() {
			return dsBdlogins;
		}

		public void setDsBdlogins(DataSource dsBdlogins) {
			this.dsBdlogins = dsBdlogins;
		}

		
		
}
