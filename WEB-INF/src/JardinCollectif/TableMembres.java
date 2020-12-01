package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableMembres {

	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtSetAdmin;
	private PreparedStatement stmtGetAll;
	private Connexion cx;
	
	
	public TableMembres(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.membres(nomembre, prenom, nom, motdepasse, admin) VALUES " + 
				"(?, ?, ?, ?, FALSE);");
		stmtExist = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.membres WHERE nomembre = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.membres WHERE nomembre = ?");
		stmtSetAdmin = cx.getConnection().prepareStatement("UPDATE jardincollectif.membres SET admin = TRUE WHERE nomembre = ?");
		stmtGetAll = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.membres");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}

	public boolean exist(String userId) throws SQLException
	{
		stmtExist.setString(1, userId);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public TupleMembre getMembre(String userId) throws SQLException
	{
		stmtExist.setString(1, userId);
		ResultSet rs = stmtExist.executeQuery();
		TupleMembre user = null;
		if(rs.next())
		{
			String prenom = rs.getString(2);
			String nom = rs.getString(3);
			String motDePasse = rs.getString(4);
			boolean admin = rs.getBoolean(5);
			
			user = new TupleMembre(userId, prenom, nom, motDePasse, admin);
			rs.close();
		}
		
		return user;
	}
	
	public void inscrire(String userId, String prenom, String nom, String motdepasse) throws SQLException
	{
		stmtInsert.setString(1, userId);
		stmtInsert.setString(2, prenom);
		stmtInsert.setString(3, nom);
		stmtInsert.setString(4, motdepasse);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(String userId) throws SQLException
	{
		stmtDelete.setString(1, userId);
		stmtDelete.executeUpdate();
	}
	
	public void setAdmin(String userId) throws SQLException
	{
		stmtSetAdmin.setString(1, userId);
		
		stmtSetAdmin.executeUpdate();
	}
	
	public List<TupleMembre> getListMembre() throws SQLException
	{
		List<TupleMembre> membresList = new ArrayList<TupleMembre>();
		ResultSet rs = stmtGetAll.executeQuery();
		while(rs.next())
		{
			membresList.add(new TupleMembre(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5)));
		}
		
		return membresList;
	}
}
