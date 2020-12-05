package JardinCollectif;

import java.sql.SQLException;
import java.util.List;

public class GestionPlante {
	private Connexion cx;
	private TablePlantes tablePlantes;
	private TablePlants tablePlants;
	
	public GestionPlante(Connexion cx, TablePlantes tablePlantes, TablePlants tablePlants) throws IFT287Exception
	{
		this.cx = cx;
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
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
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
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}

	}
	
	public List<TuplePlante> getAllPlante() throws SQLException
	{
		return tablePlantes.getAllPlante();
	}
}
