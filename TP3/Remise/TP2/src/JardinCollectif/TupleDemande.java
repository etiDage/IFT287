package JardinCollectif;
import java.sql.*;

public class TupleDemande {
	private int noMembre;
	private String nomLot;
	
	public TupleDemande()
    {
    }
	
	public TupleDemande(int noMembre,String nomLot)
    {
        this.setNoMembre(noMembre);
        this.setNomLot(nomLot);
    }
	
	private void setNomLot(String nomLot) {
		this.nomLot= nomLot;
		
	}
	public String getNomLot()
    {
        return nomLot;
    }
	
	private void setNoMembre(int noMembre) {
		this.noMembre= noMembre;
		
	}
	public int getNoMembre()
    {
        return noMembre;
    }

}
