package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMembres {

	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtSetAdmin;
	private Connexion cx;
	
	
	public TableMembres(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.membres(nomembre, prenom, nom, motdepasse, admin) VALUES " + 
				"(?, ?, ?, ?, TRUE);");
		stmtExist = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.membres WHERE nomembre = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.membres WHERE nomembre = ?");
		stmtSetAdmin = cx.getConnection().prepareStatement("UPDATE jardincollectif.membres SET admin = TRUE WHERE nomembre = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}

	public boolean exist(int nomembre) throws SQLException
	{
		stmtExist.setInt(1, nomembre);
		ResultSet rs = stmtExist.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
	
	public TupleMembre getMembre(int noMembre) throws SQLException
	{
		
	}
	
	public void inscrire(int nomembre, String prenom, String nom, String motdepasse) throws SQLException
	{
		stmtInsert.setInt(1, nomembre);
		stmtInsert.setString(2, prenom);
		stmtInsert.setString(3, nom);
		stmtInsert.setString(4, motdepasse);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(int nomembre) throws SQLException
	{
		stmtDelete.setInt(1, nomembre);
		stmtDelete.executeUpdate();
	}
	
	public void setAdmin(int nomembre) throws SQLException
	{
		stmtSetAdmin.setInt(1, nomembre);
		
		stmtSetAdmin.executeUpdate();
	}
}
