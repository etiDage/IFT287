package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableAssignations {

	private Connexion cx;
	private PreparedStatement stmtNbMembreLots;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDeleteParNoMembre;
	private PreparedStatement stmtDeleteParNomLots;
	private PreparedStatement stmtGetNbMembreLot;
	private PreparedStatement stmtEstAssigner;

	
	public TableAssignations(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtNbMembreLots = cx.getConnection().prepareStatement("SELECT COALESCE(min(sub.nb), 2) " + 
				"FROM (SELECT count(a.nomlots) AS nb " + 
				"		FROM jardincollectif.assignation a " + 
				"		WHERE a.nomlots IN (SELECT nomlots FROM jardincollectif.assignation WHERE nomembre = ?) " + 
				"		GROUP BY a.nomlots) sub;");
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.assignation(nomembre, nomlots) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomembre FROM jardincollectif.assignation WHERE nomembre = ? AND nomlots = ?");
		stmtDeleteParNoMembre = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.assignation WHERE nomembre = ?");
		stmtDeleteParNomLots = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.assignation WHERE nomlots = ?");
		stmtGetNbMembreLot = cx.getConnection().prepareStatement("SELECT count(nomlots) FROM jardincollectif.assignation WHERE nomlots = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public int nbMinMembreParLot(String userId) throws SQLException
	{
		stmtNbMembreLots.setString(1, userId);
		ResultSet rs = stmtNbMembreLots.executeQuery();
		
		int res;
		if(rs.next())
		{
			res = rs.getInt(1);
		}
		else
		{
			res = 0;
		}
		rs.close();
		return res;
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
	
	public void ajouterAssignations(String userId,String nomLots) throws SQLException
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
	
	public int getNbMembreLot(String nomLot) throws SQLException
	{
		stmtGetNbMembreLot.setString(1, nomLot);
		ResultSet rs = stmtGetNbMembreLot.executeQuery();
		rs.next();
		int nbMembre = rs.getInt(1);
		rs.close();
		return nbMembre;
	}

}
