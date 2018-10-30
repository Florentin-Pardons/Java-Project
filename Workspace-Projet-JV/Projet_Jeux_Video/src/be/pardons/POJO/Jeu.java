package be.pardons.POJO;

import java.util.Date;

public class Jeu {

	//Variable
	private String nom;
	private double tarif;
	private Date datesortie;
	private String developpeur;
	private String editeur;
	
	//Setteur	
	public void SetNom(String nom) {
		this.nom = nom;
	}
	
	public void SetTarif(double tarif) {
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
	public String GetNom() {
		return this.nom;
	}
	
	public double GetTarif() {
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
	
	//Constructeur
	public Jeu(String nom, double tarif, Date datesortie, String developpeur, String editeur)
	{
		this.nom = nom;
		this.tarif = tarif;
		this.datesortie = datesortie;
		this.developpeur = developpeur;
		this.editeur = editeur;
	}
	
	// Tostring
	@Override
	public String toString() {
		return nom + " " + tarif + " " + datesortie + " " + developpeur + " " + editeur;
	}
}