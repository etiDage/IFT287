package JardinCollectif;

import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Permet de représenter un membre.
 * 
 * <pre>
 * Vincent Ducharme
 * Université de Sherbrooke
 * Version 1.0 - 18 juin 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 *
 * </pre>
 */

@Entity
public class TupleMembre {
	
	@Id
	@GeneratedValue
	private long m_id;
	
	private int m_noMembre;
	private String m_prenom;
	private String m_nom;
	private String m_motDePasse;
	private boolean m_admin;
	
	public TupleMembre()
    {
    }
	
	public TupleMembre(int noMembre, String prenom, String nom, String motDePasse, boolean admin)
    {
		m_noMembre= noMembre;
		m_prenom= prenom;
		m_nom= nom;
		m_motDePasse= motDePasse;
		m_admin= admin;
    }
    public boolean getAdmin()
    {
        return m_admin;
    }
    public String getMotDePasse()
    {
        return m_motDePasse;
    }
    public String getNom()
    {
        return m_nom;
    }
    public String getPrenom()
    {
        return m_prenom;
    }
    public int getNoMembre()
    {
        return m_noMembre;
    }

}
