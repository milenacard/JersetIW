package co.edu.udea.iw.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.exception.IWDaoException;
import co.edu.udea.iw.service.ClienteService;

/**
 * Servlet implementation class UsuarioServlet
 * @autor Milena Cardenas
 * @version 1.0
 */
@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteService clienteService = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo para enviar las peticiones y respuesta
	 * request, los objetos que me hicieron en la peticion
	 * response, se configura la respuesta que voy a dar al cliente,
	 * en formato HTML (formateado)
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Obtener la lista de clientes*/
		List<Cliente> lista = null;
		PrintWriter out = response.getWriter();
		out.println("<table border='1'>"
				+ "<tr>"
				+ "<th>Cedula</th>"
				+ "<th>Nombre Completo</th>"
				+ "<th>Correo</th>"
				+ "</tr>");
		
		try {
			lista = clienteService.obtener();
			for (Cliente cliente : lista) {
				out.println("<tr>");
				out.println("<td>" + cliente.getCedula() + "</td>");
				out.println("<td>" + cliente.getNombres() + "</td>");
				out.println("<td>" + cliente.getCorreoElectronico() + "</td>");
				out.println("</tr>");
			}
			out.print("</table>");
		} catch (IWDaoException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Metodo para inicializar el Servlet
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);			
		
		/* Trae la fraccion de memoria que separa spring para los bean */
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		
		/* Dice cual bean es el que necesita que traiga */
		clienteService = (ClienteService) ac.getBean("clienteService");		
	}	

}
