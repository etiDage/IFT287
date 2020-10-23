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
	
	public void planterPlante(String nomPlante, String nomLot, int noMembre, int nbExemplaires, Date datePlantaison) throws Exception
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
			if(!tableAssignations.exist(noMembre, nomLot))
			{
				throw new IFT287Exception("Le membre " + noMembre + " est pas assign� au lot " + nomLot);
			}
			if(tablePlants.exist(nomLot, nomPlante, noMembre)) 
			{
				throw new IFT287Exception("il y a d�j� des " + nomPlante +"plant� dans le lot "+ nomLot +"Plant� par "+ noMembre);
			}
			
			// Ajout du membre a la table
			tablePlants.ajouterPlants(nomLot, nomPlante, datePlantaison, nbExemplaires, noMembre);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public void recolterPlante(String nomPlante, String nomLot, int noMembre) throws Exception
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
			if(!tableAssignations.exist(noMembre, nomLot))
			{
				throw new IFT287Exception("Ce membre("+noMembre+") ne travaille pas dans ce lot "+ nomLot);
			}
			if(!tablePlants.RecolteReady(nomLot, nomPlante, noMembre))
			{
				throw new IFT287Exception("Plante "+nomPlante +" Pas pr�t pour la r�colte");
			}
			tablePlants.supprimer(nomPlante, nomLot, noMembre);
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
}
