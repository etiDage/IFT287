package tp1;

import java.util.ArrayList;

public class BodySystem
{
    private String name;
    private String id;
    private String type;
    
    private ArrayList<Flow> flows;
    
    public BodySystem(String name, String id, String type)
    {
        this.name = name;
        this.id = id;
        this.type = type;
        
        flows = new ArrayList<Flow>();
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
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
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
