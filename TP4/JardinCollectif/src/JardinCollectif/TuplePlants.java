package JardinCollectif;

import java.sql.Date;
import org.bson.Document;


public class TuplePlants {
		
		private TupleLot m_lot;
		private TuplePlante m_plante;
		private Date m_datePlantation;
		private int m_nbPlants;
		
		
		public TuplePlants()
	    {
	    }
		
		public TuplePlants(Document d)
		{
			m_lot = new TupleLot(d.getString("nomLot"), d.getInteger("nbMaxMembre"));
			m_plante = new TuplePlante(d.getString("nomPlante"), d.getInteger("tempsCulture"));
			m_datePlantation = new Date(d.getDate("datePlantaison").getTime());
			m_nbPlants = d.getInteger("nbPlants");
		}
		
		public TuplePlants(TupleLot lot, TuplePlante plante, Date datePlantation, int nbPlants)
	    {
			m_lot= lot;
			m_plante= plante;
			m_datePlantation= datePlantation;
			m_nbPlants= nbPlants;
	    }
		
		public String getNomLot()
	    {
	        return m_lot.getNomLot();
	    }

		public String getNomPlante()
	    {
	        return m_plante.getNomPlante();
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
			return new Document().append("nomLot", m_lot.getNomLot())
								 .append("nbMaxMembre", m_lot.getNbMaxMembre())
								 .append("nomPlante", m_plante.getNomPlante())
								 .append("tempsCulture", m_plante.getTmpCulture())
								 .append("datePlantaison", m_datePlantation)
								 .append("nbPlants", m_nbPlants);
		}
}
