package JardinCollectif;

import java.util.List;

import javax.persistence.TypedQuery;


public class TableMembres {

	private TypedQuery<TupleMembre> stmtExist;
	private TypedQuery<TupleMembre> stmtSetAdmin;
	private Connexion cx;
	
	
	public TableMembres(Connexion cx)
	{
		this.cx = cx;
        stmtExist = cx.getConnection().createQuery("select m from TupleMembre m where m.m_noMembre = :noMembre", TupleMembre.class);
        stmtSetAdmin = cx.getConnection().createQuery("update TupleMembre set m_admin = TRUE where noMembre = :noMembre", TupleMembre.class);
//		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.membres(nomembre, prenom, nom, motdepasse, admin) VALUES " + 
//				"(?, ?, ?, ?, TRUE);");
//		stmtExist = cx.getConnection().prepareStatement("SELECT nomembre FROM jardincollectif.membres WHERE nomembre = ?");
//		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.membres WHERE nomembre = ?");
//		stmtSetAdmin = cx.getConnection().prepareStatement("UPDATE jardincollectif.membres SET admin = TRUE WHERE nomembre = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}

	public boolean exist(int noMembre)
	{
		stmtExist.setParameter("noMembre", noMembre);
        return !stmtExist.getResultList().isEmpty();
	}
	
	public void inscrire(int noMembre, String prenom, String nom, String motdepasse) 
	{
		TupleMembre m = new TupleMembre(noMembre, prenom, nom, motdepasse, true);
		cx.getConnection().persist(m);
	}
	
	public void supprimer(int noMembre)
	{
		if(exist(noMembre))
		{
			TupleMembre m = getMembre(noMembre);
			cx.getConnection().remove(m);
		}
	}
	
	public void setAdmin(int noMembre)
	{
		stmtSetAdmin.setParameter("noMembre", noMembre);
		stmtSetAdmin.executeUpdate();
	}
	
	private TupleMembre getMembre(int noMembre)
	{
		stmtExist.setParameter("noMembre", noMembre);
		List<TupleMembre> membre = stmtExist.getResultList();
		
		if(!membre.isEmpty())
		{
			return membre.get(0);
		}
		else
		{
			return null; // Ce membre n'existe pas
		}
	}
}
