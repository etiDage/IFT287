package JardinCollectif;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TablePlants {
	
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtPlanteCultiver;
	private PreparedStatement stmtLotCultiver;
	private PreparedStatement stmtPretRecolte;
	private PreparedStatement stmtExistLotPlantes;
	private Connexion cx;
	
	public TablePlants(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.plants(nomlots, nomplante, dateplantaison, nbplants) VALUES " + 
				"(?, ?, ?, ?);");
		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.plants WHERE nomlots = ? AND nomplante= ?");
		stmtPlanteCultiver = cx.getConnection().prepareStatement("SELECT nomplante FROM jardincollectif.plants WHERE nomplante = ?;");
		stmtLotCultiver = cx.getConnection().prepareStatement("SELECT nomlots FROM jardincollectif.plants WHERE nomlots = ?;");
		stmtPretRecolte = cx.getConnection().prepareStatement("SELECT ((CURRENT_DATE - dateplantaison)>= p.tempsculture)"+
		" FROM jardincollectif.plants pl JOIN jardincollectif.plante p on pl.nomplante = p.nomplante "+
		"where nomlots =? AND pl.nomplante =?;");
		stmtExistLotPlantes = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.plants WHERE nomlots = ? AND nomplante= ?");

	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public void ajouterPlants(String nomLots, String nomPlante, Date datePlantaison ,int nbPlants) throws SQLException
	{
		stmtInsert.setString(1, nomLots);
		stmtInsert.setString(2, nomPlante);
		stmtInsert.setDate(3, datePlantaison);
		stmtInsert.setInt(4, nbPlants);
		stmtInsert.executeUpdate();
	}
	
	public void supprimer(String nomLots, String nomPlante) throws SQLException
	{
		stmtDelete.setString(1, nomLots);
		stmtDelete.setString(2, nomPlante);
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
	
	public boolean RecolteReady(String nomLots, String nomPlante) throws SQLException
	{
		stmtPretRecolte.setString(1,nomLots);
		stmtPretRecolte.setString(2,nomPlante);
		ResultSet rs = stmtPretRecolte.executeQuery();
		rs.next();
		boolean ready = rs.getBoolean(1);
		rs.close();
		return ready;
	}
	
	public boolean existLotPlante(String nomLot, String nomPlante) throws SQLException
	{
		stmtExistLotPlantes.setString(1, nomLot);
		stmtExistLotPlantes.setString(2, nomPlante);
		ResultSet rs = stmtExistLotPlantes.executeQuery();
		boolean existe = rs.next();
		rs.close();
		return existe;
	}
}
