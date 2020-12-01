package jardinServlet;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.IFT287Exception;



public class Accueil extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Accueil : POST");
        try
        {
            if (!JardinHelper.peutProcederLogin(getServletContext(), request, response))
            {
                System.out.println("Servlet Accueil : POST ne peut pas procéder.");
                // Le dispatch sera fait par JardinHelper.peutProceder
                return;
            }

            HttpSession session = request.getSession();

            // Si c'est la première fois qu'on essaie de se logguer, ou
            // d'inscrire quelqu'un
            if (!JardinHelper.gestionnairesCrees(session))
            {
                System.out.println("Servlet Accueil : POST Création des gestionnaires");
                JardinHelper.creerGestionnaire(getServletContext(), session);
            }

            if (request.getParameter("connecter") != null)
            {
                System.out.println("Servlet Accueil : POST - Connecter");
                try
                {
                    // lecture des paramètres du formulaire login.jsp
                    String userId = request.getParameter("userId");
                    String motDePasse = request.getParameter("motDePasse");

                    request.setAttribute("userId", userId);
                    request.setAttribute("motDePasse", motDePasse);
                                        
                    if (userId == null || userId.equals(""))
                        throw new IFT287Exception("Le nom d'utilisateur ne peut pas être nul!");
                    if (motDePasse == null || motDePasse.equals(""))
                        throw new IFT287Exception("Le mot de passe ne peut pas être nul!");

                    if (JardinHelper.getJardinInterro(session).getGestionMembre().informationsConnexionValide(Integer.valueOf(userId),
                            motDePasse))
                    {
                        session.setAttribute("userID", userId);
                        if(JardinHelper.getJardinInterro(session).getGestionMembre().utilisateurEstAdmin(Integer.valueOf(userId)))
                            session.setAttribute("admin", true);
                        session.setAttribute("etat", new Integer(JardinConstantes.CONNECTE));

                        System.out.println("Servlet Accueil : POST dispatch vers accueil.jsp");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                        dispatcher.forward(request, response);
                    }
                    else
                    {
                        throw new IFT287Exception("Les informations de connexion sont erronées.");
                    }
                }
                catch (Exception e)
                {
                    List<String> listeMessageErreur = new LinkedList<String>();
                    listeMessageErreur.add(e.getMessage());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
                    dispatcher.forward(request, response);
                    // pour déboggage seulement : afficher tout le contenu de l'exception
                    e.printStackTrace();
                }
            }
            else if (request.getParameter("inscrire") != null)
            {
                System.out.println("Servlet Accueil : POST - Inscrire");
                try
                {
                    // lecture des paramètres du formulaire de creerCompte.jsp
                    String userId = request.getParameter("userId");
                    String motDePasse = request.getParameter("motDePasse");
                    String telephoneS = request.getParameter("telephone");
                    String nom = request.getParameter("nom");

                    request.setAttribute("userId", userId);
                    request.setAttribute("motDePasse", motDePasse);
                    request.setAttribute("telephone", telephoneS);
                    request.setAttribute("nom", nom);
                    
                    if (userId == null || userId.equals(""))
                        throw new BiblioException("Vous devez entrer un nom d'utilisateur!");
                    if (motDePasse == null || motDePasse.equals(""))
                        throw new BiblioException("Vous devez entrer un mot de passe!");
                    if (telephoneS == null || telephoneS.equals(""))
                        throw new BiblioException("Vous devez entrer un numéro de téléphone!");
                    if (nom == null || nom.equals(""))
                        throw new BiblioException("Vous devez entrer un nom!");

                    long telephone = JardinHelper.ConvertirLong(telephoneS, "Le numéro de téléphone");

                    String accesS = request.getParameter("acces");
                    int acces = 1;
                    if (accesS != null)
                        acces = JardinHelper.ConvertirInt(accesS, "Le niveau d'accès");

                    String limitePretS = request.getParameter("limite");
                    int limitePret = 5;
                    if (limitePretS != null)
                        limitePret = JardinHelper.ConvertirInt(limitePretS, "La limite de prêt");

                    GestionBibliotheque biblioUpdate = JardinHelper.getBiblioUpdate(session);
                    synchronized (biblioUpdate)
                    {
                        biblioUpdate.getGestionMembre().inscrire(userId, motDePasse, acces, nom, telephone, limitePret);
                    }

                    // S'il y a déjà un userID dans la session, c'est parce
                    // qu'on est admin et qu'on inscrit un nouveau membre
                    if (session.getAttribute("userID") == null)
                    {
                        session.setAttribute("userID", userId);
                        if(acces == 0)
                            session.setAttribute("admin", acces == 0);
                        session.setAttribute("etat", new Integer(BiblioConstantes.CONNECTE));

                        System.out.println("Servlet Accueil : POST dispatch vers accueil.jsp");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                        dispatcher.forward(request, response);
                    }
                    else
                    {
                        // Vers gestionMembre?
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                catch (Exception e)
                {
                    List<String> listeMessageErreur = new LinkedList<String>();
                    listeMessageErreur.add(e.getMessage());
                    request.setAttribute("listeMessageErreur", listeMessageErreur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creerCompte.jsp");
                    dispatcher.forward(request, response);
                    // pour déboggage seulement : afficher tout le contenu de l'exception
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
            // pour déboggage seulement : afficher tout le contenu de l'exception
            e.printStackTrace();
        }
    }

    // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET
    // c'est que l'adresse a été demandé par l'utilisateur.
    // On procède si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Accueil : GET");
        if (JardinHelper.peutProceder(getServletContext(), request, response))
        {
            System.out.println("Servlet Accueil : GET dispatch vers accueil.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
            dispatcher.forward(request, response);
        }
    }
}
