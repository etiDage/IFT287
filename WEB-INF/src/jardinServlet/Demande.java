package jardinServlet;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.IFT287Exception;
import JardinCollectif.GestionJardin;

public class Demande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer etat =(Integer) request.getSession().getAttribute("etat");
		if(etat ==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try
			{
				String nomLot =request.getParameter("nomLot");
				request.setAttribute("nomLot", nomLot);
				String userId = (String) request.getSession().getAttribute("userID");
				GestionJardin jardinUpdate = (GestionJardin) request.getSession()
                        .getAttribute("jardinUpdate");
				synchronized (jardinUpdate)
                {
                	jardinUpdate.getGestionDemandeAssignation().rejoindreLot(userId, nomLot);
                }
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                dispatcher.forward(request, response);
			}
			catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/demande.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
            }
		}
	}
	
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
	        doGet(request, response);
	    }	
}
