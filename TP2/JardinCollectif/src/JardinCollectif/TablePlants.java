package JardinCollectif;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TablePlants {
	
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TablePlants(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.plants(nomLots, nomPlante, datePlantaison, nbPlants, noMembre) VALUES " + 
				"(?, ?, ?, ?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomPlante, nomLots, noMembre FROM jardincollectif.plants WHERE nomLots = ? AND nomPlante= ? AND noMembre= ? ");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.plants WHERE nomLots = ? AND nomPlante= ? AND noMembre= ? ");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomLots, String nomPlante, int noMembre) throws SQLException
	{
		stmtExist.setString(1, nomLots);
		stmtExist.setString(2, nomPlante);
		stmtExist.setInt(3, noMembre);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public void ajouterPlants(String nomLots, String nomPlante, Date datePlantaison ,int nbPlants , int noMembre) throws SQLException
	{
		stmtInsert.setString(1, nomLots);
		stmtInsert.setString(2, nomPlante);
		stmtInsert.setDate(3, datePlantaison);
		stmtInsert.setInt(4, nbPlants);
		stmtInsert.setInt(5, noMembre);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(String nomLots, String nomPlante, int noMembre) throws SQLException
	{
		stmtDelete.setString(1, nomLots);
		stmtDelete.setString(2, nomPlante);
		stmtDelete.setInt(3, noMembre);
		stmtDelete.executeUpdate();
	}

}
