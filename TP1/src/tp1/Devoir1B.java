// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package tp1;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

/**
 * Fichier de base pour le Devoir1B du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier JSON en son équivalent en XML.
 *
 * Paramètres du programme
 * 0- Nom du fichier JSON
 * 1- Nom du fichier XML
 * 
 * </pre>
 */
public class Devoir1B
{

    public static void main(String[] args) throws FileNotFoundException
    {
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1B <fichierJSON> <fichierXML>");
            return;
        }
        
        String nomFichierJSON = args[0];
        String nomFichierXML = args[1];
        
        System.out.println("Debut de la conversion du fichier " + nomFichierJSON + " vers le fichier " + nomFichierXML);

        // Votre code de conversion devrait aller ici
        
        JsonReader reader = Json.createReader(new FileReader(nomFichierJSON));
        JsonStructure json_struct = reader.read();
        
        
        JsonObject parser = (JsonObject) json_struct;
        
        JSONParser jsonParser = new JSONParser(parser);
        
        MainBody mainBody = jsonParser.parseMainBody();
        
        System.out.println(mainBody.getBodyName());
        
        System.out.println("Conversion terminee.");

    }

}
