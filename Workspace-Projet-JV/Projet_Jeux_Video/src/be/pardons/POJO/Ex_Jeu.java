package be.pardons.POJO;

import java.util.List;

import be.pardons.DAO.Ex_JeuDAO;

public class Ex_Jeu {

	//Variable
	private int id;
	private Jeu jeu;
	private boolean dispo;
		
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}	
	
	public void SetJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void SetDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	//Getteur
	public int GetId() {
		return this.id;
	}
	
	public Jeu GetJeu() {
		return this.jeu;
	}
	
	public boolean GetDispo() {
		return this.dispo;
	}
	
	//Constructeur
	public Ex_Jeu(Jeu jeu, boolean dispo)
	{
		this.jeu = jeu;
		this.dispo = dispo;
	}
	
	public Ex_Jeu(int id, Jeu jeu, boolean dispo)
	{
		this.id = id;
		this.jeu = jeu;
		this.dispo = dispo;
	}
	
	//Methode
	public boolean Creer(Joueur joueur) {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.create(this, joueur);
	}
	
	public boolean Delete() {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.delete(this);
	}
	
	public boolean Update() {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.update(this);
	}
	
	//Creation de la liste
	public static List<Ex_Jeu> List(Joueur joueur)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.list(joueur);
	}
	
	//Creation de la liste dispo
	public static List<Ex_Jeu> ListDispo()
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.listdispo();
	}
	
	//Preteur
	public static Joueur Preteur(Ex_Jeu ex_jeu)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.GetPreteur(ex_jeu);
	}
	
	//VerifDispoJeu
	public static Ex_Jeu Dispo(Jeu jeu)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		
		//Recherche dans la liste
		for (Ex_Jeu customer : ex_jeuDao.listdispo()) {
	        if (customer.GetJeu().GetId() == jeu.GetId()) {
	            return customer;
	        }
	    }
		return null;
	}
	
	// Tostring
	@Override
	public String toString() {
		return jeu + " " + dispo;
	}
}