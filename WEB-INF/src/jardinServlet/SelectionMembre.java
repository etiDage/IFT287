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
		 			dispatcher.foward(request, response);
		 		}
		 		else 
		 		{
		 			session.setAttribute("userId",null);
		 			session.setAttribute("etat",new Integer(JardinConstantes.CONNECTE));
		 			String userIdParam = request.getParameter("idMembre");
		 			request.setAttribute("userId", userIdParam);
		 			
		 			int idMembre = -1;
		 			try {
		 				idMembre= Integer.parseInt(userIdParam);
		 				
		 				session.setAttribute("idMembre",userIdParam);
		 			}catch (NumberFormatException e)
	                {
	                    throw new IFT287Exception("Format de no Membre " + userIdParam + " incorrect.");
	                }
		 		}
		 		
		 	}
		 	catch(SQLException e)
	        {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
	        }
		 	catch(IFT287Exception e)
	        {
		 		List<String> listeMessageErreur = new LinkedList<String>();
	            listeMessageErreur.add(e.toString());
	            request.setAttribute("listeMessageErreur", listeMessageErreur);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/selectionMembre.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	 
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	    {
	        doGet(request, response);
	    }
}
