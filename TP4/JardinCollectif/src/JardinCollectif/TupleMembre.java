package JardinCollectif;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

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

public class TupleMembre {
	
	private int m_noMembre;
	private String m_prenom;
	private String m_nom;
	private String m_motDePasse;
	private boolean m_admin;
	
	public TupleMembre()
    {
    }
		
	public TupleMembre(Document d)
	{
		m_noMembre = d.getInteger("noMembre");
		m_prenom = d.getString("prenom");
		m_nom = d.getString("nom");
		m_motDePasse = d.getString("motDePasse");
		m_admin = d.getBoolean("admin");
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

    public void setAdmin()
    {
    	m_admin = true;
    }
    
    public Document toDocument()
    {
    	return new Document().append("noMembre", m_noMembre)
    						 .append("prenom", m_prenom)
    						 .append("nom", m_nom)
    						 .append("motDePasse", m_motDePasse)
    						 .append("admin", m_admin);
    }
        
    @Override
    public boolean equals(Object o)
    {
    	if (o == this)
    	{
    		return true;
    	}
    	
    	if(!(o instanceof TupleMembre))
    	{
    		return false;
    	}
    	
    	TupleMembre m = (TupleMembre) o;
    	
    	return this.getNoMembre() == m.getNoMembre();
    }
}
