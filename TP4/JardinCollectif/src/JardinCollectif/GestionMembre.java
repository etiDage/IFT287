package JardinCollectif;



public class GestionMembre {

	private TableMembres tableMembres;
	private TableLots tableLots;
	
	
	public GestionMembre(TableMembres tableMembres, TableLots tableLots) throws IFT287Exception
	{

        if (tableMembres.getConnexion() != tableLots.getConnexion())
            throw new IFT287Exception("Les instances de TableMembres et de TableLots n'utilisent pas la même connexion au serveur");
        this.tableMembres = tableMembres;
		this.tableLots = tableLots;
	}
	
	public void inscrireMembre(int nomembre, String prenom, String nom, String motDePasse) throws Exception
	{
		try
		{
			if(tableMembres.exist(nomembre))
			{
				throw new IFT287Exception("Le membres " + nomembre + " est deja dans la liste de membres.");
			}
			// Ajout du membre a la table
			tableMembres.inscrire(nomembre, prenom, nom, motDePasse);
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void supprimerMembre(int noMembre) throws Exception
	{
		try
		{
			if(tableLots.seulSurUnLot(noMembre))
			{
				throw new IFT287Exception("Le membre que vous tentez de supprimer est seul sur un lot, donc impossible de le supprimer");
			}
			tableLots.supprimerMembre(noMembre);
			tableMembres.supprimer(noMembre);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void promouvoirMembre(int nomembre) throws Exception
	{
		try
		{
			if(!tableMembres.exist(nomembre))
			{
				throw new IFT287Exception("Le membre " + nomembre + " n'est pas present dans la liste de membre.");
			}
			tableMembres.setAdmin(nomembre);
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
