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
	
	public void planterPlante(String nomPlante, String nomLot, int noMembre, int nbExemplaires, Date datePlantaison)
	{
		try
		{
			if(tableAssignations.exist(nomembre))
			{
				throw new IFT287Exception("Le membres " + nomembre + " est deja dans la liste de membres.");
			}
			// Ajout du membre a la table
			tableMembres.inscrire(nomembre, prenom, nom, motDePasse);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
}
