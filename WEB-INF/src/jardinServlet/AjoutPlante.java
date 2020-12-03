package jardinServlet;

import java.util.List;
import java.util.LinkedList;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.IFT287Exception;
import JardinCollectif.GestionJardin;


public class AjoutPlante extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			
			Integer etat =(Integer) request.getSession().getAttribute("etat");
			if(etat ==null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
			else
			try
			{
					String nomPlante = request.getParameter("nomPlante");
					request.setAttribute("nomPlante",nomPlante);
					
					String tempsDeCulture = request.getParameter("tempsDeCulture");
					int nbJour = -1;
					try
	                {
						nbJour = Integer.parseInt(tempsDeCulture);
	                }
	                catch (NumberFormatException e)
	                {
	                    throw new IFT287Exception("Format de temp de culture " + tempsDeCulture + " incorrect (nombre seulement)");
	                }
					GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
					synchronized (jardinUpdate)
	                {
						jardinUpdate.getGestionPlante().ajouterPlante(nomPlante, nbJour);
	                }
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
	                dispatcher.forward(request, response);
					
					
			}
			catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajouterPlante.jsp");
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
