package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mediatek2020.*;
import mediatek2020.items.*;

// classe mono-instance dont l'unique instance est injectée dans Mediatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		AccesBD bd = new AccesBD();
	}

	private MediathequeData() {
		
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		
		List<Document> listeDocuments = new ArrayList<Document>();
		String reqGetTousDocuments = "SELECT * FROM document";
		try {
			Connection conn = AccesBD.connexionBD();	// Connexion a la base de données
			PreparedStatement stmtGetTousDocuments = conn.prepareStatement(reqGetTousDocuments);
			ResultSet res = stmtGetTousDocuments.executeQuery();
			
			int idDocument = 0;
			String titreDocument = "";
			String auteur = "";
			int typeDocument = 0;
			int disponible = 0;
			int idUtilisateurEmprunteur = 0;
			// boucle qui ajoute tous les documents dans la liste de documents
			while (res.next()) {
				idDocument = res.getInt("IdDoc");
				titreDocument = res.getString("TitreDoc");
				auteur = res.getString("Auteur");
				typeDocument = res.getInt("IdTypeDoc");
				disponible = res.getInt("Disponible");
				idUtilisateurEmprunteur = res.getInt("IdUtilisateurEmprunteur");
				listeDocuments.add(new DocumentMediatheque(idDocument, titreDocument, auteur, typeDocument, disponible, idUtilisateurEmprunteur));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeDocuments;
		
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String reqGetUser = "SELECT * FROM utilisateur WHERE login = ? AND mdp = ? ";
		try {
			Connection conn = AccesBD.connexionBD();	// Connexion a la base de données
			PreparedStatement stmtGetUser = conn.prepareStatement(reqGetUser);
			
			// On affecte les paramètres de la requête
			stmtGetUser.setString(1, login);	
			stmtGetUser.setString(2, password);
			
			ResultSet res = stmtGetUser.executeQuery();
			
			// si l'utilisateur se trouve dans la base de données, on le créer
			if(res.next()) {
				int idUtilisateur = res.getInt("IdUtilisateur");
				String loginUtilisateur = res.getString("Login");
				String mdpUtilisateur = res.getString("Mdp");
				String typeUtilisateur = res.getString("TypeUtilisateur");
				
				// Si l'utilisateur existe on le créer et on le retourne
				return new UtilisateurMediatheque(idUtilisateur,loginUtilisateur, mdpUtilisateur, typeUtilisateur);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String reqGetDocument = "SELECT * FROM document WHERE IdDoc = ? ";
		try {
			Connection conn = AccesBD.connexionBD();	// Connexion a la base de données
			PreparedStatement stmtGetDocument = conn.prepareStatement(reqGetDocument);
			stmtGetDocument.setInt(1, numDocument);
			ResultSet res = stmtGetDocument.executeQuery();
			
			// Si on trouve le document de numéro numDocument alors on le créer et on le renvoie
			if(res.next()) {
				int idDocument = res.getInt("IdDoc");
				String titreDocument = res.getString("TitreDoc");
				String auteur = res.getString("Auteur");
				int typeDocument = res.getInt("IdTypeDoc");
				int disponible = res.getInt("Disponible");
				int idUtilisateurEmprunteur = res.getInt("IdUtilisateurEmprunteur");
				return new DocumentMediatheque(idDocument, titreDocument, auteur, typeDocument, disponible, idUtilisateurEmprunteur);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void nouveauDocument(int type, Object... args) { 
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		
		String reqNewDoc = "INSERT INTO document (IdDoc, TitreDoc, Auteur, IdTypeDoc, Disponible, IdUtilisateurEmprunteur)"
				+ " values(?,?,?,?,?,NULL)";
							
		try {
			Connection conn = AccesBD.connexionBD();	// Connexion a la base de données
			PreparedStatement stmtNewDoc = conn.prepareStatement(reqNewDoc);
			
			// On convertit les Object en String
			String titreDoc = args[0].toString();
			String auteur = args[1].toString();
			
			// On affecte les paramètres de la requête
			stmtNewDoc.setInt(1, 0);  // on utilise l'auto increment de phpmyadmin
			stmtNewDoc.setString(2, titreDoc);
			stmtNewDoc.setString(3, auteur);
			stmtNewDoc.setInt(4, type);
			stmtNewDoc.setInt(5, 1);
			
			stmtNewDoc.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
