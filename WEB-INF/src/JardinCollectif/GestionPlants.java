package JardinCollectif;

import java.sql.Date;

public class GestionPlants {
	private Connexion cx;
	private TablePlants tablePlants;
	private TableLots tableLots;
	private TablePlantes tablePlantes;
	private TableAssignations tableAssignations;
	
	public GestionPlants(Connexion cx, TablePlants tablePlants, TableLots tableLots, TablePlantes tablePlantes, TableAssignations tableAssignations) throws IFT287Exception
	{
		this.cx = cx;
        if (tablePlants.getConnexion() != tableAssignations.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tableLots.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tablePlantes.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableAssignations n'utilisent pas la même connexion au serveur");
        this.tablePlants = tablePlants;
        this.tableLots = tableLots;
        this.tablePlantes = tablePlantes;
        this.tableAssignations = tableAssignations;
	}
	
	public void planterPlante(String nomPlante, String nomLot, String userId, int nbExemplaires, Date datePlantaison) throws Exception
	{
		try
		{
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Lot " +nomLot+" est inexistant");
			}
			if(!tablePlantes.exist(nomPlante))
			{
				throw new IFT287Exception("Plante " + nomPlante + " est inexistante");
			}
			if(!tableAssignations.exist(userId, nomLot))
			{
				throw new IFT287Exception("Le membre " + userId + " est pas assign� au lot " + nomLot);
			}
			if(tablePlants.existLotPlante(nomLot, nomPlante)) 
			{
				throw new IFT287Exception("il y a d�j� des " + nomPlante +"plant� dans le lot "+ nomLot +"Plant� par "+ userId);
			}
			
			// Ajout du membre a la table
			tablePlants.ajouterPlants(nomLot, nomPlante, datePlantaison, nbExemplaires);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public void recolterPlante(String nomPlante, String nomLot, String userId) throws Exception
	{
		try
		{
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Lot " +nomLot+" est inexistant");
			}
			if(!tablePlantes.exist(nomPlante))
			{
				throw new IFT287Exception("Plante " +nomPlante+" est inexistante");
			}
			if(!tableAssignations.exist(userId, nomLot))
			{
				throw new IFT287Exception("Ce membre("+userId+") ne travaille pas dans ce lot "+ nomLot);
			}
			if(!tablePlants.existLotPlante(nomLot, nomPlante))
			{
				throw new IFT287Exception("Ce lot("+nomLot+") n'a pas de "+ nomPlante);
			}
			if(!tablePlants.RecolteReady(nomLot, nomPlante))
			{
				throw new IFT287Exception("Plante "+nomPlante +" Pas pr�t pour la r�colte");
			}
			
			tablePlants.supprimer(nomLot, nomPlante);
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
}
