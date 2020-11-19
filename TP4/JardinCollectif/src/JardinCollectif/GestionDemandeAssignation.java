package JardinCollectif;


public class GestionDemandeAssignation {
	private TableMembres tableMembres;
	private TableLots tableLots;
	
	
	public GestionDemandeAssignation(TableMembres tableMembres, TableLots tableLots) throws IFT287Exception
	{
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
			
			TupleMembre membre = tableMembres.getMembre(noMembre);
			
			if(tableLots.existeDemande(membre, nomLot))
			{
				throw new IFT287Exception("Une demande existe deja pour le membre " + noMembre + " au lot " + nomLot);
			}
			
			tableLots.ajouterDemande(membre, nomLot);
			
		}
		catch(Exception e)
		{

			throw e;
		}
	}
	
	public void accepterDemande(int noMembre, String nomLot) throws Exception
	{
		try
		{
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}
			
			TupleMembre membre = tableMembres.getMembre(noMembre);

			if(!tableLots.existeDemande(membre, nomLot))
			{
				throw new IFT287Exception("Aucune demande appartient au noMembre " + noMembre + " et au lot " + nomLot);
			}
			if(tableLots.getNbMembreLot(nomLot) >= tableLots.getNbMaxMembre(nomLot))
			{
				throw new IFT287Exception("Le lot " + nomLot + " est a son nombre maximal de membre");
			}
			if(tableLots.estAssigner(membre, nomLot))
			{
				throw new IFT287Exception("Le membre " + noMembre + " est deja assigner au lot " + nomLot);
			}
			tableLots.accepterDemande(membre, nomLot);

		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public void refuserDemande(int noMembre, String nomLot) throws Exception
	{
		try
		{
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}
			
			TupleMembre membre = tableMembres.getMembre(noMembre);

			if(!tableLots.existeDemande(membre, nomLot))
			{
				throw new IFT287Exception("Aucune demande appartient au noMembre " + noMembre + " et au lot " + nomLot);
			}
			
			tableLots.supprimerDemande(membre, nomLot);
			
		}
		catch(Exception e)
		{
			throw e;
		}

	}
}
