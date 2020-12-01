package JardinCollectif;
import java.sql.*;

public class TupleAssignation {
	private String userId;
	private String nomLot;
	
	public TupleAssignation()
    {
    }
	
	public TupleAssignation(String userId,String nomLot)
    {
        this.setNoMembre(userId);
        this.setNomLot(nomLot);
    }
	
	private void setNomLot(String nomLot) {
		this.nomLot= nomLot;
		
	}
	public String getNomLot()
    {
        return nomLot;
    }
	
	private void setNoMembre(String userId) {
		this.userId= userId;
		
	}
	public String getNoMembre()
    {
        return userId;
    }

}
