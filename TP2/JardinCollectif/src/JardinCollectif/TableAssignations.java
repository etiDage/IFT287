package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableAssignations {

	private Connexion cx;
	private PreparedStatement stmtNbMembreLots;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;

	
	public TableAssignations(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtNbMembreLots = cx.getConnection().prepareStatement("SELECT COALESCE(min(sub.nb), 2) " + 
				"FROM (SELECT count(a.nomlots) AS nb " + 
				"		FROM jardincollectif.assignation a " + 
				"		WHERE a.nomlots IN (SELECT nomlots FROM jardincollectif.assignation WHERE nomembre = ?) " + 
				"		GROUP BY a.nomlots) sub;");
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.assignation(noMembre, nomLots) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT noMembre FROM jardincollectif.assignation WHERE noMembre = ? AND nomLots = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.assignation WHERE noMembre = ? AND nomLots = ?");

	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public int nbMinMembreParLot(int nomembre) throws SQLException
	{
		stmtNbMembreLots.setInt(1, nomembre);
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
	
	public boolean exist(int noMembre,String nomLots) throws SQLException
	{
		stmtExist.setInt(1, noMembre);
		stmtExist.setString(2, nomLots);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public void ajouterAssignations(int noMembre,String nomLots) throws SQLException
	{
		stmtInsert.setInt(1, noMembre);
		stmtInsert.setString(2, nomLots);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(int noMembre,String nomLots) throws SQLException
	{
		stmtDelete.setInt(1, noMembre);
		stmtDelete.setString(2, nomLots);
		stmtDelete.executeUpdate();
	}
}
