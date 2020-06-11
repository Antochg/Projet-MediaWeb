<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de connexion</title>
</head>
<body>
<form action="LoginServlet" method="POST">
	<table style="with: 50%">

		<tr>
			<td>Login</td>
			<td><input type="text" name="login" /></td>
		</tr>
		<tr>
			<td>Mot de passe</td>
			<td><input type="password" name="password" /></td>
		</tr>
	</table>
	<input type="submit" value="Login" />
</form>

	<% 
		// Affiche un message d'erreur si l'utilisateur n'existe pas ou s'il s'est trompe dans son mot de passe
		String erreurLogin = (String) session.getAttribute("erreurLogin");
		if(erreurLogin == "1") { 
	%>
        <h4>Utilisateur inconnu ou mot de passe incorrect !</h4>
	<% 		session.setAttribute("erreurLogin", "0");
		}
	%>

</body>
</html>