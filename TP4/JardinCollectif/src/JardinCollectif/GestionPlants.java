package JardinCollectif;

import java.sql.Date;

public class GestionPlants {
	private TablePlants tablePlants;
	private TableLots tableLots;
	private TablePlantes tablePlantes;
	private TableMembres tableMembres;
	
	public GestionPlants(TablePlants tablePlants, TableLots tableLots, TablePlantes tablePlantes, TableMembres tableMembres) throws IFT287Exception
	{
        if (tablePlants.getConnexion() != tableLots.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableLots n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tablePlantes.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TablePlantes n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableMembres n'utilisent pas la même connexion au serveur");
        this.tablePlants = tablePlants;
        this.tableLots = tableLots;
        this.tablePlantes = tablePlantes;
        this.tableMembres = tableMembres;
	}
	
	public void planterPlante(String nomPlante, String nomLot, int noMembre, int nbExemplaires, Date datePlantaison) throws Exception
	{
		try
		{
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}			
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Lot " +nomLot+" est inexistant");
			}
			if(!tablePlantes.exist(nomPlante))
			{
				throw new IFT287Exception("Plante " + nomPlante + " est inexistante");
			}
			
			TupleMembre membre = tableMembres.getMembre(noMembre);

			if(!tableLots.estAssigner(membre, nomLot))
			{
				throw new IFT287Exception("Le membre " + noMembre + " est pas assign� au lot " + nomLot);
			}
			if(tablePlants.existLotPlante(nomLot, nomPlante)) 
			{
				throw new IFT287Exception("il y a d�j� des " + nomPlante +"plant� dans le lot "+ nomLot +"Plant� par "+ noMembre);
			}
			
			TupleLot lot = tableLots.getLot(nomLot);
			TuplePlante plante = tablePlantes.getPlante(nomPlante);

			// on plante la plante
			tablePlants.ajouterPlants(lot, plante, datePlantaison, nbExemplaires);
			
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public void recolterPlante(String nomPlante, String nomLot, int noMembre) throws Exception
	{
		try
		{
			if(!tableMembres.exist(noMembre))
			{
				throw new IFT287Exception("Le noMembre " + noMembre + " n'existe pas dans la liste de membre");
			}			
			if(!tableLots.exist(nomLot))
			{
				throw new IFT287Exception("Lot " +nomLot+" est inexistant");
			}
			if(!tablePlantes.exist(nomPlante))
			{
				throw new IFT287Exception("Plante " +nomPlante+" est inexistante");
			}
			
			TupleMembre membre = tableMembres.getMembre(noMembre);
			
			if(!tableLots.estAssigner(membre, nomLot))
			{
				throw new IFT287Exception("Ce membre("+noMembre+") ne travaille pas dans ce lot "+ nomLot);
			}
			if(!tablePlants.existLotPlante(nomLot, nomPlante))
			{
				throw new IFT287Exception("Ce lot("+nomLot+") n'a pas de "+ nomPlante);
			}
			if(tablePlants.getNbJourEnCulture(nomLot, nomPlante) < tablePlantes.getTempsDeCulture(nomPlante))
			{
				throw new IFT287Exception("Les plantes ne sont pas pretes ");
			}
			
			// On recolte les plants
			tablePlants.supprimer(nomLot, nomPlante);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
