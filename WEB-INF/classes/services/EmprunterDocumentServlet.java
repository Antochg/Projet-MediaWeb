package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.Utilisateur;

/**
 * Servlet traitant l'emprunt d'un document par un utilisateur abonné
 */
@WebServlet("/EmprunterDocumentServlet") 
public class EmprunterDocumentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		
		// Récupère les variables de la page JSP
		String idDoc = request.getParameter("idDoc");
		int idDocument = Integer.parseInt(idDoc);
		
		// Récupère les variables session
		HttpSession session = request.getSession(true);
		String login = (String) session.getAttribute("login");
		String password = (String) session.getAttribute("password");

		// Instancie un utilisateur et un document
		Utilisateur user = Mediatheque.getInstance().getUser(login, password);
		Document doc = (Document) Mediatheque.getInstance().getDocument(idDocument);
		
		try {
			// Vérifie si le document est disponible et emprunte le document et affiche un message de confirmation
			if(doc.data()[4].equals(1)) {
				doc.emprunter(user);
				session.setAttribute("emprunter", "1");
				response.sendRedirect(request.getContextPath()+"/EmprunterDocument.jsp");
			}
			// Sinon renvoie sur la page JSP et affiche d'un message d'erreur
			else {
				session.setAttribute("erreurEmprunt", "1");
				response.sendRedirect(request.getContextPath()+"/EmprunterDocument.jsp");
			}
		} catch (EmpruntException e) {
			e.printStackTrace();
		}
	}
	
}
