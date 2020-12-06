package jardinServlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JardinCollectif.GestionJardin;
import JardinCollectif.IFT287Exception;

public class GererMembre extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer etat = (Integer) request.getSession().getAttribute("etat");
		if(etat == null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("promouvoir") != null)
		{
			traiterPromouvoir(request, response);
		}
		else if(request.getParameter("supprimer") != null)
		{
			traiterSupprimer(request, response);
		}
		else if(request.getParameter("accepterDemande") != null)
		{
			traiterAccepterDemande(request, response);
		}
		else if(request.getParameter("refuserDemande") != null)
		{
			traiterRefuserDemande(request, response);
		}
        else
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add("Choix non reconnu");
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gererMembre.jsp");
            dispatcher.forward(request, response);
        }
	}
		
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
    
    public void traiterPromouvoir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
    	{
    		if(request.getParameter("membreSelectionne") == null)
    			throw new IFT287Exception("Aucun membre selectionner");
    		String userId = request.getParameter("membreSelectionne");
    		GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
    		synchronized(jardinUpdate)
    		{
    			jardinUpdate.getGestionMembre().promouvoirMembre(userId);
    		}
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
    		dispatcher.forward(request, response);

    	}
		catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gererMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }

    }
    
    public void traiterSupprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
    	{
    		if(request.getParameter("membreSelectionne") == null)
    			throw new IFT287Exception("Aucun membre selectionner");
    		String userId = request.getParameter("membreSelectionne");
    		GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
    		synchronized(jardinUpdate)
    		{
    			jardinUpdate.getGestionMembre().supprimerMembre(userId);
    		}
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
    		dispatcher.forward(request, response);

    	}
		catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gererMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
    
    public void traiterAccepterDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
    	{
    		if(request.getParameter("demandeSelectionne") == null)
    			throw new IFT287Exception("Aucune demande selectionner");
    		String info = request.getParameter("demandeSelectionne");
			List<String> infosDemande = Arrays.asList(info.split(","));
    		GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
    		synchronized(jardinUpdate)
    		{
    			jardinUpdate.getGestionDemandeAssignation().accepterDemande(infosDemande.get(0), infosDemande.get(1));
    		}
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
    		dispatcher.forward(request, response);

    	}
		catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gererMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }
    
    public void traiterRefuserDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
    	{
    		if(request.getParameter("demandeSelectionne") == null)
    			throw new IFT287Exception("Aucune demande selectionner");
    		String info = request.getParameter("demandeSelectionne");
			List<String> infosDemande = Arrays.asList(info.split(","));
    		GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
    		synchronized(jardinUpdate)
    		{
    			jardinUpdate.getGestionDemandeAssignation().refuserDemande(infosDemande.get(0), infosDemande.get(1));
    		}
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
    		dispatcher.forward(request, response);

    	}
		catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gererMembre.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}
