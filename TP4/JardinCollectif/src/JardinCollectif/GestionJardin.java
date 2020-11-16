package JardinCollectif;

import java.sql.SQLException;

public class GestionJardin {
	private Connexion cx;
	private TableLots tableLots;
	private TableMembres tableMembres;
	private TablePlantes tablePlantes;
	private TablePlants tablePlants;
	private GestionLot gestionLot;
	private GestionInterrogation gestionInterrogation;
	private GestionMembre gestionMembre;
	private GestionPlante gestionPlante;
	private GestionDemandeAssignation gestionDemandeAssignation;
	private GestionPlants gestionPlants;
	
	public GestionJardin(String serveur, String bd, String user, String pass) throws IFT287Exception, SQLException 
	{
		cx = new Connexion(serveur, bd, user, pass);
		tableLots = new TableLots(cx);
		tableMembres = new TableMembres(cx);
		tablePlantes = new TablePlantes(cx);
		tablePlants = new TablePlants(cx);
		setGestionLot(new GestionLot(cx, tableLots, tableMembres, tablePlants));
		setGestionInterrogation(new GestionInterrogation(cx, tableMembres, tableLots, tablePlantes, tablePlants));
		setGestionMembre(new GestionMembre(cx, tableMembres, tableLots));
		setGestionPlante(new GestionPlante(cx, tablePlantes, tablePlants));
		setGestionDemandeAssignation(new GestionDemandeAssignation(cx, tableMembres, tableLots));
		setGestionPlants(new GestionPlants(cx,tablePlants, tableLots, tablePlantes));
	}
	
    public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }

	
	public GestionLot getGestionLot()
	{
		return gestionLot;
	}
	
	private void setGestionLot(GestionLot gestionLot)
	{
		this.gestionLot = gestionLot;
	}
	
	public GestionInterrogation getGestionInterrogation()
	{
		return gestionInterrogation;
	}
	
	private void setGestionInterrogation(GestionInterrogation gestionInterrogation)
	{
		this.gestionInterrogation = gestionInterrogation;
	}

	public GestionPlante getGestionPlante() {
		return gestionPlante;
	}

	private void setGestionPlante(GestionPlante gestionPlante) {
		this.gestionPlante = gestionPlante;
	}

	public GestionMembre getGestionMembre() {
		return gestionMembre;
	}

	private void setGestionMembre(GestionMembre gestionMembre) {
		this.gestionMembre = gestionMembre;
	}

	public GestionDemandeAssignation getGestionDemandeAssignation() {
		return gestionDemandeAssignation;
	}

	private void setGestionDemandeAssignation(GestionDemandeAssignation gestionDemandeAssignation) {
		this.gestionDemandeAssignation = gestionDemandeAssignation;
	}

	public GestionPlants getGestionPlants() {
		return gestionPlants;
	}

	private void setGestionPlants(GestionPlants gestionPlants) {
		this.gestionPlants = gestionPlants;
	}
	
}