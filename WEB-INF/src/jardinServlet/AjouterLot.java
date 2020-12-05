package jardinServlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JardinCollectif.GestionJardin;
import JardinCollectif.IFT287Exception;


public class AjouterLot extends HttpServlet
{
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
		else
		{
			try 
			{
				String nomLot = request.getParameter("nomLot");
				request.setAttribute("nomLot", nomLot);
				
				
				String nbMaxMembre = request.getParameter("nbMaxMembre");
				int nbMax = -1;
				try
				{
					nbMax = Integer.parseInt(nbMaxMembre);
				}
				catch(NumberFormatException e)
				{
					throw new IFT287Exception("Format de nbMaxMembre incorrect (nombre seulement)");
				}
				GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
				synchronized(jardinUpdate)
				{
					jardinUpdate.getGestionLot().ajouterLot(nomLot, nbMax);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
				dispatcher.forward(request, response);
			}
			catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.toString());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ajouterLot.jsp");
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
