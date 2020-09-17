package tp1;

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
    
    
}
