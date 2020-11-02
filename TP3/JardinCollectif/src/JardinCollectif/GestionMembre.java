package JardinCollectif;

import java.sql.SQLException;

public class GestionMembre {
	private Connexion cx;
	private TableMembres tableMembres;
	private TableLots tableLots;
	
	
	public GestionMembre(Connexion cx, TableMembres tableMembres, TableLots tableLots) throws IFT287Exception
	{
		this.cx = cx;
        if (tableMembres.getConnexion() != tableLots.getConnexion())
            throw new IFT287Exception("Les instances de TableMembres et de TableLots n'utilisent pas la même connexion au serveur");
        this.tableMembres = tableMembres;
		this.tableLots = tableLots;
	}
	
	public void inscrireMembre(int nomembre, String prenom, String nom, String motDePasse) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(tableMembres.exist(nomembre))
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
	
	public void supprimerMembre(int nomembre) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(tableLots.nbMinMembreParLot(nomembre) <= 1)
			{
				throw new IFT287Exception("Le membre que vous tentez de supprimer est seul sur un lot, donc impossible de le supprimer");
			}
			tableLots.supprimerParNoMembre(nomembre);
			tableLots.supprimerParNoMembre(nomembre);
			tableMembres.supprimer(nomembre);
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public void promouvoirMembre(int nomembre) throws Exception
	{
		try
		{
			cx.demarreTransaction();
			if(!tableMembres.exist(nomembre))
			{
				throw new IFT287Exception("Le membre " + nomembre + " n'est pas present dans la liste de membre.");
			}
			tableMembres.setAdmin(nomembre);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
}
