package JardinCollectif;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class TupleLot {
	    
	private String m_nomLot;
	private int m_nbMaxMembre;
	
	//@OneToMany(mappedBy = "m_noMembre")
	private List<Integer> demandes;
	
	//@OneToMany(mappedBy = "m_noMembre")
	private List<Integer> assignations;
	
	
	public TupleLot()
    {
    }
	
	public TupleLot(Document d)
	{
		m_nomLot = d.getString("nomLot");
		m_nbMaxMembre = d.getInteger("nbMaxMembre");
		demandes = stringToList(d.getString("demandes"));
		assignations = stringToList(d.getString("assignations"));
	}
	
	public TupleLot(String nomLot, int nbMaxMembre)
    {
		m_nomLot= nomLot;
		m_nbMaxMembre= nbMaxMembre;
		demandes = new ArrayList<Integer>();
		assignations = new ArrayList<Integer>();
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
    	int pos = assignations.indexOf(noMembre);
    	assignations.remove(pos);
    }
    
    public void ajouterDemande(int noMembre)
    {
    	demandes.add(noMembre);
    }
    
    public void retirerDemande(int noMembre)
    {
    	int pos = demandes.indexOf(noMembre);
    	demandes.remove(pos);
    }
    
    public boolean estAssigner(int noMembre)
    {
    	return assignations.contains(noMembre);
    }
    
    public int nbAssignations()
    {
    	return assignations.size();
    }
    
    public boolean existeDemande(int noMembre)
    {
    	return demandes.contains(noMembre);
    }
    public int getNbMembreLot(String nomLot) {
    	return assignations.size();
    }
    
    private String listToString(List<Integer> list)
    {
    	StringBuffer s = new StringBuffer();
    	for(int i : list)
    	{
    		s.append(i + ",");
    	}
    	return s.toString();
    }
    
    private List<Integer> stringToList(String list)
    {
    	List<String> stringList = Arrays.asList(list.split(","));
    	List<Integer> intList = new ArrayList<Integer>();
    	
    	for(String s : stringList)
    	{
    		intList.add(Integer.valueOf(s));
    	}
    	
    	return intList;
    }
    
    public Document toDocument()
    {
    	return new Document().append("nomLot", m_nomLot)
    						 .append("nbMaxMembre", m_nbMaxMembre)
    						 .append("demandes", listToString(demandes))
    						 .append("assignations", listToString(assignations));
    }
}
