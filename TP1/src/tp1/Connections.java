package tp1;
import java.util.*;

public class Connections
{
    private String id;
    private ArrayList<String> toId;
    
Connections (String id){
    this.id = id;
    toId= new ArrayList<>();
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
