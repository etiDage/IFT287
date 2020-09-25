package tp1;

import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Organ
{
    private String name;
    private int id;
    private int systemID;
    
    public Organ(String name, int id, int systemID)
    {
        this.setName(name);
        this.setId(id);
        this.setSystemID(systemID);
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

    public int getSystemID()
    {
        return systemID;
    }

    public void setSystemID(int systemID)
    {
        this.systemID = systemID;
    }

	public void jsonOrgan(JsonGenerator gen) 
	{
		gen.writeStartObject();
		gen.write("name", name);
		gen.write("id", id);
		gen.write("systemID", systemID);
		gen.writeEnd();

	}
	public void xmlOrgan(Document document, Element n) 
	{
		n.setAttribute("name",name);
        n.setAttribute("id",Integer.toString(id));
        n.setAttribute("systemID",Integer.toString(systemID));
       
        
	}
	
    
    
}
