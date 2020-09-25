package tp1;

import javax.json.stream.JsonGenerator;

public class Organ
{
    private String name;
    private String id;
    private String systemID;
    
    public Organ(String name, String id, String systemID)
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSystemID()
    {
        return systemID;
    }

    public void setSystemID(String systemID)
    {
        this.systemID = systemID;
    }

	public void jsonOrgan(JsonGenerator gen) 
	{
		gen.writeStartObject();
		gen.write("name", name);
		gen.write("ID", id);
		gen.write("systemId", systemID);
		gen.writeEnd();

	}
	public void xmlOrgan(Document document, Node n) 
	{
		n.setAttribute("name",name);
        n.setAttribute("ID",id);
        n.setAttribute("systemId",systemID);
       
        
	}
	
    
    
}
