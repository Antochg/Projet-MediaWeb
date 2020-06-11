<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>

	<button onclick="window.location.href = 'DeconnexionServlet';">Deconnexion</button>
    
    <h3>Bonjour 
		<% 
		// Récupère le login et l'affiche
		String login = (String) session.getAttribute("login");
		out.println(login);
		%> 
	</h3>
    
    <button onclick="window.location.href = 'EmprunterDocument.jsp';">Emprunter un document</button>
    <button onclick="window.location.href = 'RendreDocument.jsp';">Rendre un document</button>
	<button onclick="window.location.href = 'ReserverDocument.jsp';">Reserver un document</button>

</body>
</html>