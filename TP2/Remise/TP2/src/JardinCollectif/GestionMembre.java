package JardinCollectif;

import java.sql.SQLException;

public class GestionMembre {
	private Connexion cx;
	private TableMembres tableMembres;
	private TableAssignations tableAssignations;
	private TableDemandes tableDemandes;
	
	
	public GestionMembre(Connexion cx, TableMembres tableMembres, TableAssignations tableAssignations, TableDemandes tableDemandes) throws IFT287Exception
	{
		this.cx = cx;
        if (tableMembres.getConnexion() != tableAssignations.getConnexion())
            throw new IFT287Exception("Les instances de TableMembres et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tableDemandes.getConnexion() != tableAssignations.getConnexion())
            throw new IFT287Exception("Les instances de TableDemandes et de TableAssignations n'utilisent pas la même connexion au serveur");
        if (tableMembres.getConnexion() != tableDemandes.getConnexion())
            throw new IFT287Exception("Les instances de TableMembres et de TableDemandes n'utilisent pas la même connexion au serveur");
        this.tableMembres = tableMembres;
		this.tableAssignations = tableAssignations;
		this.tableDemandes = tableDemandes;
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
			if(tableAssignations.nbMinMembreParLot(nomembre) <= 1)
			{
				throw new IFT287Exception("Le membre que vous tentez de supprimer est seul sur un lot, donc impossible de le supprimer");
			}
			tableMembres.supprimer(nomembre);
			tableAssignations.supprimerParNoMembre(nomembre);
			tableDemandes.supprimerParNoMembre(nomembre);
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
