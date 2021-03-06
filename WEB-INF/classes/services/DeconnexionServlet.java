package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet traitant la déconnexion d'un utilisateur
 */
@WebServlet("/DeconnexionServlet")
public class DeconnexionServlet  extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        // Récupération et destruction de la session en cours 
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirection vers la page de connexion 
        response.sendRedirect("Login.jsp");
    }
}
