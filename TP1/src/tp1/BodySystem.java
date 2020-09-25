package tp1;

import java.util.ArrayList;

import javax.json.stream.JsonGenerator;

public class BodySystem
{
    private String name;
    private int id;
    private int type;
    
    private ArrayList<Flow> flows;
    
    public BodySystem(String name, int id, int type)
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
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
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

	public void jsonSystem(JsonGenerator gen) 
	{
		gen.writeStartObject();
		gen.write("name", name);
		gen.write("ID", id);
		gen.write("type", type);
		gen.writeStartArray("Flows");
		for(Flow flow : flows)
		{
			flow.jsonFlow(gen);
		}
		gen.writeEnd();
		gen.writeEnd();

		
	}
	public void xmlSystem(Document document, Node n) 
	{
		n.setAttribute("name",name);
        n.setAttribute("ID",id);
        n.setAttribute("type",type);
        for(Flow flow : flows)
		{
        	Node flo =document.createElement("Flows");
            n.appendChild(flo);
            flow.xmlFlow(document, flo);
		}
       
        
	}
}
