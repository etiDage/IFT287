package JardinCollectif;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.*;

@Entity
public class TupleLot {
	
    @Id
    @GeneratedValue
    private long m_id;
    
	private String m_nomLot;
	private int m_nbMaxMembre;
	
	@OneToMany(mappedBy = "m_noMembre")
	private List<TupleMembre> demandes;
	
	@OneToMany(mappedBy = "m_noMembre")
	private List<TupleMembre> assignations;
	
	
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
    
    public List<TupleMembre> getDemandes()
    {
    	return demandes;
    }
    
    public List<TupleMembre> getAssignations()
    {
    	return assignations;
    }
}
