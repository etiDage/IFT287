package JardinCollectif;
import java.sql.*;

public class TuplePlante {
	private String nomPlante;
	private int tempCulture;
	
	public TuplePlante()
    {
    }
	
	public TuplePlante(String nomPlante, int tempCulture)
    {
        this.setNomPlante(nomPlante);
        this.setTmpCulture(tempCulture);
    }

	private void setTmpCulture(int tempCulture) {
		this.tempCulture= tempCulture;
		
	}
	public int getTmpCulture()
    {
        return tempCulture;
    }

	private void setNomPlante(String nomPlante) {
		this.nomPlante= nomPlante;
		
	}
	public String getNomPlante()
    {
        return nomPlante;
    }

}
