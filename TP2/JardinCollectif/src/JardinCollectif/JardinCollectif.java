// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package JardinCollectif;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class JardinCollectif
{
    private static Connexion cx;
    private static GestionJardin gestionJardin;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java JardinCollectif.JardinCollectif <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        JardinCollectif jardinInstance = null;
        try
        {
            // Ouverture du fichier de transactions
            // s'il est spécifié comme argument
            boolean lectureAuClavier = true;
            InputStream sourceTransaction = System.in;
            if (args.length > 4)
            {
                sourceTransaction = new FileInputStream(args[4]);
                lectureAuClavier = false;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(sourceTransaction));
            jardinInstance = new JardinCollectif(args[0], args[1], args[2], args[3]);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (jardinInstance != null)
                jardinInstance.fermer();
        }
    }

    public JardinCollectif(String serveur, String bd, String user, String pass) throws IFT287Exception, SQLException
    {
    	gestionJardin = new GestionJardin(serveur, bd, user, pass);
    }
    /**
     * Decodage et traitement d'une transaction
     * @throws SQLException 
     */
    
    public void fermer() throws SQLException
    {
    	gestionJardin.fermer();
    }
    
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if(command.equals("help"))
                {
                	afficherAide();
                }
                else if (command.equals("inscrireMembre"))
                {
                    // Lecture des parametres
                    String prenom = readString(tokenizer);
                    String nom = readString(tokenizer);
                    String motDePasse = readString(tokenizer);
                    int noMembre = readInt(tokenizer);
                    
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionJardin.getGestionMembre().inscrireMembre(noMembre, prenom, nom, motDePasse);
                    
                }
                else if (command.equals("supprimerMembre"))
                {
                	//Lecture des parametres
                	int noMembre = readInt(tokenizer);
                	
                	gestionJardin.getGestionMembre().supprimerMembre(noMembre);
                }
                else if (command.equals("promouvoirAdministrateur"))
                {
                	int noMembre = readInt(tokenizer);
                	
                	gestionJardin.getGestionMembre().promouvoirMembre(noMembre);
                }
                else if (command.equals("ajouterLot"))
                {
                	String nomLot = readString(tokenizer);
                	int nbMaxMembre = readInt(tokenizer);
                }
                else if (command.equals("supprimerLot"))
                {
                	String nomLot = readString(tokenizer);
                }
                else if (command.equals("rejoindreLot"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                }
                else if (command.equals("accepterDemande"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                }
                else if (command.equals("refuserDemande"))
                {
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                }
                else if (command.equals("ajouterPlante"))
                {
                	String nomPlante = readString(tokenizer);
                	int tempsDeCulture = readInt(tokenizer);
                }
                else if (command.equals("retirerPlante"))
                {
                	String nomPlante = readString(tokenizer);
                	int tempsDeCulture = readInt(tokenizer);
                }
                else if (command.equals("planterPlante"))
                {
                	String nomPlante = readString(tokenizer);
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                	int nbExemplaires = readInt(tokenizer);
                	Date datePlantation = readDate(tokenizer);
                }
                else if (command.equals("recolterPlante"))
                {
                	String nomPlante = readString(tokenizer);
                	String nomLot = readString(tokenizer);
                	int noMembre = readInt(tokenizer);
                }
                else if (command.equals("afficherMembres"))
                {
                	gestionJardin.getGestionInterrogation().afficherMembres();
                }
                else if (command.equals("afficherPlantes"))
                {
                	gestionJardin.getGestionInterrogation().afficherPlantes();
                }
                else if (command.equals("afficherLots"))
                {
                	gestionJardin.getGestionInterrogation().afficherLots();
                }
                else if (command.equals("afficherPlantesLot"))
                {
                	String nomLot = readString(tokenizer);
                	gestionJardin.getGestionInterrogation().afficherPlantsLot(nomLot);
                }
                else if (command.equals("quitter"))
                {
                	
                }
                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            cx.rollback();
        }
    }
    
    private static void afficherAide()
    {
        System.out.println();
        System.out.println("Chaque transaction comporte un nom et une liste d'arguments");
        System.out.println("separes par des espaces. La liste peut etre vide.");
        System.out.println(" Les dates sont en format yyyy-mm-dd.");
        System.out.println("");
        System.out.println("Les transactions sont:");
        System.out.println("inscrireMembre <prenom> <nom> <motDePasse> <noMembre>");
        System.out.println("supprimerMembre <noMembre>");
        System.out.println("promouvoirAdministrateur <noMembre>");
        System.out.println("ajouterLot <nomLot> <nbMaxMembre>");
        System.out.println("supprimerLot <nomLot>");
        System.out.println("rejoindreLot <nomLot <noMembre>");
        System.out.println("accepterDemande <nomLot> <noMembre>");
        System.out.println("refuserDemande <nomLot> <noMembre>");
        System.out.println("ajouterPlante <nomPlante> <tempsDeCulture>");
        System.out.println("retirerPlante <nomPlante>");
        System.out.println("planterPlante <nomPlante> <nomLot> <noMembre> <nbExemplaires> <datePlantation>");
        System.out.println("recolterPlante <nomPlante> <nomLot> <noMembre>");
        System.out.println("afficherMembres");
        System.out.println("afficherPlantes");
        System.out.println("afficherLots");
        System.out.println("afficherPlantesLot <lot>");
        System.out.println("quitter");
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
