package JardinCollectif;

import java.sql.*;

public class TuplePlants {
		private String nomLot;
		private String nomPlante;
		private Date datePlantation;
		private int noMembre;
		private int nbPlants;
		
		public TuplePlants()
	    {
	    }
		
		public TuplePlants(String nomLot, String nomPlante, Date datePlantation, int noMembre, int nbPlants)
	    {
	        this.setNomLot(nomLot);
	        this.setNomPlante(nomPlante);
	        this.setDatePlantation(datePlantation);
	        this.setNoMembre(noMembre);
	        this.setNbPlants(nbPlants);
	    }

		private void setNomLot(String nomLot) {
			this.nomLot= nomLot;
			
		}
		public String getNomLot()
	    {
	        return nomLot;
	    }

		private void setNomPlante(String nomPlante) {
			this.nomPlante= nomPlante;
			
		}
		public String getNomPlante()
	    {
	        return nomPlante;
	    }

		private void setDatePlantation(Date datePlantation) {
			this.datePlantation= datePlantation;
			
		}
		public Date getDatePlantation()
	    {
	        return datePlantation;
	    }

		private void setNoMembre(int noMembre) {
			this.noMembre= noMembre;
			
		}
		public int getNoMembre()
	    {
	        return noMembre;
	    }

		private void setNbPlants(int nbPlants) {
			this.nbPlants= nbPlants;
			
		}
		public int getNbPlants()
	    {
	        return nbPlants;
	    }
}
