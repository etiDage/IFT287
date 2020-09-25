package tp1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MonParser extends DefaultHandler {
	
	
	private MainBody mainBody;
	private BodySystem bodySystem;
	private Flow flow;
	private Connectible connectible;
	private Connections connection;
	private int toId;
	private Organ organ;
	
	
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
			mainBody = new MainBody(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)));
			break;
			
		case "System":
			bodySystem = new BodySystem(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), Integer.parseInt(attrs.getValue(2)));
			break;
			
		case "Flow":
			flow = new Flow(Integer.parseInt(attrs.getValue("id")), attrs.getValue("name"));
			break;
		
		case "Connectible":
			break;
			
		case "Atrium":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;
			
		case "Ventricle":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;
		
		case "Artery":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setStartRadius(Double.parseDouble(attrs.getValue("startRadius")));
			if(attrs.getValue("endRadius") != null)
			{
				connectible.setEndRadius(Double.parseDouble(attrs.getValue("endRadius")));
			}
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			
			break;
			
		case "Vein":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setStartRadius(Double.parseDouble(attrs.getValue("startRadius")));
			if(attrs.getValue("endRadius") != null)
			{
				connectible.setEndRadius(Double.parseDouble(attrs.getValue("endRadius")));
			}
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			
			break;
			
		case "Capillaries":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			if(attrs.getValue("length") != null)
			{
				connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			}

			break;
			
		case "Nose":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;

		case "AirConnectible":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setStartRadius(Double.parseDouble(attrs.getValue("startRadius")));
			connectible.setEndRadius(Double.parseDouble(attrs.getValue("endRadius")));
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			break;

		case "Alveoli":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;
			
		case "DigestiveTract":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;
			
		case "StomachTract":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;

		case "DuodenumTract":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;

		case "RectumTract":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;
			
		case "BiDuct":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;
			
		case "Duct":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;

		case "DuctOverflowableJunction":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;

		case "DeversingDuct":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;

		case "InnerGallbladder":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			break;

		case "SalivaryDuct":
			connectible = new Connectible(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), qName);
			connectible.setLength(Float.parseFloat(attrs.getValue("length")));
			connectible.setVolume(Double.parseDouble(attrs.getValue("volume")));
			break;

		case "Connections":
			break;
			
		case "Connection":
			connection = new Connections(Integer.parseInt(attrs.getValue(0)));
			break;
			
		case "to":
			toId = Integer.parseInt(attrs.getValue("id"));
			break;
			
		case "Organs":
			break;
			
		case "Organ":
			organ = new Organ(attrs.getValue(0), Integer.parseInt(attrs.getValue(1)), Integer.parseInt(attrs.getValue(2)));
			break;
			
		}
	}
	
	public void endElement(String nidamespace, String lname, String qName)
	{
		switch(qName)
		{
		case "MainBody":
			break;
			
		case "System":
			mainBody.addSystem(bodySystem);
			break;
			
		case "Flow":
			System.out.println(flow.getName());
			bodySystem.addFlow(flow);
			break;
		
		case "Connectible":
			break;
			
		case "Atrium":
			flow.addConnectible(connectible);
			break;
			
		case "Ventricle":
			flow.addConnectible(connectible);
			break;
		
		case "Artery":
			flow.addConnectible(connectible);
			break;
			
		case "Vein":
			flow.addConnectible(connectible);
			break;
			
		case "Capillaries":
			flow.addConnectible(connectible);
			break;
			
		case "Nose":
			flow.addConnectible(connectible);
			break;

		case "AirConnectible":
			flow.addConnectible(connectible);
			break;
			
		case "Alveoli":
			flow.addConnectible(connectible);
			break;
			
		case "DigestiveTract":
			flow.addConnectible(connectible);
			break;
			
		case "StomachTract":
			flow.addConnectible(connectible);
			break;

		case "DuodenumTract":
			flow.addConnectible(connectible);
			break;

		case "RectumTract":
			flow.addConnectible(connectible);
			break;
			
		case "BiDuct":
			flow.addConnectible(connectible);
			break;
			
		case "Duct":
			flow.addConnectible(connectible);
			break;

		case "DuctOverflowableJunction":
			flow.addConnectible(connectible);
			break;

		case "DeversingDuct":
			flow.addConnectible(connectible);
			break;

		case "InnerGallbladder":
			flow.addConnectible(connectible);
			break;

		case "SalivaryDuct":
			flow.addConnectible(connectible);
			break;

		case "Connections":
			break;
			
		case "Connection":
			flow.addConnections(connection);
			break;
			
		case "to":
			connection.addToId(toId);
			break;
			
		case "Organs":
			break;
			
		case "Organ":
			mainBody.addOrgan(organ);
			break;
			
		}
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
	
	public MainBody getMainBody()
	{
		return mainBody;
	}
}
