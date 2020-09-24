package tp1;
import java.util.*;

public class Connections
{
    private int id;
    private ArrayList<Integer> toId;
    
Connections (int id){
    this.id = id;
    toId= new ArrayList<>();
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
}
