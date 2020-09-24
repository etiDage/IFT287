package tp1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MonParser extends DefaultHandler {
	
	
	private MainBody mainBody;
	
	
	@Override
	public void startDocument()
	{
		
	}
	
	@Override
	public void endDocument()
	{
		
	}
	
	
	public void startElement(String namespace, String lname, String qName, Attributes attrs)
	{
		switch(qName)
		{
		case "MainBody":
			MainBody main_body = new MainBody(attrs.getValue(0), attrs.getValue(1));
			mainBody = main_body;
			break;
			
		case "BodySystem":
			BodySystem body_system = new BodySystem(attrs.getValue(0), attrs.getValue(1), attrs.getValue(2));
			
			
		}
	}
	
	public void endElement(String namespace, String lname, String qName)
	{
		
	}
	
	public void warning(SAXParseException e)
	{
		
	}

	public void error(SAXParseException e)
	{
		
	}
	
	public void fatalError(SAXParseException e)
	{
		
	}
}
