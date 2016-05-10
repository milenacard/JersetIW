package co.edu.udea.iw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaMudo
 * @autor Milena Cardenas
 * @version 1.0
 */
@WebServlet("/HolaMudo") //es el patron o url que va a responder a este servlet
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor para el servlet
     * @see HttpServlet#HttpServlet()
     */
    public HolaMundo() {
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
		// TODO Auto-generated method stub
		PrintWriter out;
		out = response.getWriter();
		/*se va a mostrar en el cliente(un navegador Web)*/
		out.append("<b>Hola Mundo</b>"); //Se manda el codigo HTML como quiere  que se vea
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
