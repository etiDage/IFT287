package jardinServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


public class Logout extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getSession().invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
