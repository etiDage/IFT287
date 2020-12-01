package jardinServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class inscription extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		System.out.println("Servlet Inscription : POST");
		if(!JardinHelper.peutProcederLogin(getServletContext(), request, response))
		{
			return;
		}
		
		System.out.println("Servlet Inscription : POST dispatch vers creerCompte.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creerCompte.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Servlet Inscription : GET");
		
		if(JardinHelper.peutProceder(getServletContext(), request, response))
		{
			System.out.println("Servlet Inscription : GET dispatch vers creerCompte.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/creerCompte.jsp");
			dispatcher.forward(request, response);
		}
	}
}
