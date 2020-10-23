package JardinCollectif;

import java.sql.*;


public class TupleLot {

	private String nomLot;
	private int nbMaxMembre;
	
	public TupleLot()
    {
    }
	
	public TupleLot(String nomLot, int nbMaxMembre)
    {
        this.setNomLot(nomLot);
        this.setNbMaxMembre(nbMaxMembre);
    }

	private void setNbMaxMembre(int nbMaxMembre) {
		this.nbMaxMembre = nbMaxMembre;
		
	}
    public int getNbMaxMembre()
    {
        return nbMaxMembre;
    }

	private void setNomLot(String nomLot) {
		this.nomLot = nomLot;
		
	}
    public String getNomLot()
    {
        return nomLot;
    }
}
