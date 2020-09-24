package tp1;
import java.util.*;

import javax.json.stream.JsonGenerator;

public class Connections
{
    private String id;
    private ArrayList<String> toId;
    
Connections (String id){
    this.id = id;
    toId= new ArrayList<>();
}

	public void jsonToId(JsonGenerator gen) {
		for(int i = 0 ; i< toId.size(); i++) {
    		gen.writeStartObject();
    		gen.write(toId.get(i));
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
    public List<String> getToId()
    {
        return toId;
    }
    public void addToId(String id)
    {
        toId.add(id);
    }
}
