package tp1;
import java.util.*;

import javax.json.stream.JsonGenerator;

public class Connections
{
    private int id;
    private ArrayList<Integer> toId;
    
	Connections (int id){
	    this.id = id;
	    toId = new ArrayList<Integer>();
	}

	public void jsonToId(JsonGenerator gen) {
		for(int i = 0 ; i< toId.size(); i++) {
    		gen.writeStartObject();
    		int data = toId.get(i);
    		gen.write(data);
    		gen.writeEnd();
    	}
	}

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public List<Integer> getToId()
    {
        return toId;
    }
    public void addToId(int id)
    {
        toId.add(id);
    }
    
    
    public void jsonConnection(JsonGenerator gen)
    {
		gen.writeStartObject();
		gen.write("id", id);
		gen.writeStartArray("to");
		for(int toID : toId)
		{
			gen.writeStartObject();
			gen.write("id", toID);
			gen.writeEnd();
		}
		gen.writeEnd();
	
		gen.writeEnd();

    }
    
	public void xmlConnection(Document document, Node n) 
	{
        n.setAttribute("ID",id);
        for(int toID : toId)
		{
        	Node toId =document.createElement("ToId");
            n.appendChild(toId);
            toId.setAttribute("id", toID);
		}
        
	}
}
