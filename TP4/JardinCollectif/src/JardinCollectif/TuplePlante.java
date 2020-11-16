package JardinCollectif;

import java.util.Date;
import org.bson.Document;

public class TuplePlante {
	
	private String m_nomPlante;
	private int m_tempsCulture;
		
	public TuplePlante()
    {
		
    }
	
	
	public TuplePlante(Document d)
	{
		m_nomPlante = d.getString("nomPlante");
		m_tempsCulture = d.getInteger("tempsCulture");
	}
	
	public TuplePlante(String nomPlante, int tempCulture)
    {
		m_nomPlante= nomPlante;
		m_tempsCulture= tempCulture;
    }

	public int getTmpCulture()
    {
        return m_tempsCulture;
    }
	public String getNomPlante()
    {
        return m_nomPlante;
    }
	
	public String toString()
	{
		StringBuffer s = new StringBuffer(getNomPlante() + " " + getTmpCulture());
		return s.toString();
	}
	
	public Document toDocument()
	{
		return new Document().append("nomPlante", m_nomPlante)
							 .append("tempsCulture", m_tempsCulture);
	}
}
