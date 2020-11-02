package JardinCollectif;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TuplePlants {
	
    	@Id
    	@GeneratedValue
    	private long m_id;
	
		private String m_nomLot;
		private String m_nomPlante;
		private Date m_datePlantation;
		private int m_nbPlants;
		
		
		public TuplePlants()
	    {
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
}
