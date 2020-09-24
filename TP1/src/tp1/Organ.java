package tp1;

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
    
    
}
