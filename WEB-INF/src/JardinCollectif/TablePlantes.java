package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablePlantes {
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtGetAll;
	private Connexion cx;

	public TablePlantes(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.plante(nomplante, tempsculture) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomplante FROM jardincollectif.plante WHERE nomplante = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.plante WHERE nomplante = ?");
		stmtGetAll = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.plante");
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
	public List<TuplePlante> getAllPlante() throws SQLException
	{
		ResultSet rs = stmtGetAll.executeQuery();
		List<TuplePlante> list = new ArrayList<TuplePlante>();
		while(rs.next())
		{
			list.add(new TuplePlante(rs.getString(1), rs.getInt(2)));
		}
		rs.close();
		return list;
	}

}
