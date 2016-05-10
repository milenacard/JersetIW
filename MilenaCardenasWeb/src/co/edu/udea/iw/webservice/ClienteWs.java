package co.edu.udea.iw.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.exception.IWServiceException;
import co.edu.udea.iw.service.ClienteService;
import co.edu.udea.iw.web.dto.ClienteDTOWs;
import javassist.tools.rmi.RemoteException;

/**
 * clase para entregar la lista 
 * de clientes
 * @author milena.cardenasa
 * @version 1.0
 */
@Component //
@Path("cliente") //url
public class ClienteWs {
	
	@Autowired //inyecta el ClienteService(Logica de negocio)
	ClienteService clienteService;
	
	@Produces(MediaType.APPLICATION_XML)
	@GET //consulto datos
	/* librería remoteException -->java.sssi*/
	public List<ClienteDTOWs> obtener() throws RemoteException{
		List<Cliente> lista = null;
		List<ClienteDTOWs> listaR = new ArrayList<ClienteDTOWs>();
		try {
			lista = clienteService.obtener();
			
			for (Cliente cliente : lista) {
				ClienteDTOWs clienteDtows = new ClienteDTOWs();
				clienteDtows.setCedula(cliente.getCedula());
				clienteDtows.setNombre(cliente.getNombres());
				clienteDtows.setApellido(cliente.getApellidos());
				clienteDtows.setEmail(cliente.getCorreoElectronico());
				
				listaR.add(clienteDtows);
			}
		} catch (IWDaoException e) {
			throw new RemoteException(e);
		}
		return listaR;
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET 
	public String  guardar(@QueryParam("cedula") String cedula,  @QueryParam("nombre") String nombreCompleto, @PathParam("apellido") String apellidos,
			@PathParam("correoelectronico") String correoElectronico,
			@PathParam("usuarioCrea") String usuarioCrea){
			
		try {
			clienteService.guardaCliente(cedula, nombreCompleto, apellidos, correoElectronico, usuarioCrea);
		} catch (IWDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IWServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return " ";
	
		
	}
	
}

