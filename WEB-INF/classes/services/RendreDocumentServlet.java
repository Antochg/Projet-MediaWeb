package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

/**
 * Servlet traitant du rendu d'un document
 */
@WebServlet("/RendreDocumentServlet") 
public class RendreDocumentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		
		// Récupère les variables de la page JSP
		String idDoc = request.getParameter("idDoc");
		int idDocument = Integer.parseInt(idDoc);
		
		// Récupère les variables de session
		HttpSession session = request.getSession(true);
		String login = (String) session.getAttribute("login");
		String password = (String) session.getAttribute("password");

		// Instancie un utilisateur et un document
		Utilisateur user = Mediatheque.getInstance().getUser(login, password);
		Document doc = (Document) Mediatheque.getInstance().getDocument(idDocument);
		
		// Rend un document, affiche un message de confirmation de rendu et renvoie sur la page pour rendre un document
		try {
			if(doc.data()[4].equals(0)) {
				doc.rendre(user);
				session.setAttribute("rendre", "1");
				response.sendRedirect(request.getContextPath()+"/RendreDocument.jsp");
			}
			else {
				session.setAttribute("erreurRendre", "1");
				response.sendRedirect(request.getContextPath()+"/RendreDocument.jsp");
			}
		} catch (RetourException e) {
			e.printStackTrace();
		}
	}
}
