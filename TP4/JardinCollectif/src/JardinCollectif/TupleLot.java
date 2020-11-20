package JardinCollectif;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class TupleLot {
	    
	private String m_nomLot;
	private int m_nbMaxMembre;
	
	private List<TupleMembre> demandes;
	
	private List<TupleMembre> assignations;
	
	
	public TupleLot()
    {
    }
	
	@SuppressWarnings("unchecked")
	public TupleLot(Document d)
	{
		m_nomLot = d.getString("nomLot");
		m_nbMaxMembre = d.getInteger("nbMaxMembre");
		System.out.println(d.get("demandes"));
		demandes = documentToList((List<Document>)d.get("demandes"));
		assignations = documentToList((List<Document>)d.get("assignations"));
	}
	
	public TupleLot(String nomLot, int nbMaxMembre)
    {
		m_nomLot= nomLot;
		m_nbMaxMembre= nbMaxMembre;
		demandes = new ArrayList<TupleMembre>();
		assignations = new ArrayList<TupleMembre>();
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
    
    public void assigner(TupleMembre membre)
    {
    	assignations.add(membre);
    }
    
    public void retirer(TupleMembre membre)
    {
    	assignations.remove(membre);
    }
    
    public void ajouterDemande(TupleMembre membre)
    {
    	demandes.add(membre);
    }
    
    public void retirerDemande(TupleMembre membre)
    {
    	demandes.remove(membre);
    }
    
    public boolean estAssigner(TupleMembre membre)
    {
    	return assignations.contains(membre);
    }
    
    public int nbAssignations()
    {
    	return assignations.size();
    }
    
    public boolean existeDemande(TupleMembre membre)
    {
    	return demandes.contains(membre);
    }
    
    public int getNbMembreLot(String nomLot) {
    	return assignations.size();
    }
    
    private List<Document> listToDocument(List<TupleMembre> list)
    {
    	List<Document> l = new ArrayList<Document>();
    	for(TupleMembre m : list)
    	{
    		l.add(m.toDocument());
    	}
    	return l;
   	
    }
    
    private List<TupleMembre> documentToList(List<Document> list)
    {
    	List<TupleMembre> membres = new ArrayList<TupleMembre>();
    	
    	for(Document d : list)
    	{
    		TupleMembre membre = new TupleMembre(d);
    		membres.add(membre);
    	}
    	
    	return membres;
  
    }
    
    public List<Document> demandesToDocument()
    {
    	return listToDocument(demandes);
    }
     
    public List<Document> assignationsToDocument()
    {
    	return listToDocument(demandes);
    }

    
    public Document toDocument()
    {
    	return new Document().append("nomLot", m_nomLot)
    						 .append("nbMaxMembre", m_nbMaxMembre)
    						 .append("demandes", demandesToDocument())
    						 .append("assignations", assignationsToDocument());
		
    }
}
