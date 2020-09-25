package tp1;

import javax.json.stream.JsonGenerator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public void xmlConnectible(Document document, Element n) 
{
	switch(type) {
case "Atrium":
	Element Atr= document.createElement("Atrium");
	n.appendChild(Atr);
	xmlSetAttributeConnectible(Atr);
	break;
	
case "Ventricle":
	Element vent= document.createElement("Ventricule");
	n.appendChild(vent);
	xmlSetAttributeConnectible(vent);
	break;

case "Artery":
	Element Art= document.createElement("Artery");
	n.appendChild(Art);
	xmlSetAttributeConnectible(Art);
	break;
	
case "Vein":
	Element Vein= document.createElement("Vein");
	n.appendChild(Vein);
	xmlSetAttributeConnectible(Vein);
	
	break;
	
case "Capillaries":
	Element Cap= document.createElement("Capillaries");
	n.appendChild(Cap);
	xmlSetAttributeConnectible(Cap);

	break;
	
case "Nose":
	Element Nose= document.createElement("Nose");
	n.appendChild(Nose);
	xmlSetAttributeConnectible(Nose);
	break;

case "AirConnectible":
	Element Air= document.createElement("AirConnectible");
	n.appendChild(Air);
	xmlSetAttributeConnectible(Air);
	break;

case "Alveoli":
	Element Alve= document.createElement("Alveoli");
	n.appendChild(Alve);
	xmlSetAttributeConnectible(Alve);
	break;
	
case "DigestiveTract":
	Element Dige= document.createElement("DigestiveTract");
	n.appendChild(Dige);
	xmlSetAttributeConnectible(Dige);
	break;
	
case "StomachTract":
	Element Sto= document.createElement("StomachTract");
	n.appendChild(Sto);
	xmlSetAttributeConnectible(Sto);
	break;

case "DuodenumTract":
	Element Duode= document.createElement("DuodenumTract");
	n.appendChild(Duode);
	xmlSetAttributeConnectible(Duode);
	break;

case "RectumTract":
	Element Rect= document.createElement("RectumTract");
	n.appendChild(Rect);
	xmlSetAttributeConnectible(Rect);
	break;
	
case "BiDuct":
	Element Bi= document.createElement("BiDuct");
	n.appendChild(Bi);
	xmlSetAttributeConnectible(Bi);
	break;
	
case "Duct":
	Element Duct= document.createElement("Duct");
	n.appendChild(Duct);
	xmlSetAttributeConnectible(Duct);
	break;

case "DuctOverflowableJunction":
	Element Dover= document.createElement("DuctOverflowableJunction");
	n.appendChild(Dover);
	xmlSetAttributeConnectible(Dover);
	break;

case "DeversingDuct":
	Element Dever= document.createElement("DeversingDuct");
	n.appendChild(Dever);
	xmlSetAttributeConnectible(Dever);
	break;

case "InnerGallbladder":
	Element Inner= document.createElement("InnerGallbladder");
	n.appendChild(Inner);
	xmlSetAttributeConnectible(Inner);
	break;

case "SalivaryDuct":
	Element Saliva= document.createElement("SalivaryDuct");
	n.appendChild(Saliva);
	xmlSetAttributeConnectible(Saliva);
	break;
	}
    
}
public void xmlSetAttributeConnectible(Element n) {
	
	n.setAttribute("name",name);
    n.setAttribute("id",Integer.toString(id));
    if(volume>=0) {
    	n.setAttribute("volume",String.valueOf(volume));
	}
	if(length>=0) {
		n.setAttribute("length",String.valueOf(length));
	}
	if(startRadius>=0) {
		n.setAttribute("startRadius",String.valueOf(startRadius));
	}
	if(endRadius>=0) {
		n.setAttribute("endRadius",String.valueOf(endRadius));
	}
	
}
}
