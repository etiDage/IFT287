package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

public class BodySystem
{
    private String name;
    private String id;
    private int type;
    
    private ArrayList<Flow> flows;
    
    public BodySystem(String name, String id, int type)
    {
        this.name = name;
        this.id = id;
        this.type = type;
        
        flows = new ArrayList<Flow>();
    }
    
    public void jsonFlow (JsonGenerator gen) {
    	
    	for(int i = 0 ; i< flows.size(); i++) {
    		gen.writeStartObject();
    			gen.write("name", flows.get(i).getName());
    			gen.write("ID", flows.get(i).getId());
    			gen.writeStartArray("Connectibles");
    				flows.get(i).jsonConnectibles(gen);
    			gen.writeEnd();
    		
    			gen.writeStartArray("Connections");
    				flows.get(i).jsonConnections(gen);
    			gen.writeEnd();
    		
    		gen.writeEnd();
    	}
    	
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setType(int type)
    {
        this.type = type;
    }
    
    public ArrayList<Flow> getFlows()
    {
        return flows;
    }
    
    public void addFlow(Flow flow)
    {
        flows.add(flow);
    }
}
