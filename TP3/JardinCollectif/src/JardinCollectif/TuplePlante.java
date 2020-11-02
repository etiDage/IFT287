package JardinCollectif;
import java.sql.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TuplePlante {
	
	@Id
    @GeneratedValue
    private long m_id;
	
	private String m_nomPlante;
	private int m_tempCulture;
	
	public TuplePlante()
    {
    }
	
	public TuplePlante(String nomPlante, int tempCulture)
    {
		m_nomPlante= nomPlante;
		m_tempCulture= tempCulture;
    }

	public int getTmpCulture()
    {
        return m_tempCulture;
    }
	public String getNomPlante()
    {
        return m_nomPlante;
    }

}
