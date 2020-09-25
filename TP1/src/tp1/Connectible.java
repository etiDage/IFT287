package tp1;

import javax.json.stream.JsonGenerator;

public class Connectible
{   
   private int id;
   private String type;
   private  String name;
   private double volume;
   private float length;
   private double startRadius;
   private double endRadius;
   
Connectible (String name, int id, String type){
    
    this.id = id;
    this.type = type;
    this.name = name;
    volume= -1;
    length= -1;
    startRadius =-1;
    endRadius = -1;
}

   
public int getId()
{
    return id;
}
public void setId(int id)
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
public double getVolume()
{
    return volume;
}
public void setVolume(double volume)
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

public void jsonConnectible(JsonGenerator gen)
{
	gen.writeStartObject();
	gen.write("type", type);
	gen.write("name", name);
	gen.write("id", id);
	if(volume>=0) {
		gen.write("volume", volume);
	}
	if(length>=0) {
		gen.write("length", length);
	}
	if(startRadius>=0) {
		gen.write("startRadius", startRadius);
	}
	if(endRadius>=0) {
		gen.write("endRadius", endRadius);
	}
	gen.writeEnd();

}
}
