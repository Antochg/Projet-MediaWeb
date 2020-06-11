package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet pour la réécriture d'url qui renvoie sur la page de Login
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
			
		response.sendRedirect("Login.jsp"); 
	}
}
