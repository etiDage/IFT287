package JardinCollectif;

import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleLot {
	
    @Id
    @GeneratedValue
    private long m_id;
    
	private String m_nomLot;
	private int m_nbMaxMembre;
	
	public TupleLot()
    {
    }
	
	public TupleLot(String nomLot, int nbMaxMembre)
    {
		m_nomLot= nomLot;
		m_nbMaxMembre= nbMaxMembre;
    }


    public int getNbMaxMembre()
    {
        return m_nbMaxMembre;
    }
    public String getNomLot()
    {
        return m_nomLot;
    }
}
