package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.ReservationException;
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;

/**
 * Un document de la m�diath�que
 *
 */
public class DocumentMediatheque implements Document {
	
	private int idDocument;
	private String titreDocument;
	private String auteur;
	private int typeDocument;
	private int disponible;
	private int idUtilisateurEmprunteur;
	
	public DocumentMediatheque(int idDocument, String titreDocument, String auteur, int typeDocument, int disponible, int idUtilisateurEmprunteur) {
		this.idDocument = idDocument;
		this.titreDocument = titreDocument;
		this.auteur = auteur;
		this.typeDocument = typeDocument;
		this.disponible = disponible;
		this.idUtilisateurEmprunteur = idUtilisateurEmprunteur;
	}

	/**
	 * R�cup�re les donn�es d'un document
	 */
	@Override
	public Object[] data() {
		Object[] data = new Object[6];
		data[0] = idDocument;
		data[1] = titreDocument;
		data[2] = auteur;
		data[3] = typeDocument;
		data[4] = disponible;
		data[5] = idUtilisateurEmprunteur;
		return data;
	}
	
	/**
	 * M�thode qui permet � un utilisateur d'emprunter un document en changeant la base de donn�es
	 * @param arg0 l'utilisateur
	 */
	@Override
	public void emprunter(Utilisateur arg0) throws EmpruntException {
		synchronized {
			String reqChangerDisponible = "UPDATE document SET Disponible = 0, IdUtilisateurEmprunteur = ? WHERE IdDoc = ?";
			
			try {
				Connection conn = AccesBD.connexionBD();	// Connexion � la base de donn�es
				PreparedStatement stmtChangerDisponible = conn.prepareStatement(reqChangerDisponible);
			
				int idUtilisateurEmprunteur = (int) arg0.data()[0];
				
				// On affecte les param�tres de la requ�te
				stmtChangerDisponible.setInt(1, idUtilisateurEmprunteur);
				stmtChangerDisponible.setInt(2, this.getIdDocument());
				
				stmtChangerDisponible.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * M�thode qui permet � un utilisateur de rendre un document en changeant la base de donn�es
	 * @param arg0 l'utilisateur
	 */
	@Override
	public void rendre(Utilisateur arg0) throws RetourException {
		synchronized {
			String reqChangerDisponible = "UPDATE document SET Disponible = 1, IdUtilisateurEmprunteur = NULL WHERE IdDoc = ?";
			try {
				Connection conn = AccesBD.connexionBD();	// Connexion a la base de donn�es
				PreparedStatement stmtChangerDisponible = conn.prepareStatement(reqChangerDisponible);
			
				// On affecte les param�tres de la requ�te
				stmtChangerDisponible.setInt(1, this.getIdDocument());
				
				stmtChangerDisponible.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void reserver(Utilisateur arg0) throws ReservationException {
		
	}

	/**
	 * getter d'IdDocument
	 * @return idDocument
	 */
	public int getIdDocument() {
		return idDocument;
	}

}
