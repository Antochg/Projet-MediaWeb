package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;

/**
 * Servlet traitant l'ajout d'un document
 */
@WebServlet("/AjouterDocumentServlet") 
public class AjouterDocumentServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
			
		// les variables sessions
		HttpSession session = request.getSession(true);
		session.setAttribute("ajouter", "0");
		
		// Récupère les variables de la page JSP
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		int type = Integer.parseInt(request.getParameter("types"));
		
		//Ajoute le document
		Mediatheque.getInstance().nouveauDocument(type, titre, auteur);
		session.setAttribute("ajouter", "1");
		
		//Renvoie sur la page JSP
		response.sendRedirect(request.getContextPath()+"/AjouterDocument.jsp");
	}
}
