package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableDemandes {


	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDeleteParNoMembre;
	private PreparedStatement stmtDeleteParNomLots;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtGetAll;
	private Connexion cx;
	
	public TableDemandes(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.demandes(nomembre, nomlots) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomembre FROM jardincollectif.demandes WHERE nomembre = ? AND nomlots = ?");
		stmtDeleteParNoMembre = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomembre = ?");
		stmtDeleteParNomLots = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomlots = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomlots = ? AND nomembre = ?");
		stmtGetAll = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.demandes");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String userId,String nomLots) throws SQLException
	{
		stmtExist.setString(1, userId);
		stmtExist.setString(2, nomLots);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public void ajouterDemandes(String userId,String nomLots) throws SQLException
	{
		stmtInsert.setString(1, userId);
		stmtInsert.setString(2, nomLots);
		stmtInsert.executeUpdate();
	}
	
	public void supprimerParNoMembre(String userId) throws SQLException
	{
		stmtDeleteParNoMembre.setString(1, userId);
		stmtDeleteParNoMembre.executeUpdate();
	}
	
	public void supprimerParNomLots(String nomLots) throws SQLException
	{
		stmtDeleteParNomLots.setString(1, nomLots);
		stmtDeleteParNomLots.executeUpdate();
	}
	
	public void supprimerDemande(String userId, String nomLot) throws SQLException
	{
		stmtDelete.setString(1, nomLot);
		stmtDelete.setString(2, userId);
		stmtDelete.executeUpdate();
	}
	
	public List<TupleDemande> getAllDemandes() throws SQLException
	{
		ResultSet rs = stmtGetAll.executeQuery();
		List<TupleDemande> demandes = new ArrayList<TupleDemande>();
		while(rs.next())
		{
			demandes.add(new TupleDemande(rs.getString(1), rs.getString(2)));
		}
		rs.close();
		return demandes;
	}

}
