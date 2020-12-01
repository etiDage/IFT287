package JardinCollectif;

import java.sql.*;

public class TupleMembre {
	private String userId;
	private String prenom;
	private String nom;
	private String motDePasse;
	private boolean admin;
	
	public TupleMembre()
    {
    }
	
	public TupleMembre(String userId, String prenom, String nom, String motDePasse, boolean admin)
    {
        this.setNoMembre(userId);
        this.setPrenom(prenom);
        this.setNom(nom);
        this.setMotDePasse(motDePasse);
        this.setAdmin(admin);
    }

	private void setAdmin(boolean admin) {
		this.admin = admin;
		
	}
    public boolean getAdmin()
    {
        return admin;
    }

	private void setMotDePasse(String motDePasse) {
		this.motDePasse= motDePasse;
		
	}
    public String getMotDePasse()
    {
        return motDePasse;
    }

	private void setNom(String nom) {
		this.nom = nom;
		
	}
    public String getNom()
    {
        return nom;
    }

	private void setPrenom(String prenom) {
		this.prenom= prenom;
		
	}
    public String getPrenom()
    {
        return prenom;
    }

	private void setNoMembre(String userId) {
		this.userId= userId;
		
	}
    public String getNoMembre()
    {
        return userId;
    }

}
