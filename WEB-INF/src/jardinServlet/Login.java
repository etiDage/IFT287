package jardinServlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.Connexion;
import JardinCollectif.IFT287Exception;


public class Login extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
    	{
    		System.out.println("Servlet Login : POST");
    		if(JardinHelper.infoBDValide(getServletContext()))
    		{
    			JardinHelper.DispatchToLogin(request, response);
    			return;
    		}
    		
    		//Lecture des params de login.jsp
    		String userId = request.getParameter("userIdBD");
    		String motDePasse = request.getParameter("motDePasseBD");
    		String serveur = request.getParameter("serveur");
    		String bd = request.getParameter("bd");
    		
    		request.setAttribute("userIdBD", userId);
    		request.setAttribute("motDePasseBD", motDePasse);
    		request.setAttribute("serveur", serveur);
    		request.setAttribute("bd", bd);
    		
    		if(userId == null || userId.equals(""))
    		{
    			throw new IFT287Exception("Vous devez entre un nom d'utilisateur.");
    		}
    		
    		if(motDePasse == null || motDePasse.equals(""))
    		{
    			throw new IFT287Exception("Vous devez entrer un mot de passe");
    		}
    		
    		if(bd == null || bd.equals(""))
    		{
    			throw new IFT287Exception("Vous dever entrer un nom de base de donnees");
    		}
    		
    		if(serveur == null || serveur.equals(""))
    		{
    			throw new IFT287Exception("Vous devez choisir un serveur");
    		}
    		
    		try
    		{
    			Connexion cx = new Connexion(serveur, bd, userId, motDePasse);
    			cx.fermer();
    			
    			
                getServletContext().setAttribute("serveur", serveur);
                getServletContext().setAttribute("bd", bd);
                getServletContext().setAttribute("user", userId);
                getServletContext().setAttribute("pass", motDePasse);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
                dispatcher.forward(request, response);
    		}
    		catch(Exception e)
    		{
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add("Erreur de connexion au serveur de base de donnée");
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                // pour déboggage seulement : afficher tout le contenu de
                // l'exception
                e.printStackTrace();
    		}
    	}
    	catch(IFT287Exception e)
    	{
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            // pour déboggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
    	}
    }
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	System.out.println("Servlet Login : GET");
    	
    	if(JardinHelper.infoBDValide(getServletContext()))
    	{
    		JardinHelper.DispatchToLogin(request, response);
    	}
    	else
    	{
    		JardinHelper.DispatchToBDConnect(request, response);
    	}
    }
}
