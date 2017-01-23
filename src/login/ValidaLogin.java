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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class ValidaLogin extends HttpServlet {
	
	  private DataSource dsBdlogins;
	  private ServletContext sc; 
	  private BeanError error ;
	  
		public void init(ServletConfig config) throws ServletException {
			
			
			
	    	super.init(config);
	    	
	    	System.out.println("En método init");

	    	
	    	try {
		    	InitialContext initCtx = new InitialContext();
		        dsBdlogins = (DataSource) initCtx.lookup("java:jboss/datasources/dsbdlogins");
		        //DataSource ds = (DataSource) initCtx.lookup("java:app/env/MySqlDS");
		        System.out.println("Probando el acceso al DataSource al arrancar el servlet...");
//		        Connection conDB = dsBdlogins.getConnection();
//		        Statement s = conDB.createStatement();
		        System.out.println("Todo está ¡¡OK!!");
	    	}
	    	catch(NamingException ne) {
	    		System.out.println("Error en servicio JNDI al intentar crear el contexto inicial del servlet o al buscar el datasource.");
	    	}
//	    	catch(SQLException sqle) {
//	    		System.out.println("Error al acceder a la base de datos.");
//	    	}
			
			
			
		}  
	  
		
		

	 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 		doPost(request,response);
	 		
	 	}
		
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			
			
			System.out.println("entrando en post!");
			//Se obtiene el objeto de ámbito sesión
			HttpSession sesion = request.getSession();
		    // Obtener un objeto de ayuda para la solicitud
			boolean resultado = true;
			Connection conexion = null;
			Statement st = null;
			ResultSet rs = null;
			String login ;
			String clave ;
			String nombre ;
			String vista ;
			BeanUsuario beanUsuario;
			
			//Si la accion es login, se valida el login.
			//Se obtienen login y clave
			login = request.getParameter("login");
			clave = request.getParameter("clave");
			System.out.println("Todo está ¡¡O0K!!");
			beanUsuario = (BeanUsuario) sesion.getAttribute("usuarioBean");
			beanUsuario.setLogin(login);
			beanUsuario.setClave(clave);
			System.out.println("Todo está ¡¡O00K!!");
			
			try {
				conexion = dsBdlogins.getConnection();
				st = conexion.createStatement();
				rs = st.executeQuery("select login,clave,nombre from login where login = '"+login+"'");
				if (rs.next()) {
					if (!rs.getString("clave").equals(clave)) {

						resultado = false;
						beanUsuario.setcausaError("La clave no coincide.");
					}
					else
					{
						beanUsuario.setNombre(rs.getString("nombre"));
						System.out.println("Todo está ¡¡O00K!! " +  beanUsuario.getNombre() );
					}
				}
				else
				{

					resultado = false;
					beanUsuario.setcausaError("El login no existe.");
				}
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos",e);


		      
		      // Añadir en la petición el modelo a visualizar
		      request.setAttribute("beanUsuario",beanUsuario);
		      
		      if(!resultado){
		    	  vista = "/index.jsp";
		      }else{
		    	  vista= "/WEB-INF/resumen.jsp" ;
		      }
		      
		      
		      // Enviar la respuesta a la solicitud
		      RequestDispatcher rd = request.getRequestDispatcher(vista);
		      rd.forward(request,response);
		    }
	    
		}


		
		
		
		public void destroy() {
		      // Elimina el datasource del ámbito de aplicación, liberando todos los
		      // recursos que tuviera asignados.
		      sc.removeAttribute("dsBdlogins");
		      // Elimina el ámbito de aplicación.
		      sc = null;
			}

		
		
		
		

		
}
