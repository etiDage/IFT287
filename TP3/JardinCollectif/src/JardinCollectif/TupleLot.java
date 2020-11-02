package JardinCollectif;



import java.util.List;

import javax.persistence.*;

@Entity
public class TupleLot {
	
    @Id
    @GeneratedValue
    private long m_id;
    
	private String m_nomLot;
	private int m_nbMaxMembre;
	
	//@OneToMany(mappedBy = "m_noMembre")
	private List<Integer> demandes;
	
	//@OneToMany(mappedBy = "m_noMembre")
	private List<Integer> assignations;
	
	
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
    
    public List<Integer> getDemandes()
    {
    	return demandes;
    }
    
    public List<Integer> getAssignations()
    {
    	return assignations;
    }
    
    public void assigner(int noMembre)
    {
    	assignations.add(noMembre);
    }
    
    public void retirer(int noMembre)
    {
    	assignations.remove(noMembre);
    }
    
    public void ajouterDemande(int noMembre)
    {
    	demandes.add(noMembre);
    }
    
    public void retirerDemande(int noMembre)
    {
    	demandes.remove(noMembre);
    }
    
    public boolean estAssigner(int noMembre)
    {
    	return assignations.contains(noMembre);
    }
    
    public int nbAssignations()
    {
    	return assignations.size();
    }
}
