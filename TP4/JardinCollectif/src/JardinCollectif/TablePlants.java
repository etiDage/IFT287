package JardinCollectif;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TablePlants {
	
	private MongoCollection<Document> plantsCollection;
	private Connexion cx;
	
	public TablePlants(Connexion cx)
	{
		this.cx = cx;
		plantsCollection = cx.getDatabase().getCollection("Plants");
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public void ajouterPlants(String nomLot, String nomPlante, Date datePlantaison ,int nbPlants)
	{
		TuplePlants plant = new TuplePlants(nomLot, nomPlante, datePlantaison, nbPlants);
		plantsCollection.insertOne(plant.toDocument());
	}
	
	public void supprimer(String nomLot, String nomPlante)
	{
		plantsCollection.deleteOne(combine(eq("nomLot", nomLot), eq("nomPlante", nomPlante)));
	}

	public boolean planteEstCultiver(String nomPlante)
	{
		return plantsCollection.find(eq("nomPlante")).first() != null;
	}
	
	public boolean LotEstEnCulture(String nomLot)
	{
		stmtLotCultiver.setParameter("nomLot", nomLot);
		return !stmtLotCultiver.getResultList().isEmpty();
	}
	
	
	public int getNbJourEnCulture(String nomLot, String nomPlante) throws ParseException
	{
		TuplePlants plants = getPlants(nomLot, nomPlante);
		
		long millis = System.currentTimeMillis();
		Date currentDate = new Date(millis);	// Get the current date in YYYY-MM-DD format
		
		
		String strCurDate = currentDate.toString();		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date date = df.parse(strCurDate);
		
		java.util.Date datePlantaison = df.parse(plants.getDatePlantation().toString());
		
		final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
		
		int nbJour = (int) ((date.getTime() - datePlantaison.getTime()) / DAY_IN_MILLIS);
		return nbJour;	
	}
	
	public boolean existLotPlante(String nomLot, String nomPlante)
	{
		stmtExist.setParameter("nomLot", nomLot);
		stmtExist.setParameter("nomPlante", nomPlante);
		return !stmtExist.getResultList().isEmpty();
	}
	
	public TuplePlants getPlants(String nomLot, String nomPlante)
	{
		stmtExist.setParameter("nomLot", nomLot);
		stmtExist.setParameter("nomPlante", nomPlante);
		List<TuplePlants> plants = stmtExist.getResultList();
		if(!plants.isEmpty())
		{
			return plants.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public int getNbPlantsEnCulture(String nomPlante) // Retourne le nombre de plants d'une espece parmis tout les lots ( Pour gestion interrogation)
	{
		List<TuplePlants> plants = stmtSelectAll.getResultList();
		int planteCpt = 0;
		for(TuplePlants plant : plants)
		{
			if(nomPlante.equals(plant.getNomPlante()))
			{
				planteCpt += plant.getNbPlants();
			}
		}
		return planteCpt;
	}
	
	
	public List<TuplePlants> getAllPlants() // Pour affichage dans gestion Interrogation
	{
		return stmtSelectAll.getResultList();
	}
}
