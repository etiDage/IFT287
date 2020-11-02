package JardinCollectif;

import java.util.List;

import javax.persistence.TypedQuery;

public class TableLots {

	private TypedQuery<TupleLot> stmtExist;
	private Connexion cx;
	
	public TableLots(Connexion cx)
	{
		this.cx = cx;
		stmtExist = cx.getConnection().createQuery("select l from TupleLot l where l.nom = :nomLot", TupleLot.class);
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomLot)
	{
		stmtExist.setParameter("nomLot", nomLot);
		return !stmtExist.getResultList().isEmpty();
	}
	public void ajouterLot(String nomLot, int nbMaxMembre)
	{
		TupleLot l = new TupleLot(nomLot, nbMaxMembre);
		cx.getConnection().persist(l);
	}
	
	public void supprimer(String nomLot)
	{
		if(exist(nomLot))
		{
			TupleLot l = getLot(nomLot);
			cx.getConnection().remove(l);
		}
	}
	
	public int getNbMaxMembre(String nomLot)
	{
		if(exist(nomLot))
		{
			TupleLot l = getLot(nomLot);
			return l.getNbMaxMembre();
		}
		else
		{
			return -1;
		}
	}
	
	private TupleLot getLot(String nomLot)
	{
		stmtExist.setParameter("nomLot", nomLot);
		List<TupleLot> lot = stmtExist.getResultList();
		if(!lot.isEmpty())
		{
			return lot.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public boolean estAssigner(int noMembre)
	{
		
	}
	
	

}
