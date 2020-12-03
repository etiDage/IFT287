package JardinCollectif;

import java.sql.SQLException;
import java.util.List;

public class GestionLot {
	private Connexion cx;
	private TableLots tableLots;
	private TableAssignations tableAssignations;
	private TablePlants tablePlants;
	private TableDemandes tableDemandes;
	
	
	public GestionLot(Connexion cx, TableLots tableLots, TableAssignations tableAssignations, TablePlants tablePlants, TableDemandes tableDemandes) throws IFT287Exception
	{
		this.cx = cx;
        if (tableLots.getConnexion() != tableAssignations.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tableAssignations.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tableLots.getConnexion() != tableDemandes.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TableDemandes n'utilisent pas la même connexion au serveur");
        this.tableLots = tableLots;
        this.tableAssignations = tableAssignations;
        this.tablePlants = tablePlants;
        this.tableDemandes = tableDemandes;
	}
	
	public void ajouterLot(String nomLot, int nbMaxMembre) throws Exception
	{
		try
		{
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
			if(tablePlants.LotEstEnCulture(nomLot))
			{
				throw new IFT287Exception("Le lot que vous tentez de supprimer a encore des palntes non-cultiver, donc impossible de le supprimer");
			}
			tableLots.supprimer(nomLot);
			tableAssignations.supprimerParNomLots(nomLot);
			tableDemandes.supprimerParNomLots(nomLot);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public List<TupleLot> getAllLot() throws SQLException
	{
		return tableLots.getAllLot();
	}
}
