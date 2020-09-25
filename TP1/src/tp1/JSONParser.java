package tp1;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;

public class JSONParser {
	
	private JsonObject parser;
	private MainBody mainBody;
	
	public JSONParser(JsonObject parser)
	{
		this.parser = parser;
	}
	
	
	public MainBody parseMainBody()
	{
		mainBody = new MainBody(parser.getString("name"), parser.getInt("id"));
		JsonArray systems = (JsonArray) parser.get("Systems");
		systems.forEach(systemParser -> {
			JsonObject system = (JsonObject) systemParser;
			mainBody.addSystem(parseBodySystem(system));
		});
		JsonArray organs = (JsonArray) parser.get("Organs");
		organs.forEach(organParser -> {
			JsonObject organ = (JsonObject) organParser;
			mainBody.addOrgan(parseOrgan(organ));
		});
		
		return mainBody;
	}
	
	private Organ parseOrgan(JsonObject organParser) {
		Organ organ = new Organ(organParser.getString("name"), organParser.getInt("id"), organParser.getInt("systemID"));
		return organ;
	}


	private BodySystem parseBodySystem(JsonObject systemParser)
	{
		BodySystem bodySystem = new BodySystem(systemParser.getString("name"), systemParser.getInt("id"), systemParser.getInt("type"));
		JsonArray flows = (JsonArray) systemParser.get("Flow");
		flows.forEach(flowParser -> {
			JsonObject flow = (JsonObject) flowParser;
			bodySystem.addFlow(parseFlow(flow));
		});
		
		return bodySystem;
	}
	
	private Flow parseFlow(JsonObject flowParser)
	{
		Flow flow = new Flow(flowParser.getInt("id"), flowParser.getString("name"));
		JsonArray connectibles = (JsonArray) flowParser.get("Connectibles");
		connectibles.forEach(connectibleParser -> {
			JsonObject connectible = (JsonObject) connectibleParser;
			flow.addConnectible(parseConnectible(connectible));
		});
		JsonArray connections = (JsonArray) flowParser.get("Connections");
		connections.forEach(connectionParser -> {
			JsonObject connection = (JsonObject) connectionParser;
			flow.addConnections(parseConnection(connection));
		});
		
		return flow;
		
	}


	private Connections parseConnection(JsonObject connectionParser) 
	{
		Connections connection = new Connections(connectionParser.getInt("id"));
		JsonArray tos = (JsonArray) connectionParser.get("to");
		tos.forEach(toParser -> {
			JsonObject to = (JsonObject) toParser;
			connection.addToId(to.getInt("id"));
		});
		
		return connection;
	}


	private Connectible parseConnectible(JsonObject connectibleParser) 
	{
		Connectible connectible = new Connectible(connectibleParser.getString("name"), connectibleParser.getInt("id"), connectibleParser.getString("type"));
		
		if(connectibleParser.getJsonNumber("volume") != null)
		{
			JsonNumber jsonNum = connectibleParser.getJsonNumber("volume");
			double volume = jsonNum.doubleValue();
			System.out.println(volume);
			connectible.setVolume(volume);
		}
		
		if(connectibleParser.getJsonNumber("length") != null)
		{
			JsonNumber jsonNum = connectibleParser.getJsonNumber("length");
			float length = (float) jsonNum.doubleValue();
			connectible.setLength(length);
		}
		
		if(connectibleParser.getJsonNumber("startRadius") != null)
		{
			JsonNumber jsonNum = connectibleParser.getJsonNumber("startRadius");
			double startRadius = jsonNum.doubleValue();
			connectible.setStartRadius(startRadius);
		}
		
		if(connectibleParser.getJsonNumber("endRadius") != null)
		{
			JsonNumber jsonNum = connectibleParser.getJsonNumber("endRadius");
			double endRadius = jsonNum.doubleValue();
			connectible.setEndRadius(endRadius);
		}

		return connectible;
	}

}
