package tp1;

public class Connectible
{   
   private String id;
   private String type;
   private  String name;
   private float volume;
   private float length;
   private double startRadius;
   private double endRadius;
   
Connectible (String name, String id, String type){
    
    this.id = id;
    this.type = type;
    this.name = name;
    volume= -1;
    length= -1;
    startRadius =-1;
    endRadius = -1;
}

   
public String getId()
{
    return id;
}
public void setId(String id)
{
    this.id = id;
}
public String getType()
{
    return type;
}
public void setType(String type)
{
    this.type = type;
}
public String getName()
{
    return name;
}
public void setName(String name)
{
    this.name = name;
}
public float getVolume()
{
    return volume;
}
public void setVolume(float volume)
{
    this.volume = volume;
}
public float getLength()
{
    return length;
}
public void setLength(float length)
{
    this.length = length;
}
public double getStartRadius()
{
    return startRadius;
}
public void setStartRadius(double startRadius)
{
    this.startRadius = startRadius;
}
public double getEndRadius()
{
    return endRadius;
}
public void setEndRadius(double endRadius)
{
    this.endRadius = endRadius;
}
}
