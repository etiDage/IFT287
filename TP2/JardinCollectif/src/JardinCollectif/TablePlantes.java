package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TablePlantes {
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private Connexion cx;

	public TablePlantes(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.plante(nomPlante, tempsCulture) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomPlante FROM jardincollectif.plante WHERE nomPlante = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.plante WHERE nomPlante = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomPlante) throws SQLException
	{
		stmtExist.setString(1, nomPlante);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public void ajouterPlante(String nomPlante, int tempsCulture) throws SQLException
	{
		stmtInsert.setString(1, nomPlante);
		stmtInsert.setInt(2, tempsCulture);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(String nomPlante) throws SQLException
	{
		stmtDelete.setString(1, nomPlante);
		stmtDelete.executeUpdate();
	}

}
