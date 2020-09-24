package tp1;

import java.util.ArrayList;

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