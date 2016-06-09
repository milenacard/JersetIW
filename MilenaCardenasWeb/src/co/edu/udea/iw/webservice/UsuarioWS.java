package co.edu.udea.iw.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.exception.IWServiceException;
import co.edu.udea.iw.service.UsuarioService;

@Component
@Path("usuario")
public class UsuarioWS {
    
    @Autowired
    UsuarioService usuarioService;
    
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String autenticar(@QueryParam("login")String login,@QueryParam("clave")String clave){    
        try {
            if(usuarioService.validar(login, clave)){
                return "Autenticación correcta";
            }else{
                return "Login o password incorrecto";
            }
        } catch (IWDaoException e) {
            e.printStackTrace();
            return "Hubo un erro tratanto de autenticar";
        } catch (IWServiceException e) {
            e.printStackTrace();
            return "Usuario o contraseña no válidos";
        }
    }
    

}