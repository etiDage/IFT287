package jardinServlet;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.IFT287Exception;
import JardinCollectif.GestionJardin;

public class PlanterPlant extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("recolter")!= null) {
        	traiterRecolter(request, response);
        }
        else
            try
            {
                String nomLot = request.getParameter("nomLot");
                request.setAttribute("nomLot", nomLot);
                String nomPlante = request.getParameter("nomPlante");
                request.setAttribute("nomPlante", nomLot);
                String nbExemplaire = request.getParameter("nbExemplaire");
                request.setAttribute("nbExemplaire", nbExemplaire);

                // conversion du parametre bombre Exemplaire en entier
                int nb = -1; // inialisation requise par compilateur Java
                try
                {
                    nb = Integer.parseInt(nbExemplaire);
                }
                catch (NumberFormatException e)
                {
                    throw new IFT287Exception("Format de nombre d'exemplaire " + nbExemplaire + " incorrect.");
                }
                
                Date datePlantation = new Date(System.currentTimeMillis());
                String userId = (String) request.getSession().getAttribute("userID");
                GestionJardin jardinUpdate = (GestionJardin) request.getSession()
                        .getAttribute("jardinUpdate");
                synchronized (jardinUpdate)
                {
                	jardinUpdate.getGestionPlants().planterPlante(nomPlante, nomLot, userId, nb, datePlantation);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                dispatcher.forward(request, response);
            }
            catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/planterPlant.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
            }
    }
	
	public void traiterRecolter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		try {
			if(request.getParameter("plantSelectionne") == null) {
				throw new IFT287Exception("Aucun plant s�lectionn�");
			}
			String info = request.getParameter("plantSelectionne");
			List<String> infos = Arrays.asList(info.split(","));
			String nomLot = infos.get(0);
			String nomPlante = infos.get(1);
			String userid = (String) request.getSession().getAttribute("userId");
			
			GestionJardin jardinUpdate = (GestionJardin) request.getSession()
                    .getAttribute("jardinUpdate");
            synchronized (jardinUpdate)
            {
            	jardinUpdate.getGestionPlants().recolterPlante(nomPlante, nomLot, userid);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/planterPlant.jsp");
            dispatcher.forward(request, response);
		}
		catch(IFT287Exception e){
			List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/planterPlant.jsp");
            dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
		}
	}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
