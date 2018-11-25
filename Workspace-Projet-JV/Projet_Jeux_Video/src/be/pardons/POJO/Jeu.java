package be.pardons.POJO;

import java.util.Date;
import be.pardons.DAO.JeuDAO;
import java.util.List;

public class Jeu {

	//Variable
	private int id;
	private String nom;
	private int tarif;
	private Date datesortie;
	private String developpeur;
	private String editeur;
	
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetNom(String nom) {
		this.nom = nom;
	}
	
	public void SetTarif(int tarif) {
		this.tarif = tarif;
	}
	
	public void SetDatesortie(Date datesortie) {
		this.datesortie = datesortie;
	}
	
	public void SetDeveloppeur(String developpeur) {
		this.developpeur = developpeur;
	}
	
	public void SetEditeur(String editeur) {
		this.editeur = editeur;
	}
	
	//Getteur
	public int GetId() {
		return this.id;
	}
	
	public String GetNom() {
		return this.nom;
	}
	
	public int GetTarif() {
		return this.tarif;
	}
	
	
	public Date GetDatesortie() {
		return this.datesortie;
	}
	
	public String GetDeveloppeur() {
		return this.developpeur;
	}
	
	public String GetEditeur() {
		return this.editeur;
	}
	
	public Jeu()
	{}
	
	//Constructeur
	public Jeu(String nom, int tarif, Date datesortie, String developpeur, String editeur)
	{
		this.nom = nom;
		this.tarif = tarif;
		this.datesortie = datesortie;
		this.developpeur = developpeur;
		this.editeur = editeur;
	}
	
	public Jeu(int id, String nom, int tarif, Date datesortie, String developpeur, String editeur)
	{
		this.id = id;
		this.nom = nom;
		this.tarif = tarif;
		this.datesortie = datesortie;
		this.developpeur = developpeur;
		this.editeur = editeur;
	}
	
	//Methode
	public boolean Creer() {
		JeuDAO jeu = new JeuDAO();
		return jeu.create(this);
	}
	
	public boolean Delete() {
		JeuDAO jeu = new JeuDAO();
		return jeu.delete(this);
	}
	
	public boolean Update() {
		JeuDAO jeu = new JeuDAO();
		return jeu.update(this);
	}
		
	//Creation de la liste
	public static List<Jeu> List()
	{
		JeuDAO jeuDao = new JeuDAO();
		return jeuDao.list();
	}
	
	// Tostring
	@Override
	public String toString() {
		return "Nom : " + nom + " " + tarif + " " + datesortie + " " + developpeur + " " + editeur;
	}
}