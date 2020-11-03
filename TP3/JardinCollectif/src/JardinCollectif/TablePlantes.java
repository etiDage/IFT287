package JardinCollectif;

import java.util.List;

import javax.persistence.TypedQuery;

public class TablePlantes {
	private TypedQuery<TuplePlante> stmtExist;
	private TypedQuery<TuplePlante> stmtSelectAll;
	private Connexion cx;

	public TablePlantes(Connexion cx)
	{
		this.cx = cx;
		stmtExist = cx.getConnection().createQuery("select p from TuplePlante p where p.m_nomPlante = :nomPlante", TuplePlante.class);
		stmtSelectAll = cx.getConnection().createQuery("select p from TuplePlante p", TuplePlante.class);
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomPlante)
	{
		stmtExist.setParameter("nomPlante", nomPlante);
		return !stmtExist.getResultList().isEmpty();
	}
	
	public void ajouterPlante(String nomPlante, int tempsCulture)
	{
		TuplePlante p = new TuplePlante(nomPlante, tempsCulture);
		cx.getConnection().persist(p);
	}
	
	public void supprimer(String nomPlante)
	{
		if(exist(nomPlante))
		{
			TuplePlante p = getPlante(nomPlante);
			cx.getConnection().remove(p);
		}
	}
	
	private TuplePlante getPlante(String nomPlante)
	{
		stmtExist.setParameter("nomPlante", nomPlante);
		List<TuplePlante> plante = stmtExist.getResultList();
		if(!plante.isEmpty())
		{
			return plante.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public int getTempsDeCulture(String nomPlante)
	{
		TuplePlante plantes = getPlante(nomPlante);
		return plantes.getTmpCulture();
	}
	
	public List<TuplePlante> getAllPlantes()
	{
		return stmtSelectAll.getResultList();
	}

}
