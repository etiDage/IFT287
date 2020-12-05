package JardinCollectif;

import java.sql.SQLException;
import java.util.List;

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
	
	public boolean informationsConnexionValide(String userId, String motDePasse) throws Exception
	{
		try {
			if(!tableMembres.exist(userId))
			{
				throw new IFT287Exception("Le membres " + userId + " n'est pas dans la liste de membres.");
			}
			
			TupleMembre user = tableMembres.getMembre(userId);
			if(!user.getMotDePasse().contentEquals(motDePasse))
			{
				throw new IFT287Exception("Mauvais mot de passe");
			}
			cx.commit();
			return true;
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public boolean utilisateurEstAdmin(String userId) throws Exception
	{
		try
		{
			TupleMembre user = tableMembres.getMembre(userId);
			if(user == null)
			{
				throw new IFT287Exception("L'utilisateur n'existe pas");
			}
			cx.commit();
			return user.getAdmin();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	
	public void inscrireMembre(String userId, String prenom, String nom, String motDePasse) throws Exception
	{
		try
		{
			if(tableMembres.exist(userId))
			{
				throw new IFT287Exception("Le membres " + userId + " est deja dans la liste de membres.");
			}
			// Ajout du membre a la table
			tableMembres.inscrire(userId, prenom, nom, motDePasse);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public void supprimerMembre(String userId) throws Exception
	{
		try
		{
			if(tableAssignations.nbMinMembreParLot(userId) <= 1)
			{
				throw new IFT287Exception("Le membre que vous tentez de supprimer est seul sur un lot, donc impossible de le supprimer");
			}
			tableDemandes.supprimerParNoMembre(userId);
			tableAssignations.supprimerParNoMembre(userId);
			tableMembres.supprimer(userId);
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public void promouvoirMembre(String userId) throws Exception
	{
		try
		{
			if(!tableMembres.exist(userId))
			{
				throw new IFT287Exception("Le membre " + userId + " n'est pas present dans la liste de membre.");
			}
			tableMembres.setAdmin(userId);
			
			cx.commit();
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public List<TupleMembre> getListMembres() throws Exception
	{
		try
		{
			List<TupleMembre> membres = tableMembres.getListMembre();
			cx.commit();
			return membres;
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
	}
	
	public boolean exist(String userId) throws SQLException
	{
		try
		{
			boolean exist = tableMembres.exist(userId);		
			cx.commit();
			return exist;
		}
		catch(Exception e)
		{
			cx.rollback();
			throw e;
		}
		
	}
	
}
