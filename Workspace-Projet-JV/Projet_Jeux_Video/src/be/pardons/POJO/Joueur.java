package be.pardons.POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Joueur extends Personne {

	//Variable
	private double solde;
	private List<Ex_Jeu> listJeu;
	private List<Reservation> listRes;
	private List<Pret> listPret;
	
	private Date date_enr;
	private boolean rayer;
	
	//Setteur
	public void SetSolde(double solde) {
		this.solde = solde;
	}
	public void SetDate_enr(Date date_enr) {
		this.date_enr = date_enr;
	}
	public void SetRayer(boolean rayer) {
		this.rayer = rayer;
	}
	public void SetListJeu(List<Ex_Jeu> listJeu) {
		this.listJeu = listJeu;
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
	}
	public Date GetDate_enr() {
		return this.date_enr;
	}
	public boolean GetRayer() {
		return this.rayer;
	}
	public List<Ex_Jeu> GetListJeu() {
		return listJeu;
	}
	public List<Reservation> GetListRes() {
		return listRes;
	}
	public List<Pret> GetListPret() {
		return listPret;
	}
	
	//Constructeur
	public Joueur()
	{}

	public Joueur(double solde, Date date_enr, boolean rayer, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(pseudo, mp, nom, prenom, age, adresse);
		this.solde = solde;
		this.date_enr = date_enr;
		this.rayer = rayer;
		this.listJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	public Joueur(String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(pseudo, mp, nom, prenom, age, adresse);
		this.solde = 5.0;
		this.date_enr = null;
		this.rayer = false;
		this.listJeu = new ArrayList<Ex_Jeu>();
		this.listRes = new ArrayList<Reservation>();
		this.listPret = new ArrayList<Pret>();
	}
	
	//Ajouter un jeu
	public void AddExJeu(Jeu jeu, String ref, boolean dispo)
	{
		listJeu.add(new Ex_Jeu(jeu, ref, dispo));
	}
	
	//Ajouter une reservation
	public void AddRes(Jeu jeu)
	{
		listRes.add(new Reservation(jeu));
	}
	
	//Ajouter un pret
	public void AddPret(int datefin, Ex_Jeu exjeu)
	{
		listPret.add(new Pret(datefin, exjeu));
	}
	
	// Tostring
	@Override
	public String toString() {
		return super.toString() + " " + solde + " " + date_enr + " " + rayer;
	}
}