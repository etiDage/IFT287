package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDemandes {


	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDeleteParNoMembre;
	private PreparedStatement stmtDeleteParNomLots;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TableDemandes(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.demandes(nomembre, nomlots) VALUES " + 
				"(?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomembre FROM jardincollectif.demandes WHERE nomembre = ? AND nomlots = ?");
		stmtDeleteParNoMembre = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomembre = ?");
		stmtDeleteParNomLots = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomlots = ?");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.demandes WHERE nomlots = ? AND nomembre = ?");
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
	
	public void supprimerParNoMembre(int noMembre) throws SQLException
	{
		stmtDeleteParNoMembre.setInt(1, noMembre);
		stmtDeleteParNoMembre.executeUpdate();
	}
	
	public void supprimerParNomLots(String nomLots) throws SQLException
	{
		stmtDeleteParNoMembre.setString(1, nomLots);
		stmtDeleteParNoMembre.executeUpdate();
	}
	
	public void supprimerDemande(int noMembre, String nomLot) throws SQLException
	{
		stmtDelete.setString(1, nomLot);
		stmtDelete.setInt(2, noMembre);
		stmtDelete.executeUpdate();
	}

}
