package JardinCollectif;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TableLots {

	private MongoCollection<Document> lotsCollection;
	private Connexion cx;
	
	public TableLots(Connexion cx)
	{
		this.cx = cx;
		lotsCollection = cx.getDatabase().getCollection("Lots");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public boolean exist(String nomLot)
	{
		return lotsCollection.find(eq("nomLot", nomLot)).first() != null;
	}
	
	public void ajouterLot(String nomLot, int nbMaxMembre)
	{
		TupleLot lot = new TupleLot(nomLot, nbMaxMembre);
		lotsCollection.insertOne(lot.toDocument());
	}
	
	public void supprimer(String nomLot)
	{
		lotsCollection.deleteOne(eq("nomLot", nomLot));
	}
	
	public int getNbMaxMembre(String nomLot)
	{
		Document d = lotsCollection.find(eq("nomLot", nomLot)).first();
		if(d != null)
		{
			TupleLot lot = new TupleLot(d);
			return lot.getNbMaxMembre();
		}
		else
		{
			return -1;
		}
	}
	
	private TupleLot getLot(String nomLot)
	{
		Document d = lotsCollection.find(eq("nomLot", nomLot)).first();
		if(d != null)
		{
			return new TupleLot(d);
		}
		else
		{
			return null;
		}
	}
	
	public boolean seulSurUnLot(int noMembre)
	{
		List<TupleLot> lots = getAllLot();
		for(TupleLot lot : lots)
		{
			if(lot.estAssigner(noMembre) && lot.nbAssignations() == 1)
			{
				return true;
			}
		}
		return false;		
	}
	
	public void supprimerMembre(int noMembre)
	{
		List<TupleLot> lots = getAllLot();
		for(TupleLot lot : lots)
		{
			if(lot.estAssigner(noMembre))
			{
				lot.retirer(noMembre);
			}
			if(lot.existeDemande(noMembre))
			{
				lot.retirerDemande(noMembre);
			}
		}
	}
	
	public void ajouterDemande(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.ajouterDemande(noMembre);
	}
	
	public void supprimerDemande(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.retirerDemande(noMembre);
	}
	
	public void accepterDemande(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.retirerDemande(noMembre);
		lot.assigner(noMembre);
	}
	
	public void retirerAssignation(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.retirer(noMembre);
	}
	
	public boolean existeDemande(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		return lot.existeDemande(noMembre);
	}
	
	public boolean estAssigner(int noMembre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		return lot.estAssigner(noMembre);
	}
	
    public int getNbMembreLot(String nomLot) 
    {
    	TupleLot lot = getLot(nomLot);
    	return lot.getNbMembreLot(nomLot);
    }
    
    public List<TupleLot> getAllLot()	// Pour gestionInterrogation
    {
    	List<TupleLot> lotsListe = new ArrayList<TupleLot>();
        MongoCursor<Document> lots = lotsCollection.find().iterator();
        try
        {
            while (lots.hasNext())
            {
            	TupleLot lot = new TupleLot(lots.next());
            	lotsListe.add(lot);
            }
        }
        finally
        {
            lots.close();
        }
        return lotsListe;

    }
}


