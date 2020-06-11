package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Utilisateur;

/**
 * Servlet traitant de la connexion d'un utilisateur
 */
@WebServlet("/LoginServlet") 
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		
		// Récupère les variable de la page JSP
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		// Charge la classe MediathequeData
		try {
			Class.forName("persistantdata.MediathequeData");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
  
		// Instancie un utilisateur
		Utilisateur user = Mediatheque.getInstance().getUser(login, password);
		
		// Vérifie si l'utilisateur existe
		if (user != null) {
			
			// Ouverture d'une session
			HttpSession session = request.getSession(true);
		
			// Crée des variables session
			session.setAttribute("login", login);
			session.setAttribute("password", password);
			session.setAttribute("sessionUtilisateur", user);
			
			// Vérifie si l'utilisateur est un bibliothèque ou non
			if(user.isBibliothecaire()) {
				//Page d'accueil pour bibliothecaire
				response.sendRedirect(request.getContextPath()+"/AccueilBibliothecaire.jsp");  
			}
			else {
				//Page d'accueil pour abonne    		
				response.sendRedirect(request.getContextPath()+"/AccueilAbonne.jsp");
			}
		}
		// Si l'utilisateur n'existe pas, le renvoie sur la page de Login et affice un message d'erreur
		else {
			session.setAttribute("erreurLogin", "1");
			response.sendRedirect(request.getContextPath()+"/Login.jsp"); 
		}
		
	}
}