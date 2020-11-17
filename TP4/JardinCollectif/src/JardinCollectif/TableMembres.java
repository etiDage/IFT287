package JardinCollectif;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TableMembres {
	
	private MongoCollection<Document> membresCollection;
	private Connexion cx;
	
	
	public TableMembres(Connexion cx)
	{
		this.cx = cx;
		membresCollection = cx.getDatabase().getCollection("Membres");
//		stmtInsert = cx.getConnection().prepareStatement("INSERT INTO jardincollectif.membres(nomembre, prenom, nom, motdepasse, admin) VALUES " + 
//				"(?, ?, ?, ?, TRUE);");
//		stmtExist = cx.getConnection().prepareStatement("SELECT nomembre FROM jardincollectif.membres WHERE nomembre = ?");
//		stmtDelete = cx.getConnection().prepareStatement("DELETE FROM jardincollectif.membres WHERE nomembre = ?");
//		stmtSetAdmin = cx.getConnection().prepareStatement("UPDATE jardincollectif.membres SET admin = TRUE WHERE nomembre = ?");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}

	public boolean exist(int noMembre)
	{
        return membresCollection.find(eq("noMembre", noMembre)).first() != null;
	}
	
	public void inscrire(int noMembre, String prenom, String nom, String motdepasse) 
	{
		TupleMembre membre = new TupleMembre(noMembre, prenom, nom, motdepasse, false);
		membresCollection.insertOne(membre.toDocument());
	}
	
	public void supprimer(int noMembre)
	{
		membresCollection.deleteOne(eq("noMembre", noMembre));
	}
	
	public void setAdmin(int noMembre)
	{
		membresCollection.updateOne(eq("noMembre", noMembre), set("admin", true));
	}
	
	private TupleMembre getMembre(int noMembre)
	{
		Document d = membresCollection.find(eq("noMembre", noMembre)).first();
		if(d != null)
		{
			return new TupleMembre(d);
		}
		else
		{
			return null;
		}		
	}
	
	public List<TupleMembre> getAllMembres()
	{
		List<TupleMembre> membresListe = new ArrayList<TupleMembre>();
        MongoCursor<Document> membres = membresCollection.find().iterator();
        try
        {
            while (membres.hasNext())
            {
            	TupleMembre membre = new TupleMembre(membres.next());
            	membresListe.add(membre);
            }
        }
        finally
        {
            membres.close();
        }
        return membresListe;
	}
}
