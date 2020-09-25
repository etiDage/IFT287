package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Flow
{
    private int id;
    private String name;
    private ArrayList<Connectible> connectibles;
    private ArrayList<Connections>  connections; 
    
    Flow(int id, String name){
        this.id= id;
        this.name = name;
        connectibles= new ArrayList<>();
        connections = new ArrayList<>();
    }
    
	
	
    public int getId()
    {
        return id;
    }
    public void setId(int id)
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
    
    public void jsonFlow(JsonGenerator gen)
    {
		gen.writeStartObject();
		gen.write("name", name);
		gen.write("id", id);
		gen.writeStartArray("Connectibles");
		for(Connectible connectible : connectibles)
		{
			connectible.jsonConnectible(gen);
		}
		gen.writeEnd();
	
		gen.writeStartArray("Connections");
		for(Connections connection : connections)
		{
			connection.jsonConnection(gen);
		}
		gen.writeEnd();
	
		gen.writeEnd();

    }
    
    public void xmlFlow(Document document, Element n) 
	{
		n.setAttribute("id",Integer.toString(id));
		n.setAttribute("name",name);
		Element con =document.createElement("Connectible");
		n.appendChild(con);
        for(Connectible connectible : connectibles)
		{
            connectible.xmlConnectible(document, con);
		}
        Element connectionsElement =document.createElement("Connections");
		n.appendChild(connectionsElement);
        for(Connections connection : connections)
		{
        	Element connectio =document.createElement("Connection");
            connectionsElement.appendChild(connectio);
            connection.xmlConnection(document, connectio);
		}
        
	}
}
