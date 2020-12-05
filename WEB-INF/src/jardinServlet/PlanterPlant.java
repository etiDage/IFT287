package jardinServlet;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Bibliotheque.BiblioException;
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
        else if (etat.intValue() != JardinConstantes.MEMBRE_SELECTIONNE
                || request.getParameter("selectionMembre") != null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
            dispatcher.forward(request, response);
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

                // conversion du parametre idLivre en entier
                int nb = -1; // inialisation requise par compilateur Java
                try
                {
                    nb = Integer.parseInt(nbExemplaire);
                }
                catch (NumberFormatException e)
                {
                    throw new IFT287Exception("Format de no Livre " + nbExemplaire + " incorrect.");
                }
                
                Date datePlantation = new Date(System.currentTimeMillis());
                String userid = (String) request.getSession().getAttribute("userId");
                GestionJardin jardinUpdate = (GestionJardin) request.getSession()
                        .getAttribute("jardinUpdate");
                synchronized (jardinUpdate)
                {
                	jardinUpdate.getGestionPlants().planterPlante(nomPlante, nomLot, userid, nb, datePlantation);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/planterPlant.jsp");
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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
