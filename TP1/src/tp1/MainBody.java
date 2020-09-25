package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

public class MainBody
{
    private String bodyName;
    private int bodyID;
    
    private ArrayList<BodySystem> systems;
    private ArrayList<Organ> organs;
    
    
    public MainBody(String bodyName, int bodyID)
    {
        this.setBodyName(bodyName);
        this.setBodyID(bodyID);
        
        systems = new ArrayList<BodySystem>();
        organs = new ArrayList<Organ>();
    }
        
    public void jsonMainBody (JsonGenerator gen)
    {
		gen.write("Name", bodyName);
		gen.write("ID", bodyID);
		gen.writeStartArray("Systems");
		for(BodySystem bodySystem : systems)
		{
			bodySystem.jsonSystem(gen);
		}
		gen.writeEnd();
		gen.writeStartArray("Organs");
		for(Organ organ : organs)
		{
			organ.jsonOrgan(gen);
		}
		gen.writeEnd();
    }
    public void xmlMainBody(Document document, Node n) {
        n.setAttribute("Name",bodyName);
        n.setAttribute("ID",bodyID);
        for(Organ organ : organs)
		{
        	Node org =document.createElement("Organ");
            n.appendChild(org);
            organ.xmlOrgan(document, org);
		}
        for(BodySystem bodySystem : systems)
		{
        	Node sys =document.createElement("System");
            n.appendChild(sys);
            bodySystem.xmlSystem(document, sys);
		}
        
        
        
    	
    }

    public String getBodyName()
    {
        return bodyName;
    }


    public void setBodyName(String bodyName)
    {
        this.bodyName = bodyName;
    }


    public int getBodyID()
    {
        return bodyID;
    }


    public void setBodyID(int bodyID)
    {
        this.bodyID = bodyID;
    }
    
    public ArrayList<BodySystem> getSystems()
    {
        return systems;
    }
    
    public void addSystem(BodySystem system)
    {
        systems.add(system);
    }
    
    public ArrayList<Organ> getOrgans()
    {
        return organs;
    }
    
    public void addOrgan (Organ organ)
    {
        organs.add(organ);
    }
}