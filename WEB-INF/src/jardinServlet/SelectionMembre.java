package jardinServlet;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import JardinCollectif.IFT287Exception;
import JardinCollectif.GestionJardin;

public class SelectionMembre  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
		 	try {
		 		HttpSession session= request.getSession();
		 		Integer etat =(Integer) session.getAttribute("etat");
		 		if(etat == null)
		 		{
		 			RequestDispatcher dispatcher= request.getRequestDispatcher("/login.jsp");
		 			dispatcher.forward(request, response);
		 		}
		 		else 
		 		{
		 			session.setAttribute("userId",null);
		 			session.setAttribute("etat",new Integer(JardinConstantes.CONNECTE));
		 			String userIdParam = request.getParameter("userId");
		 			request.setAttribute("userId", userIdParam);
		 			if(userIdParam == null)
	                {
	                    throw new IFT287Exception("Format de no Membre " + userIdParam + " incorrect.");
	                }
					GestionJardin jardinUpdate = (GestionJardin) request.getSession().getAttribute("jardinUpdate");
					synchronized (jardinUpdate)
	                {
						if(!jardinUpdate.getGestionMembre().exist(userIdParam))
							throw new IFT287Exception("Ce id Membre n'existe pas");
					}
					session.setAttribute("userId", userIdParam);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
	                dispatcher.forward(request, response);
		 		}
		 		
		 	}
		 	catch(IFT287Exception e)
	        {
		 		List<String> listeMessageErreur = new LinkedList<String>();
	            listeMessageErreur.add(e.toString());
	            request.setAttribute("listeMessageErreur", listeMessageErreur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
	            dispatcher.forward(request, response);
	        }
		 	catch(Exception e)
	        {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
	        }

	    }
	 
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
	        doGet(request, response);
	    }
}
