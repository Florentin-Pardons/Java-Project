package be.pardons.POJO;

import java.util.List;
import be.pardons.DAO.AdministrateurDAO;
import be.pardons.DAO.PersonneDAO;

public class Administrateur extends Personne {
	
	public Administrateur()
	{}
	
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

	//Creation de la liste
	public static List<Administrateur> List()
	{
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.list();
	}
	
	public boolean Creer() {
		PersonneDAO persDao = new PersonneDAO();
		persDao.create(this);
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.create(this);
	}
	
	public boolean Delete() {
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.delete(this);
	}
	
	public boolean Update() {
		AdministrateurDAO adminDao = new AdministrateurDAO();
		return adminDao.update(this);
	}
}