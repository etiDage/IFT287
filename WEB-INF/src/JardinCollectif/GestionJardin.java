package JardinCollectif;

import java.sql.SQLException;

public class GestionJardin {
	private Connexion cx;
	private TableAssignations tableAssignations;
	private TableDemandes tableDemandes;
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
		tableAssignations = new TableAssignations(cx);
		tableDemandes = new TableDemandes(cx);
		tableLots = new TableLots(cx);
		tableMembres = new TableMembres(cx);
		tablePlantes = new TablePlantes(cx);
		tablePlants = new TablePlants(cx);
		setGestionLot(new GestionLot(cx, tableLots, tableAssignations, tablePlants, tableDemandes));
		setGestionInterrogation(new GestionInterrogation(cx));
		setGestionMembre(new GestionMembre(cx, tableMembres, tableAssignations, tableDemandes));
		setGestionPlante(new GestionPlante(cx, tablePlantes, tablePlants));
		setGestionDemandeAssignation(new GestionDemandeAssignation(cx, tableDemandes, tableAssignations, tableMembres, tableLots));
		setGestionPlants(new GestionPlants(cx,tablePlants, tableLots, tablePlantes, tableAssignations));
	}
	
    public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }
    
    
    public Connexion getConnexion()
    {
    	return cx;
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
