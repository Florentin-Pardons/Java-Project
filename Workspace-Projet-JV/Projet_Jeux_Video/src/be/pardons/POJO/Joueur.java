package be.pardons.POJO;

import java.util.ArrayList;
import java.util.List;
import be.pardons.DAO.JoueurDAO;
import be.pardons.DAO.PersonneDAO;

public class Joueur extends Personne {

	//Variable
	private double solde;
	private List<Ex_Jeu> listExJeu;
	private List<Reservation> listRes;
	private List<Pret> listPret;
	
	//private Date date_enr;
	//private boolean rayer;
	
	//Setteur	
	public void SetSolde(double solde) {
		this.solde = solde;
	}/*
	public void SetDate_enr(Date date_enr) {
		this.date_enr = date_enr;
	}
	public void SetRayer(boolean rayer) {
		this.rayer = rayer;
	}*/
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
	public double GetSolde() {
		return this.solde;
	}/*
	public Date GetDate_enr() {
		return this.date_enr;
	}
	public boolean GetRayer() {
		return this.rayer;
	}*/
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
		this.solde = 5;/*
		this.date_enr = new Date();
		this.rayer = false;*/
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	public Joueur(double solde, int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(id, pseudo, mp, nom, prenom, age, adresse);
		this.solde = solde;/*
		this.date_enr = date_enr;
		this.rayer = rayer;*/
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	public Joueur(int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(id, pseudo, mp, nom, prenom, age, adresse);
		this.solde = 5.0;/*
		this.date_enr = null;
		this.rayer = false;*/
		this.listExJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	//Ajouter un jeu
	/*
	public void AddExJeu(Jeu jeu, boolean dispo)
	{
		listExJeu.add(new Ex_Jeu(jeu, dispo));
	}*/
	/*
	//Ajouter une reservation
	public void AddRes(Jeu jeu)
	{
		listRes.add(new Reservation(jeu));
	}*/
	
	//Ajouter un pret
	/*public void AddPret(int id, Ex_Jeu exjeu, Date deb, Date )
	{
		listPret.add(new Pret(datefin, exjeu));
	}*/
	
	//Verification Connexion
	public static Joueur Verif(String pseudo, String mp)
	{
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.verif(pseudo, mp);
	}
	
	//Creation de la liste
	public static List<Joueur> List()
	{
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.list();
	}
	
	public boolean Creer() {
		PersonneDAO persDao = new PersonneDAO();
		persDao.create(this);
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.create(this);
	}
	
	public boolean Delete() {
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.delete(this);
	}
	
	public boolean Update() {
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.update(this);
	}
	
	public boolean UpdateSolde() {
		JoueurDAO joueurDao = new JoueurDAO();
		return joueurDao.updatesolde(this);
	}
	
	// Tostring
	@Override
	public String toString() {
		return super.toString() + " " + solde;
	}
}