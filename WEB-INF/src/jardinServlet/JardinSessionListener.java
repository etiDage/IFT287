package jardinServlet;

import javax.servlet.http.*;

import JardinCollectif.GestionJardin;

import java.sql.*;

public class JardinSessionListener  implements HttpSessionListener{
	
	 public void sessionCreated(HttpSessionEvent se)
	    {
	    }
	 
	 public void sessionDestroyed(HttpSessionEvent se)
	    {
	        System.out.println("Session détruite pour l'utilisateur " + se.getSession().getAttribute("userID"));
	        
	        GestionJardin jardinInterogation = (GestionJardin)se.getSession().getAttribute("jardinInterrogation");
	        if (jardinInterogation != null)
	        {
	            try
	            {
	                System.out.println("Fermeture de la connexion d'interrogation...");
	                jardinInterogation.fermer();
	            }
	            catch (SQLException e)
	            {
	                System.out.println("Impossible de fermer la connexion");
	                e.printStackTrace();
	            }
	        }
	        else
	        {
	            System.out.println("Aucun gestionnaire d'interrogation n'avait encore été créé.");
	        }
	        
	        GestionJardin jardinUpdate = (GestionJardin)se.getSession().getAttribute("jardinUpdate");
	        if (jardinUpdate != null)
	        {
	            try
	            {
	                System.out.println("Fermeture de la connexion de mise à jour...");
	                jardinUpdate.fermer();
	            }
	            catch (SQLException e)
	            {
	                System.out.println("Impossible de fermer la connexion");
	                e.printStackTrace();
	            }
	        }
	        else
	        {
	            System.out.println("Aucun gestionnaire de mise à jour n'avait encore été créé.");
	        }
	    }

}
