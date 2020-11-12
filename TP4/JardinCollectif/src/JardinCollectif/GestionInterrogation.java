package JardinCollectif;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

public class GestionInterrogation {
	private TableMembres tableMembres;
	private TableLots tableLots;
	private TablePlantes tablePlantes;
	private TablePlants tablePlants;
	private Connexion cx;
	
	public GestionInterrogation(Connexion cx, TableMembres tableMembres, TableLots tableLots, TablePlantes tablePlantes, TablePlants tablePlants) throws IFT287Exception
	{
		this.cx = cx;
        if (tableLots.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TableLots et de TableMembres n'utilisent pas la même connexion au serveur");
        if (tablePlants.getConnexion() != tableMembres.getConnexion())
            throw new IFT287Exception("Les instances de TablePlants et de TableMembres n'utilisent pas la même connexion au serveur");
        if (tablePlantes.getConnexion() != tablePlants.getConnexion())
            throw new IFT287Exception("Les instances de TablePlantes et de TablePlants n'utilisent pas la même connexion au serveur");
		
        this.tableMembres = tableMembres;
        this.tableLots = tableLots;
        this.tablePlantes = tablePlantes;
        this.tablePlants = tablePlants;
	}
	
	public void afficherMembres() // Affiche la liste de membres
	{
		cx.demarreTransaction();
		
		List<TupleMembre> membres = tableMembres.getAllMembres();
		
		System.out.println("\nnoMembre prenom nom");
		
		for(TupleMembre membre : membres)
		{
			System.out.println(membre.getNoMembre() + " " + membre.getPrenom() + " " + membre.getNom());
		}
		
		cx.commit();
	}
	
	public void afficherPlantes() // Affiche la liste de plantes avec le nombre de plants presentement cultiver
	{
		cx.demarreTransaction();
		
		List<TuplePlante> plantes = tablePlantes.getAllPlantes();
		
		System.out.println("\nNomPlante TempsCulture NombreDePlants");
		
		for(TuplePlante plante : plantes)
		{
			System.out.println(plante.getNomPlante() + " " + plante.getTmpCulture() + " jours "+ tablePlants.getNbPlantsEnCulture(plante.getNomPlante()) + " plants");
		}
		
		cx.commit();
	}
	
	public void afficherLots()
	{
		cx.demarreTransaction();
		
		List<TupleLot> lots = tableLots.getAllLot();
		
		System.out.println("\nNomLots noMembre");
		for(TupleLot lot : lots)
		{
			System.out.print(lot.getNomLot());
			if(lot.getAssignations() != null)
			{
				for(Integer noMembre : lot.getAssignations())
				{
					System.out.print(" " + noMembre);
				}				
			}
			System.out.print("\n");
		}
		
		cx.commit();
	}
	
	public void afficherPlantsLot(String nomLot) throws ParseException
	{
		cx.demarreTransaction();
		
		List<TuplePlants> plants = tablePlants.getAllPlants();
		
		System.out.println("\nNomLots NomPlante DatePlantaison DateRecolte");
		for(TuplePlants plant : plants)
		{
			if(nomLot.equals(plant.getNomLot()))
			{
				System.out.println(plant.getNomLot() + " " + plant.getNomPlante() + " " + plant.getDatePlantation() + " " + getDateRecolte(plant.getDatePlantation().toString(), tablePlantes.getTempsDeCulture(plant.getNomPlante())));
			}
		}
		
		cx.commit();

	}
	
	
	public String getDateRecolte(String datePlantaison, int nbJour) throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = df.parse(datePlantaison);		// Converti la string en date
		java.util.Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.DAY_OF_MONTH, nbJour); 
		return df.format(cal.getTime());
	}
	
}
