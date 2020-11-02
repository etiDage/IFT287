package JardinCollectif;

import java.sql.SQLException;

public class GestionLot {
	private Connexion cx;
	private TableLots tableLots;
	private TableMembres tableMembres;
	private TablePlants tablePlants;
	
	
	public GestionLot(Connexion cx, TableLots tableLots, TableMembres tableMembres, TablePlants tablePlants) throws IFT287Exception
	{
		this.cx = cx;
        if (tableLots.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TableMembres n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableMembres n'utilisent pas la même connexion au serveur");
        if (tableLots.getConnexion() != tablePlants.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TablePlants n'utilisent pas la même connexion au serveur");
        this.tableLots = tableLots;
        this.tableMembres = tableMembres;
        this.tablePlants = tablePlants;
	}
	
	public void ajouterLot(String nomLot, int nbMaxMembre) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			
			if(tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " est deja dans la liste de lots.");
			}
			// Ajout du membre a la table
			tableLots.ajouterLot(nomLot, nbMaxMembre);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public void supprimerLot(String nomLot) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(tablePlants.LotEstEnCulture(nomLot))
			{
				throw new IFT287Exception("Le lot que vous tentez de supprimer a encore des palntes non-cultiver, donc impossible de le supprimer");
			}
			tableLots.supprimer(nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public void rejoindreLot(int noMembre, String nomLot) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " n'existe pas dans la liste de lot");
			}
			if(tableLots.exist(noMembre, nomLot))
			{
				throw new IFT287Exception("Une demande existe deja pour le membre " + noMembre + " au lot " + nomLot);
			}
			tableLots.ajouterDemandes(noMembre, nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public void accepterDemande(int noMembre, String nomLot) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(!tableLots.existDemande(noMembre, nomLot))
			{
				throw new IFT287Exception("Aucune demande appartient au noMembre " + noMembre + " et au lot " + nomLot);
			}
			if(tableLots.getNbMembreLot(nomLot) >= tableLots.getNbMaxMembre(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " est a son nombre maximal de membre");
			}
			if(tableLots.existAssignation(noMembre, nomLot))
			{
				throw new IFT287Exception("Le membre " + noMembre + " est deja assigner au lot " + nomLot);
			}
			tableLots.ajouterAssignations(noMembre, nomLot);
			tableLots.supprimerDemande(noMembre, nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public void refuserDemande(int noMembre, String nomLot) throws SQLException
	{
		try
		{
			cx.demarreTransaction();
			tableLots.supprimerDemande(noMembre, nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
}
