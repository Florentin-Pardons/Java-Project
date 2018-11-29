package be.pardons.POJO;

import java.util.ArrayList;
import java.util.List;
import be.pardons.DAO.JoueurDAO;
import be.pardons.DAO.PersonneDAO;

public class Joueur extends Personne {

	//Variable
	private int solde;
	private List<Ex_Jeu> listExJeu;
	private List<Reservation> listRes;
	private List<Pret> listPret;
	
	//Setteur	
	public void SetSolde(int solde) {
		this.solde = solde;
	}
	
	public void SetListJeu(List<Ex_Jeu> listExJeu) {
		this.listExJeu = listExJeu;
	}
	
	public void SetListRes(List<Reservation> listRes) {
		this.listRes = listRes;
	}
	
	public void SetListPret(List<Pret> listPret) {
		this.listPret = listPret;
	}

	//Getteur
	public int GetSolde() {
		return this.solde;
	}
	
	public List<Ex_Jeu> GetListJeu() {
		return listExJeu;
	}
	
	public List<Reservation> GetListRes() {
		return listRes;
	}
	public List<Pret> GetListPret() {
		return listPret;
	}
	
	//Constructeur
	public Joueur()
	{
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}

	public Joueur(String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(pseudo, mp, nom, prenom, age, adresse);
		this.solde = 10;
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	public Joueur(int solde, int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(id, pseudo, mp, nom, prenom, age, adresse);
		this.solde = solde;
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	public Joueur(int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(id, pseudo, mp, nom, prenom, age, adresse);
		this.solde = 10;
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	//Verification Connexion
	public static Joueur Verif(String pseudo, String mp)
	{
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.verif(pseudo, mp);
	}
	
	//Creer
	public boolean Creer() {
		//Creer la personne
		PersonneDAO persDao = new PersonneDAO();		
		if(persDao.create(this) == true)
		{		
			JoueurDAO joueurDao = new JoueurDAO();
			if(joueurDao.create(this)== true)
				return true;
		}
		
		return false;
	}
	
	//Delete
	public boolean Delete() {
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.delete(this);
	}
	
	//Update Solde
	public boolean UpdateSolde() {
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.updatesolde(this);
	}
	
	//Creation de la liste
	public static List<Joueur> List()
	{
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.list();
	}
	
	// Tostring
	@Override
	public String toString() {
		return "Pseudo : " + super.GetPseudo() + ", Nom : " + super.GetNom() + ", Prenom : " + super.GetPrenom() + ", Solde : " + solde;
	}
	
	//Message
	public String message() {
		return super.toString() + ", Solde : " + solde;
	}
}