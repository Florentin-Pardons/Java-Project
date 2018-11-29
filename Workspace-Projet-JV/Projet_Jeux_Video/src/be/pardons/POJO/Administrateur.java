package be.pardons.POJO;

import java.util.List;

import be.pardons.DAO.AdministrateurDAO;
import be.pardons.DAO.PersonneDAO;

public class Administrateur extends Personne {
	
	//Constructeur
	public Administrateur(){}
	
	public Administrateur(String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(pseudo, mp, nom, prenom, age, adresse);
	}
	
	public Administrateur(int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		super(id, pseudo, mp, nom, prenom, age, adresse);
	}
	
	//Verification Connexion
	public static Administrateur Verif(String pseudo, String mp)
	{
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.verif(pseudo, mp);
	}
	
	//Creer
	public boolean Creer() {
		PersonneDAO persDao = new PersonneDAO();
		persDao.create(this);
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.create(this);
	}
	
	//Delete
	public boolean Delete() {
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.delete(this);
	}
	
	//Creation de la liste
	public static List<Administrateur> List()
	{
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.list();
	}
	
	// Tostring
	@Override
	public String toString() {
		return "Pseudo : " + super.GetPseudo() + ", Nom : " + super.GetNom() + ", Prenom : " + super.GetPrenom();
	}
	
	//Message
	public String message() {
		return super.toString();
	}
}