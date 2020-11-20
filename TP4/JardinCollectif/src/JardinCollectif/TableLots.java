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
		lotsCollection = cx.getDatabase().getCollection("TableLots");
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
	
	public TupleLot getLot(String nomLot)
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
	
	public boolean seulSurUnLot(TupleMembre membre)
	{
		List<TupleLot> lots = getAllLot();
		for(TupleLot lot : lots)
		{
			if(lot.estAssigner(membre) && lot.nbAssignations() == 1)
			{
				return true;
			}
		}
		return false;		
	}
	
	public void supprimerMembre(TupleMembre membre)
	{
		List<TupleLot> lots = getAllLot();
		for(TupleLot lot : lots)
		{
			if(lot.estAssigner(membre))
			{
				lot.retirer(membre);
			}
			if(lot.existeDemande(membre))
			{
				lot.retirerDemande(membre);
			}
		}
		
	}
	
	public void ajouterDemande(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.ajouterDemande(membre);
		
		lotsCollection.updateOne(eq("nomLot", nomLot), set("demandes", lot.demandesToDocument()));
	}
	
	public void supprimerDemande(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.retirerDemande(membre);
		
		lotsCollection.updateOne(eq("nomLot", nomLot), set("demandes", lot.demandesToDocument()));
	}
	
	public void accepterDemande(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		supprimerDemande(membre, nomLot);
		lot.assigner(membre);
		
		lotsCollection.updateOne(eq("nomLot", nomLot), set("assignations", lot.assignationsToDocument()));
	}
	
	public void retirerAssignation(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		lot.retirer(membre);
		
		lotsCollection.updateOne(eq("nomLot", nomLot), set("assignations", lot.assignationsToDocument()));
	}
	
	public boolean existeDemande(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		return lot.existeDemande(membre);
	}
	
	public boolean estAssigner(TupleMembre membre, String nomLot)
	{
		TupleLot lot = getLot(nomLot);
		return lot.estAssigner(membre);
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


