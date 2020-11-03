package JardinCollectif;

import java.sql.SQLException;

public class GestionDemandeAssignation {
	private Connexion cx;
	private TableMembres tableMembres;
	private TableLots tableLots;
	
	
	public GestionDemandeAssignation(Connexion cx, 
			TableMembres tableMembres, TableLots tableLots) throws IFT287Exception
	{
		this.cx = cx;
        if (tableLots.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TableMembres n'utilisent pas la même connexion au serveur");
        this.tableMembres = tableMembres;
        this.tableLots = tableLots;
	}
	
	public void rejoindreLot(int noMembre, String nomLot) throws Exception
	{
		try
		{
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " n'existe pas dans la liste de lot");
			}
			if(tableLots.existDemande(noMembre, nomLot))
			{
				throw new IFT287Exception("Une demande existe deja pour le membre " + noMembre + " au lot " + nomLot);
			}
			tableDemandes.ajouterDemandes(noMembre, nomLot);
			
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
			if(!tableDemandes.exist(noMembre, nomLot))
			{
				throw new IFT287Exception("Aucune demande appartient au noMembre " + noMembre + " et au lot " + nomLot);
			}
			if(tableAssignations.getNbMembreLot(nomLot) >= tableLots.getNbMaxMembre(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " est a son nombre maximal de membre");
			}
			if(tableAssignations.exist(noMembre, nomLot))
			{
				throw new IFT287Exception("Le membre " + noMembre + " est deja assigner au lot " + nomLot);
			}
			tableAssignations.ajouterAssignations(noMembre, nomLot);
			tableDemandes.supprimerDemande(noMembre, nomLot);
			
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
			tableDemandes.supprimerDemande(noMembre, nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
}
