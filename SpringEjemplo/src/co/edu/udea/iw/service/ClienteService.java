package co.edu.udea.iw.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.ClienteDAO;
import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.exception.IWServiceException;
import co.edu.udea.iw.util.validations.Validaciones;

/**
 * Clase encargada de la logica de negocio
 * para la clase Cliente.
 * Clase transaccional con la BD
 * @author Usuario
 *
 */
@Transactional //se ve afectado cada uno de los metodos de la clase
public class ClienteService {
	
	private ClienteDAO clienteDAO;
	private UsuarioDAO usuarioDAO;
	
	public void guardaCliente(String cedula, String nombres, String apellidos, 
			String correoElectronico, String usuarioCrea) throws IWDaoException, IWServiceException{
		
		Cliente cliente = null;
		
		if(Validaciones.isTextoVacio(cedula)){
			throw new IWServiceException("La cedula del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(nombres)){
			throw new IWServiceException("Los nombres del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(apellidos)){
			throw new IWServiceException("Los apellidos del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(correoElectronico)){
			throw new IWServiceException("El correo electr�nico del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(usuarioCrea)){
			throw new IWServiceException("El usuario que crea el cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(!Validaciones.isEmail(correoElectronico)){
			throw new IWServiceException("El correo electr�nico del cliente debe ser v�lido");
		}
		
		if(clienteDAO.obtener(cedula) != null){
			throw new IWServiceException("Ya existe un cliente con cedula " + cedula +  " en el sistema");
		}
		
		Usuario usuario = usuarioDAO.obtener(usuarioCrea);
		if(usuario == null){
			throw new IWServiceException("El usuario que crea el cliente debe ser un usuario del sistema");
		}
		
		cliente = new Cliente();
		
		cliente.setCedula(cedula);
		cliente.setNombres(nombres);
		cliente.setApellidos(apellidos);
		cliente.setCorreoElectronico(correoElectronico);
		cliente.setUsuarioCrea(usuario);
		cliente.setFechaCreacion(new Date());
		cliente.setEliminado(Boolean.FALSE);
		
		clienteDAO.insertar(cliente);
		
	}
	
	public void actualizarCliente(String cedula, String nombres, String apellidos, 
			String correoElectronico, String usuarioModifica) throws IWDaoException, IWServiceException{
		
		Cliente cliente = null;
		
		if(Validaciones.isTextoVacio(cedula)){
			throw new IWServiceException("La cedula del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(nombres)){
			throw new IWServiceException("Los nombres del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(apellidos)){
			throw new IWServiceException("Los apellidos del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(correoElectronico)){
			throw new IWServiceException("El correo electronico del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(usuarioModifica)){
			throw new IWServiceException("El usuario que modifica el cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(!Validaciones.isEmail(correoElectronico)){
			throw new IWServiceException("El correo electronico del cliente debe ser valido");
		}
		
		Usuario usuario = usuarioDAO.obtener(usuarioModifica);
		if(usuario == null){
			throw new IWServiceException("El usuario que modifica el cliente debe ser un usuario del sistema");
		}
		
		cliente = clienteDAO.obtener(cedula);
		
		if(cliente == null){
			throw new IWServiceException("El cliente a modificar no existe en el sistema");
		}
		
		
		cliente.setNombres(nombres);
		cliente.setApellidos(apellidos);
		cliente.setCorreoElectronico(correoElectronico);
		cliente.setUsuarioModifica(usuario);
		cliente.setFechaModificacion(new Date());
		
		clienteDAO.modificar(cliente);
		
	}
	
	public void eliminarCliente(String cedula, String usuarioElimina) throws IWDaoException, IWServiceException{
		
		Cliente cliente = null;
		
		if(Validaciones.isTextoVacio(cedula)){
			throw new IWServiceException("La cedula del cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		if(Validaciones.isTextoVacio(usuarioElimina)){
			throw new IWServiceException("El usuario que elimina el cliente no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		Usuario usuario = usuarioDAO.obtener(usuarioElimina);
		if(usuario == null){
			throw new IWServiceException("El usuario que elimina el cliente debe ser un usuario del sistema");
		}
		
		
		cliente = clienteDAO.obtener(cedula);
		
		if(cliente == null){
			throw new IWServiceException("El cliente a eliminar no existe en el sistema");
		}
		
		
		cliente.setEliminado(Boolean.TRUE);
		cliente.setUsuarioElimina(usuario);
		cliente.setFechaEliminacion(new Date());
		
		clienteDAO.modificar(cliente);
		
	}
	
	
	public List<Cliente> obtener() throws IWDaoException{
		return clienteDAO.obtener();
	}
	
	public Cliente obtener(String cedula) throws IWDaoException, IWServiceException{
		if(cedula == null && "".equals(cedula)){
			throw new IWServiceException("La cedula del cliente a buscar no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		return clienteDAO.obtener(cedula);
	}


	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}


	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}


	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}


	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
