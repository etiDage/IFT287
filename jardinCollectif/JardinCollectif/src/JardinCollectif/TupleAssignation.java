package JardinCollectif;
import java.sql.*;

public class TupleAssignation {
	private int noMembre;
	private String nomLot;
	
	public TupleAssignation()
    {
    }
	
	public TupleAssignation(int noMembre,String nomLot)
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
