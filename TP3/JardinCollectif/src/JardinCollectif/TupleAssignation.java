package JardinCollectif;
import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleAssignation {
	
    @Id
    @GeneratedValue
    private long m_id;
    
	private int m_noMembre;
	private String m_nomLot;
	
	public TupleAssignation()
    {
    }
	
	public TupleAssignation(int noMembre,String nomLot)
    {
		m_nomLot= nomLot;
		m_noMembre= noMembre;
    }
	public String getNomLot()
    {
        return m_nomLot;
    }
	public int getNoMembre()
    {
        return m_noMembre;
    }

}
