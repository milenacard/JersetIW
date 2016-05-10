package co.edu.udea.iw.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Servicio web
 * @author milena.cardenasa
 * @version 1.0
 */
/* Anotado como jersey */
@Path("saludo") //rest/saludo -->hace referencia a la clase
public class HolaMundo {
	
	@Produces(MediaType.TEXT_HTML)
	@GET
	/*Queryparam tiene el nombre que se va a mandar en la url */
	/* url: /rest/saludo?nombre=Mile*/
	public String saludar(@QueryParam("nombre") String nombreCompleto ){
	 
	 return "Buenas Tardes " +nombreCompleto;
	}
	
	@Produces(MediaType.TEXT_HTML)
	@GET
	@Path("saludo2/{nombre}/{apellido}") 
	/* url: /rest/saludo/saludo2/Mile/Card */
	public String saludar(@PathParam("nombre") String nombreCompleto, @PathParam("apellido") String apellidos){
		
		return "Buenas Tardes " +nombreCompleto + " " + apellidos;
	}

}

//