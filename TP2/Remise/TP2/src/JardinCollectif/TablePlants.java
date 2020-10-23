package JardinCollectif;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TablePlants {
	
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtExist;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtPlanteCultiver;
	private PreparedStatement stmtLotCultiver;
	private PreparedStatement stmtPretRecolte;
	private Connexion cx;
	
	public TablePlants(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.plants(nomlots, nomplante, dateplantaison, nbplants, nomembre) VALUES " + 
				"(?, ?, ?, ?, ?);");
		stmtExist = cx.getConnection().prepareStatement("SELECT nomplante, nomlots, nomembre FROM jardincollectif.plants WHERE nomlots = ? AND nomplante= ? AND nomembre= ? ");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.plants WHERE nomlots = ? AND nomplante= ? AND nomembre= ? ");
		stmtPlanteCultiver = cx.getConnection().prepareStatement("SELECT nomplante FROM jardincollectif.plants WHERE nomplante = ?;");
		stmtLotCultiver = cx.getConnection().prepareStatement("SELECT nomlots FROM jardincollectif.plants WHERE nomlots = ?;");
		stmtPretRecolte = cx.getConnection().prepareStatement("SELECT ((CURRENT_DATE - dateplantaison)>= p.tempsculture)"+
		" FROM jardincollectif.plants pl JOIN jardincollectif.plante p on pl.nomplante = p.nomplante "+
		"where nomlots =? AND pl.nomplante =? AND nomembre= ?;");
		
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

	public boolean planteEstCultiver(String nomPlante) throws SQLException
	{
		stmtPlanteCultiver.setString(1, nomPlante);
		ResultSet rs = stmtPlanteCultiver.executeQuery();
		boolean estCultiver = rs.next();
		rs.close();
		return estCultiver;
	}
	
	public boolean LotEstEnCulture(String nomLot) throws SQLException
	{
		stmtLotCultiver.setString(1, nomLot);
		ResultSet rs = stmtLotCultiver.executeQuery();
		boolean estEnCulture = rs.next();
		rs.close();
		return estEnCulture;
	}
	
	public boolean RecolteReady(String nomLots, String nomPlante, int noMembre) throws SQLException
	{
		stmtPretRecolte.setString(1,nomLots);
		stmtPretRecolte.setString(2,nomPlante);
		stmtPretRecolte.setInt(3,noMembre);
		ResultSet rs = stmtPretRecolte.executeQuery();
		rs.next();
		boolean ready = rs.getBoolean(1);
		rs.close();
		return ready;
	}
}