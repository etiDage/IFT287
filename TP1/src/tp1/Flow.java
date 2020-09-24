package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

public class Flow
{
    private String id;
    private String name;
    private ArrayList<Connectible> connectibles;
    private ArrayList<Connections>  connections; 
    
    Flow(String id, String name){
        this.id= id;
        this.name = name;
        connectibles= new ArrayList<>();
        connections = new ArrayList<>();
    }
    
	public void jsonConnectibles(JsonGenerator gen) {
		for(int i = 0 ; i< connectibles.size(); i++) {
    		gen.writeStartObject();
    		gen.write("toId", connectibles.get(i).getId());
    		gen.write("name", connectibles.get(i).getName());
    		gen.write("type", connectibles.get(i).getType());
    		if(connectibles.get(i).getVolume()>=0) {
    			gen.write("Volume", connectibles.get(i).getVolume());
    		}
    		if(connectibles.get(i).getLength()>=0) {
    			gen.write("Volume",connectibles.get(i).getLength());
    		}
    		if(connectibles.get(i).getStartRadius()>=0) {
    			gen.write("Volume",connectibles.get(i).getStartRadius());
    		}
    		if(connectibles.get(i).getEndRadius()>=0) {
    			gen.write("Volume",connectibles.get(i).getEndRadius());
    		}
    		gen.writeEnd();
    	}
	} 
	
public void jsonConnections (JsonGenerator gen) {
    	
    	for(int i = 0 ; i< connections.size(); i++) {
    		gen.writeStartObject();
    			gen.write("ID", connections.get(i).getId());
    			gen.writeStartArray("toId");
    				connections.get(i).jsonToId(gen);
    			gen.writeEnd();
    		
    		gen.writeEnd();
    	}
    	
    }
	
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public ArrayList<Connectible> getConnectibles()
    {
        return connectibles;
    }
    public void addConnectible(Connectible connectible )
    {
        connectibles.add(connectible);
    }
    public ArrayList<Connections> getConnections()
    {
        return connections;
    }
    public void addConnections(Connections connect )
    {
    		connections.add(connect);
    }
}
