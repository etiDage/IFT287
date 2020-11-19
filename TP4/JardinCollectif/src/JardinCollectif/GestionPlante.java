package JardinCollectif;

public class GestionPlante {
	private TablePlantes tablePlantes;
	private TablePlants tablePlants;
	
	public GestionPlante(TablePlantes tablePlantes, TablePlants tablePlants) throws IFT287Exception
	{
        if (tablePlantes.getConnexion() != tablePlants.getConnexion())
            throw new IFT287Exception("Les instances de TablePlantes et de TablePlants n'utilisent pas la même connexion au serveur");
		this.tablePlantes = tablePlantes;
		this.tablePlants = tablePlants;
	}
	
	public void ajouterPlante(String nomPlante, int tempsDeCulture) throws Exception
	{
		try
		{
			if(tablePlantes.exist(nomPlante))
			{
				throw new IFT287Exception("La plante " + nomPlante + " est deja dans la liste de membres.");
			}
			// Ajout du membre a la table
			tablePlantes.ajouterPlante(nomPlante, tempsDeCulture);
		}
		catch(Exception e)
		{
			throw e;
		}

	}
	
	public void supprimerPlante(String nomPlante) throws Exception
	{
		try
		{
			if(tablePlants.planteEstCultiver(nomPlante))
			{
				throw new IFT287Exception("La plante que vous tentez de supprimer est presentement en culture, donc impossible de la supprimer.");
			}
			tablePlantes.supprimer(nomPlante);
			
		}
		catch(Exception e)
		{
			throw e;
		}

	}
}
