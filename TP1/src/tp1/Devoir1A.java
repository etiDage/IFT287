// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package tp1;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) throws IOException 
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1A <fichierXML> <fichierJSON>");
            return;
        }
        
        String nomFichierXML = args[0];
        String nomFichierJSON = args[1];
        
        System.out.println("Debut de la conversion du fichier " + nomFichierXML + " vers le fichier " + nomFichierJSON);
        
        MainBody patient= new MainBody("Charlot", "1");

        // Votre code de conversion devrait aller ici
			@SuppressWarnings("resource")
			FileWriter writer = new FileWriter(nomFichierJSON);
			JsonGenerator gen = Json.createGenerator(writer);
			
			//Map<String, Object> config = new HashMap<String, Object>(1);
			//config.put(JsonGenerator.PRETTY_PRINTING,true);
			//StringWriter w = new StringWriter();
			//JsonGeneratorFactory f = Json.createGeneratorFactory(config);
			//JsonGenerator jsonGenerator = f.createGenerator(w);
			
		gen.writeStartObject();
			gen.write("Name", patient.getBodyName());
			gen.write("ID", patient.getBodyID());
			gen.writeStartArray("Organs");
				patient.jsonOrgans(gen);
			gen.writeEnd();
			gen.writeStartArray("Systems");
				patient.jsonBodySystem(gen);
			gen.writeEnd();
			
		gen.writeEnd();
		gen.close();

        
        System.out.println("Conversion terminee.");
    }

}
