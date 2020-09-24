package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

public class MainBody
{
    private String bodyName;
    private String bodyID;
    
    private ArrayList<BodySystem> systems;
    private ArrayList<Organ> organs;
    
    
    public MainBody(String bodyName, String bodyID)
    {
        this.setBodyName(bodyName);
        this.setBodyID(bodyID);
        
        systems = new ArrayList<BodySystem>();
        organs = new ArrayList<Organ>();
    }
    
    public void jsonOrgans (JsonGenerator gen) {
    	
    	for(int i = 0 ; i< organs.size(); i++) {
    		gen.writeStartObject();
    		gen.write("name", organs.get(i).getName());
    		gen.write("ID", organs.get(i).getId());
    		gen.write("systemId", organs.get(i).getSystemID());
    		gen.writeEnd();
    	}
    	
    }
    public void jsonBodySystem (JsonGenerator gen) {
    	
    	for(int i = 0 ; i< systems.size(); i++) {
    		gen.writeStartObject();
    		gen.write("name", systems.get(i).getName());
    		gen.write("ID", systems.get(i).getId());
    		gen.write("type", systems.get(i).getType());
    		gen.writeStartArray("Flows");
    			systems.get(i).jsonFlow(gen);
    		gen.writeEnd();
    		gen.writeEnd();
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


    public String getBodyID()
    {
        return bodyID;
    }


    public void setBodyID(String bodyID)
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