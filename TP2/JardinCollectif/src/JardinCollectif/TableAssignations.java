package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableAssignations {

	private Connexion cx;
	private PreparedStatement stmtNbMembreLots;

	
	public TableAssignations(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtNbMembreLots = cx.getConnection().prepareStatement("SELECT COALESCE(min(sub.nb), 2) " + 
				"FROM (SELECT count(a.nomlots) AS nb " + 
				"		FROM jardincollectif.assignation a " + 
				"		WHERE a.nomlots IN (SELECT nomlots FROM jardincollectif.assignation WHERE nomembre = ?) " + 
				"		GROUP BY a.nomlots) sub;");

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
}
