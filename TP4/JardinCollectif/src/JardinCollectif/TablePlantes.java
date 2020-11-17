package JardinCollectif;

import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

import org.bson.Document;

import com.mongodb.client.MongoCollection;


public class TablePlantes {
	private MongoCollection<Document> plantesCollection;
	private Connexion cx;

	public TablePlantes(Connexion cx)
	{
		this.cx = cx;
		plantesCollection = cx.getDatabase().getCollection("plantes");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomPlante)
	{
		return plantesCollection.find(eq("nomPlante", nomPlante)).first() != null;
	}
	
	public void ajouterPlante(String nomPlante, int tempsCulture)
	{
		TuplePlante plante = new TuplePlante(nomPlante, tempsCulture);
		plantesCollection.insertOne(plante.toDocument());
	}
	
	public void supprimer(String nomPlante)
	{
		plantesCollection.deleteOne(eq("nomPlante", nomPlante));
	}
	
	private TuplePlante getPlante(String nomPlante)
	{
		TuplePlante plante = new TuplePlante(plantesCollection.find(eq("nomPlante", nomPlante)).first());
		return plante;
	}
	
	public int getTempsDeCulture(String nomPlante)
	{
		TuplePlante plantes = getPlante(nomPlante);
		return plantes.getTmpCulture();
	}
	
	public List<TuplePlante> getAllPlantes()
	{
		
	}

}
