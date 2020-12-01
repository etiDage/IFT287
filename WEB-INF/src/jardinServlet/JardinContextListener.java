package jardinServlet;

import java.util.Enumeration;

import javax.servlet.*;

public class JardinContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("Contexte Jardin WEB d�marr� : " + sce.getServletContext().getServletContextName());
        System.out.println("Voici les param�tres du contexte tels que d�finis dans web.xml");
        Enumeration<String> initParams = sce.getServletContext().getInitParameterNames();
        while (initParams.hasMoreElements())
        {
            String name = (String) initParams.nextElement();
            System.out.println(name + ":" + sce.getServletContext().getInitParameter(name));
        }
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("Le contexte de l'application GestionJardin vient d'�tre d�truit.");
    }

}
