package JardinCollectif;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		stmtLots = cx.getConnection().prepareStatement("SELECT lots.nomlots, m1.nomembre " + 
				"FROM  jardincollectif.lots lots LEFT JOIN jardincollectif.assignation assign ON lots.nomlots = assign.nomlots LEFT JOIN jardincollectif.membres m1 ON assign.nomembre = m1.nomembre " + 
				"ORDER BY assign.nomlots;");
		stmtPlantsParLots = cx.getConnection().prepareStatement("SELECT l1.nomlots, COALESCE(p.nomplante, 'aucune'), p.dateplantaison, (p.dateplantaison + pe.tempsculture)  AS daterecolte " + 
				"FROM jardincollectif.lots l1 LEFT JOIN jardincollectif.plants p ON l1.nomlots = p.nomlots " + 
				"    LEFT JOIN jardincollectif.plante pe ON p.nomplante = pe.nomplante ");
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
	
	public List<String> listerPlantes() throws SQLException
	{
		ResultSet rs = stmtPlante.executeQuery();
		
		List<String> list = new ArrayList<String>();
		
		while(rs.next())
		{
			list.add(rs.getString(1));
			list.add(String.valueOf(rs.getInt(2)));
			list.add(String.valueOf(rs.getInt(3)) + " plants");
		}
		cx.commit();
		
		return list;
	}
	
	public List<String> listerLots() throws SQLException
	{
		ResultSet rs = stmtLots.executeQuery();
		
		List<String> list = new ArrayList<String>();
				
		while(rs.next())
		{
			String nomembre;
			if(rs.getInt(2) == -1)
			{
				nomembre = "";
			}
			else
			{
				nomembre = String.valueOf(rs.getInt(2));
			}
			list.add(rs.getString(1));
			list.add(nomembre);
		}
		
		cx.commit();
		return list;
	}
	
	public List<String> listerPlantsLot() throws SQLException
	{
		ResultSet rs = stmtPlantsParLots.executeQuery();
		
		List<String> list = new ArrayList<String>();
		while(rs.next())
		{
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			System.out.println(rs.getString(2));
			if(rs.getString(2).equals("aucune"))
			{
				list.add("aucune");
				list.add("aucune");
			}
			else
			{
				list.add(rs.getDate(3).toString());
				list.add(rs.getDate(4).toString());				
			}
		}
		
		cx.commit();
		return list;
	}
}
