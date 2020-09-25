package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
		gen.write("name", bodyName);
		gen.write("id", bodyID);
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
    public void xmlMainBody(Document document, Element n) {
        n.setAttribute("bodyName",bodyName);
        n.setAttribute("bodyID",Integer.toString(bodyID));
        
        Element systemsElement = document.createElement("Systems");
        n.appendChild(systemsElement);
        for(BodySystem bodySystem : systems)
		{
        	Element sys =document.createElement("System");
            systemsElement.appendChild(sys);
            bodySystem.xmlSystem(document, sys);
		}
        
        Element organsElement = document.createElement("Organs");
        n.appendChild(organsElement);
        for(Organ organ : organs)
		{
        	Element org =document.createElement("Organ");
        	organsElement.appendChild(org);
            organ.xmlOrgan(document, org);
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