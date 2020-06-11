package persistantdata;

import mediatek2020.items.Utilisateur;

/**
 * Un utilisateur de la médiathèque
 *
 */
public class UtilisateurMediatheque implements Utilisateur {
	
	private int idUtilisateur;
	private String loginUtilisateur;
	private String mdpUtilisateur;
	private String typeUtilisateur;
	
	public UtilisateurMediatheque(int idUtilisateur, String loginUtilisateur, String mdpUtilisateur, String typeUtilisateur) {
		this.idUtilisateur = idUtilisateur;
		this.loginUtilisateur = loginUtilisateur;
		this.mdpUtilisateur = mdpUtilisateur;
		this.typeUtilisateur = typeUtilisateur;
	}

	/**
	 * Récupère les données d'un utilisateur
	 */
	@Override
	public Object[] data() {
		Object[] data = new Object[4];
		data[0] = idUtilisateur;
		data[1] = loginUtilisateur;
		data[2] = mdpUtilisateur;
		data[3] = typeUtilisateur;
		return data;
	}

	/**
	 * Retourne vrai si l'utilisateur est un bibliothécaire sinon retourne faux
	 */
	@Override
	public boolean isBibliothecaire() {
		if(this.typeUtilisateur.equals("Abonne")) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne le login de l'utilisateur
	 */
	@Override
	public String name() {
		return this.loginUtilisateur;
	}
}
