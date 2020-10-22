package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDemandes {


	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TableDemandes(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.demandes(noMembre, nomLots) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT noMembre FROM jardincollectif.demandes WHERE noMembre = ? AND nomLots = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE noMembre = ? AND nomLots = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
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
	
	public void ajouterDemandes(int noMembre,String nomLots) throws SQLException
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
