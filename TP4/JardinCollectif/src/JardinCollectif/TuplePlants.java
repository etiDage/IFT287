package JardinCollectif;

import java.sql.Date;
import org.bson.Document;


public class TuplePlants {
		
		private String m_nomLot;
		private String m_nomPlante;
		private Date m_datePlantation;
		private int m_nbPlants;
		
		
		public TuplePlants()
	    {
	    }
		
		public TuplePlants(Document d)
		{
			m_nomLot = d.getString("nomLot");
			m_nomPlante = d.getString("nomPlante");
			m_datePlantation = d.getDate("datePlantaison");
			m_nbPlants = d.getInteger("nbPlants");
		}
		
		public TuplePlants(String nomLot, String nomPlante, Date datePlantation, int nbPlants)
	    {
			m_nomLot= nomLot;
			m_nomPlante= nomPlante;
			m_datePlantation= datePlantation;
			m_nbPlants= nbPlants;
	    }
		
		public String getNomLot()
	    {
	        return m_nomLot;
	    }

		public String getNomPlante()
	    {
	        return m_nomPlante;
	    }

		public Date getDatePlantation()
	    {
	        return m_datePlantation;
	    }

		public int getNbPlants()
	    {
	        return m_nbPlants;
	    }
		
		public Document toDocument()
		{
			return new Document().append("nomLot", m_nomLot)
								 .append("nomPlante", m_nomPlante)
								 .append("datePlantaison", m_datePlantation)
								 .append("nbPlants", m_nbPlants);
		}
}
