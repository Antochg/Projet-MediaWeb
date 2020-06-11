<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="mediatek2020.items.Document"%>
<%@ page import ="mediatek2020.items.Utilisateur"%>
<%@ page import ="mediatek2020.Mediatheque"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Emprunter un document</title>
</head>
<body>

	<table>
		<tr>
			<td>Titre</td>
			<td>Auteur</td>
			<td>Type de document</td>
			<td></td>
		</tr>
	
		<%
		// Instancie une liste de document et un utilisateur
		List<Document> listeDocuments = new ArrayList<Document>();
		listeDocuments = (ArrayList<Document>) Mediatheque.getInstance().tousLesDocuments();
		Utilisateur user = (Utilisateur)session.getAttribute("sessionUtilisateur");
		
		// Affiche tous les documents disponible
		for(Document d : listeDocuments) {
			
			String titre = (String) d.data()[1];
			String auteur = (String) d.data()[2];
			String typeDoc = null;
			
			if((int)d.data()[3] == 1)
				typeDoc = "Livre";
			else if((int)d.data()[3] == 2)
				typeDoc = "DVD";
			else
				typeDoc = "CD";
			
			int idDoc = (int) d.data()[0];
		
			if(d.data()[4].equals(1)) {
		%>
				<tr>
					<td><% out.println(titre); %></td>
					<td><% out.println(auteur); %></td>
					<td><% out.println(typeDoc); %></td>
					<td>
						<form method="post" action="EmprunterDocumentServlet">
							<input type="hidden" name="idDoc" value="<% out.print(idDoc); %>">
							<input type="submit" value="Emprunter" />
						</form>
					</td>
				</tr>
		<% 
			}
		}
		%>
	</table>
	
	<% 
		// Affiche un message d'erreur si un autre utilisateur emprunte le document avant
		String erreurEmprunt = (String) session.getAttribute("erreurEmprunt");
		if(erreurEmprunt == "1"){
			out.println("Une erreur est survenue lors de l'emprunt du document !");
			session.setAttribute("erreurEmprunt", "0");
		}
		
	%> 
	
	<% 
		// Affiche un message de confirmation d'emprunt après un emprunt réussi
		String emprunter = (String) session.getAttribute("emprunter");
		if(emprunter == "1"){
			out.println("Vous avez emprunter un document !");
			session.setAttribute("emprunter", "0");
		}
	%> 

	<button onclick="window.location.href = 'AccueilAbonne.jsp';">Accueil</button>

</body>
</html>