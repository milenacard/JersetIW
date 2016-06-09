package co.edu.udea.iw.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import co.edu.udea.iw.service.UsuarioService;
import co.edu.udea.iw.web.dto.ClienteDTOWs;
import javassist.tools.rmi.RemoteException;

/**
 * clase para crear el servicion para la clase clientes
 * 
 * @author milena.cardenasa
 * @version 1.0
 */
@Component
@Path("cliente")// url
public class ClienteWs {

	@Autowired
	// inyecta el ClienteService(Logica de negocio)
	ClienteService clienteService;
	
	@Autowired
	// inyecta el UsuarioService(Logica de negocio)
	UsuarioService usuarioService;

	/**
	 * Metodo para entregar la lista de clientes
	 * 
	 * @return Lista de clientes
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	// consulto datos
	/* librería remoteException -->java.sssi */
	public List<ClienteDTOWs> obtener() throws RemoteException {
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

	/**
	 * Metodo para guardar un cliente con todos sus atributos
	 * @param cedula
	 * @param nombreCompleto
	 * @param apellidos
	 * @param correoElectronico
	 * @param usuarioCrea
	 * @return
	 */
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	@Path("guardar")
	// url /rest/cliente/guardar
	public String guardar(@QueryParam("cedula") String cedula,
			@QueryParam("nombre") String nombreCompleto,
			@PathParam("apellido") String apellidos,
			@PathParam("correoelectronico") String correoElectronico,
			@PathParam("usuarioCrea") String usuarioCrea) {

		try {
			clienteService.guardaCliente(cedula, nombreCompleto, apellidos,
					correoElectronico, usuarioCrea);
		} catch (IWDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IWServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return " ";
	}

	/**
	 * Metodo para eliminar un cliente. Borrado logico.
	 * 
	 * @param cedula identificador del cliente a eliminar
	 * @param usuarioElimina usiario que elimina el cliente
	 * @return
	 */
	@Produces(MediaType.TEXT_PLAIN)
	@DELETE
	@Path("delete")
	// url /rest/cliente/delete
	public String delete(@QueryParam("cedula") String cedula,
			@QueryParam("usuarioElimina") String usuarioElimina) {

		try {
			clienteService.eliminarCliente(cedula, usuarioElimina);

		} catch (IWDaoException e) {
			e.printStackTrace();
		} catch (IWServiceException e) {
			e.printStackTrace();
		}
		return " ";

	}
	
	 /**
	  * Metodo para modificar un cliente.
	  * 
	  * @param cedula
	  * @param nombres
	  * @param apellidos
	  * @param correoElectronico
	  * @param usuarioModifica
	  * @return 
	  */
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	@Path("modificar")
	public String modificar(@QueryParam("cedula") String cedula, @QueryParam("nombres") String nombres, 
			@QueryParam("apellidos") String apellidos, @QueryParam("correoElectronico") String correoElectronico,
			@QueryParam("usuarioModifica") String usuarioModifica){
		
		try {
			clienteService.actualizarCliente(cedula, nombres, apellidos, correoElectronico, usuarioModifica);
		} catch (IWDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IWServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return " ";
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Path("autenticar")
	public String autenticar(@QueryParam("login") String login, @QueryParam("clave") String clave){
	
		try {
			usuarioService.validar(login, clave);
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
