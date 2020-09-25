// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule
package tp1;
import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

/**
 * Fichier de base pour le Devoir1A du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier XML en son équivalent en JSON.
 *
 * Paramètres du programme
 * 0- Nom du fichier XML
 * 1- Nom du fichier JSON
 * 
 * </pre>
 */
public class Devoir1A
{

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1A <fichierXML> <fichierJSON>");
            return;
        }
        
        String nomFichierXML = args[0];
        String nomFichierJSON = args[1];
        
        System.out.println("Debut de la conversion du fichier " + nomFichierXML + " vers le fichier " + nomFichierJSON);
        
        // Votre code de conversion devrait aller ici
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        DefaultHandler handler = new MonParser();
        parser.parse(new File(nomFichierXML), handler);
        
        MainBody patient = ((MonParser) handler).getMainBody();
        
        
        Map<String, Object> config = new HashMap<String, Object>(1);
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        FileWriter writer = new FileWriter(nomFichierJSON);
        JsonGeneratorFactory f = Json.createGeneratorFactory(config);
		JsonGenerator gen = f.createGenerator(writer);
		
		gen.writeStartObject();
		
		patient.jsonMainBody(gen);
		
		gen.writeEnd();
		gen.close();
       
        
        System.out.println("Conversion terminee.");
    }

}
