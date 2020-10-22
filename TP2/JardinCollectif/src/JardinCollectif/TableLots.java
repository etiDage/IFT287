package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableLots {

	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TableLots(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.lots(nomLots, nbMaxMembre) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomLots FROM jardincollectif.lots WHERE nomLots = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.lots WHERE nomLots = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomLots) throws SQLException
	{
		stmtExist.setString(1, nomLots);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	public void ajouterLot(String nomLots, int nbMaxMembre) throws SQLException
	{
		stmtInsert.setString(1, nomLots);
		stmtInsert.setInt(2, nbMaxMembre);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(String nomLots) throws SQLException
	{
		stmtDelete.setString(1, nomLots);
		stmtDelete.executeUpdate();
	}

}
