<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="mediatek2020.Mediatheque"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter un document</title>
</head>
<body>

	<form action="AjouterDocumentServlet" method="POST">
  		<table style="with: 50%">
  		
			<tr>
				<td>Titre</td>
				<td><input required type="text" name="titre" /></td>
			</tr>
			<tr>
				<td>Auteur</td>
				<td><input required type="text" name="auteur" /></td>
			</tr>
			<tr>
				<label for="typeSelect">Selectionner le type du document : </label>
	
				<select required name="types" id="types">
				    <option value="">--Selectionner une option--</option>
				    <option value="1">Livre</option> 
					<option value="2">DVD</option> 
					<option value="3">CD</option> 
				</select>
			</tr>
			
		</table>
		<input type="submit" value="Ajouter"/>
	</form>
	
	 <% 
		// Affiche un message de confirmation après l'ajout d'un document
		String ajouter = (String) session.getAttribute("ajouter");
		if(ajouter == "1"){
			out.println("Le document a ete ajoute !");
			session.setAttribute("ajouter", "0");
		}
	 %> 
	 <br/>
	 <br/>
	
	<button onclick="window.location.href = 'AccueilBibliothecaire.jsp';">Accueil</button>

</body>
</html>