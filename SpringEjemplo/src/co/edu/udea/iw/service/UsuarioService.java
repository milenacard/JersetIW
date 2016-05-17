package co.edu.udea.iw.service;

import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.iw.dao.UsuarioDAO;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.exception.IWServiceException;
import co.edu.udea.iw.util.encode.Cifrar;
import co.edu.udea.iw.util.validations.Validaciones;

/**
 * Clase encargada de la logica de negocio
 * para la clase Usuario
 * Clase transaccional con la BD
 * @author Usuario
 *
 */
@Transactional
public class UsuarioService {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	
	public Boolean validar(String login, String clave) throws IWDaoException, IWServiceException{
		
		Cifrar cifrar = new Cifrar();
		
		if(Validaciones.isTextoVacio(login)){
			throw new IWServiceException("El login del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		if(Validaciones.isTextoVacio(clave)){
			throw new IWServiceException("La clave del usuario no puede ser nula, ni una cadena de caracteres vacia");
		}
		
		Usuario usuario = usuarioDAO.obtener(login);
		if(usuario == null){
			throw new IWServiceException("Usuario o contrase�a no v�lidos");
		}
		
		
		if(!cifrar.encrypt(clave).equals(usuario.getPassword())){
			throw new IWServiceException("Usuario o contrase�a no v�lidos");
		}
		
		return Boolean.TRUE;		
	}
	

}
