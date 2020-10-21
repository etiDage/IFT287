package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionInterrogation {
	private PreparedStatement stmtMembres; //Pour afficher la liste de membres
	private PreparedStatement stmtPlante; // Pour afficher la liste de plantes
	private PreparedStatement stmtLots;		// Pour afficher la liste de lots
	private PreparedStatement stmtPlantsParLots; // Pour afficher les plants d'un lot
	private Connexion cx;
	
	public GestionInterrogation(Connexion cx) throws SQLException
	{
		this.cx = cx;
		stmtMembres = cx.getConnection().prepareStatement("SELECT noMembre, prenom, nom FROM jardincollectif.membres");
		stmtPlante = cx.getConnection().prepareStatement("SELECT p1.nomplante, p1.tempsculture, COALESCE(p.nbplants, 0) AS NombreDePlants " + 
				"FROM jardincollectif.plante p1 LEFT JOIN jardincollectif.plants p on p1.nomplante = p.nomplante;");
		stmtLots = cx.getConnection().prepareStatement("SELECT assign.nomlots, m1.nomembre, m1.prenom, m1.nom " + 
				"FROM jardincollectif.assignation assign LEFT JOIN jardincollectif.membres m1 ON assign.nomembre = m1.nomembre " + 
				"ORDER BY assign.nomlots;");
		stmtPlantsParLots = cx.getConnection().prepareStatement("SELECT l1.nomlots, p.nomplante, p.dateplantaison, (p.dateplantaison + pe.tempsculture) AS daterecolte " + 
				"FROM jardincollectif.lots l1 LEFT JOIN jardincollectif.plants p ON l1.nomlots = p.nomlots " + 
				"    LEFT JOIN jardincollectif.plante pe ON p.nomplante = pe.nomplante " + 
				"WHERE l1.nomlots = ?");
	}
	
	public void afficherMembres() throws SQLException
	{
		ResultSet rs = stmtMembres.executeQuery();
		
		System.out.println("\nnoMembre prenom nom");
		while(rs.next())
		{
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}
		
		cx.commit();
	}
	
	public void afficherPlantes() throws SQLException
	{
		ResultSet rs = stmtPlante.executeQuery();
		
		System.out.println("\nNomPlante TempsCulture NombreDePlants");
		while(rs.next())
		{
			System.out.println(rs.getString(1) + " " + rs.getInt(2) + " jours "+ rs.getInt(3) + " plants");
		}
		
		cx.commit();
	}
	
	public void afficherLots() throws SQLException
	{
		ResultSet rs = stmtLots.executeQuery();
		
		System.out.println("\nNomLots noMembre prenom nom");
		while(rs.next())
		{
			System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
		
		cx.commit();
	}
	
	public void afficherPlantsLot(String nomLot) throws SQLException
	{
		stmtPlantsParLots.setString(1, nomLot);
		ResultSet rs = stmtPlantsParLots.executeQuery();
		
		System.out.println("\nNomLots NomPlante DatePlantaison DateRecolte");
		while(rs.next())
		{
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDate(3) + " " + rs.getDate(4));
		}
		
		cx.commit();

	}
}
