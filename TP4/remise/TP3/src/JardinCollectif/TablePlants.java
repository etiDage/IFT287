package JardinCollectif;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TablePlants {
	
//	private PreparedStatement stmtPlanteCultiver;
//	private PreparedStatement stmtLotCultiver;
//	private PreparedStatement stmtPretRecolte;
//	private PreparedStatement stmtExistLotPlantes;
	
	private TypedQuery<TuplePlants> stmtExist;
	private TypedQuery<TuplePlants> stmtPlanteCultiver;
	private TypedQuery<TuplePlants> stmtLotCultiver;
	private TypedQuery<TuplePlants> stmtSelectAll;
	private Connexion cx;
	
	public TablePlants(Connexion cx)
	{
		this.cx = cx;
//		stmtPlanteCultiver = cx.getConnection().prepareStatement("SELECT plant.nomPlante FROM TuplePlant plant WHERE plant.nomPlante = :");
//		stmtLotCultiver = cx.getConnection().prepareStatement("SELECT nomlots FROM jardincollectif.plants WHERE nomlots = ?;");
//		stmtPretRecolte = cx.getConnection().prepareStatement("SELECT ((CURRENT_DATE - dateplantaison)>= p.tempsculture)"+
//		" FROM jardincollectif.plants pl JOIN jardincollectif.plante p on pl.nomplante = p.nomplante "+
//		"where nomlots =? AND pl.nomplante =?;");
//		stmtExistLotPlantes = cx.getConnection().prepareStatement("SELECT * FROM jardincollectif.plants WHERE nomlots = ? AND nomplante= ?");
		
		stmtExist = cx.getConnection().createQuery("select plants from TuplePlants plants where plants.m_nomLot = :nomLot AND plants.m_nomPlante = :nomPlante", TuplePlants.class);
		stmtPlanteCultiver = cx.getConnection().createQuery("select plants from TuplePlants plants where plants.m_nomPlante = :nomPlante", TuplePlants.class);
		stmtLotCultiver = cx.getConnection().createQuery("select plants from TuplePlants plants where plants.m_nomLot = :nomLot", TuplePlants.class);
		stmtSelectAll = cx.getConnection().createQuery("select plants from TuplePlants plants", TuplePlants.class);
	}
	
	public Connexion getConnexion()
	{
		return cx;
	}
	
	public void ajouterPlants(String nomLot, String nomPlante, Date datePlantaison ,int nbPlants)
	{
		TuplePlants plants = new TuplePlants(nomLot, nomPlante, datePlantaison, nbPlants);
		cx.getConnection().persist(plants);		
	}
	
	public void supprimer(String nomLot, String nomPlante)
	{
		if(existLotPlante(nomLot, nomPlante))
		{
			TuplePlants plants = getPlants(nomLot, nomPlante);
			cx.getConnection().remove(plants);
		}
	}

	public boolean planteEstCultiver(String nomPlante)
	{
		stmtPlanteCultiver.setParameter("nomPlante", nomPlante);
		return !stmtPlanteCultiver.getResultList().isEmpty();
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
