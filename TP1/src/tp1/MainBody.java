package tp1;

import java.util.ArrayList;

public class MainBody
{
    private String bodyName;
    private int bodyID;
    
    private ArrayList<BodySystem> systems;
    private ArrayList<Organ> organs;
    
    
    public MainBody(String bodyName, int bodyID)
    {
        this.setBodyName(bodyName);
        this.setBodyID(bodyID);
        
        systems = new ArrayList<BodySystem>();
        organs = new ArrayList<Organ>();
    }

    public String getBodyName()
    {
        return bodyName;
    }


    public void setBodyName(String bodyName)
    {
        this.bodyName = bodyName;
    }


    public int getBodyID()
    {
        return bodyID;
    }


    public void setBodyID(int bodyID)
    {
        this.bodyID = bodyID;
    }
    
    public ArrayList<BodySystem> getSystems()
    {
        return systems;
    }
    
    public void addSystem(BodySystem system)
    {
        systems.add(system);
    }
    
    public ArrayList<Organ> getOrgans()
    {
        return organs;
    }
    
    public void addOrgan (Organ organ)
    {
        organs.add(organ);
    }
}