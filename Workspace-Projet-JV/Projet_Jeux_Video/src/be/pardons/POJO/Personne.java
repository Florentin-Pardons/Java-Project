package be.pardons.POJO;

import be.pardons.DAO.PersonneDAO;

public class Personne {

	//Variable
	private int id;
	private String pseudo;
	private String mp;
	private String nom;
	private String prenom;
	private Integer age;
	private String adresse;
	
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public void SetMp(String mp) {
		this.nom = mp;
	}
	
	public void SetNom(String nom) {
		this.nom = nom;
	}
	
	public void SetPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void SetAge(Integer age) {
		this.age = age;
	}
	
	public void SetAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	//Getteur
	public int GetId() {
		return this.id;
	}
	
	public String GetPseudo() {
		return this.pseudo;
	}
	
	public String GetMp() {
		return this.mp;
	}
	
	public String GetNom() {
		return this.nom;
	}
	
	public String GetPrenom() {
		return this.prenom;
	}
	
	public Integer GetAge() {
		return this.age;
	}
	
	
	public String GetAdresse() {
		return this.adresse;
	}
	
	//Constructeur
	public Personne()
	{}
	
	public Personne(String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		this.pseudo = pseudo;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;		
	}
	
	public Personne(int id, String pseudo, String mp, String nom, String prenom, Integer age, String adresse)
	{
		this.id = id;
		this.pseudo = pseudo;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;		
	}
	
	// Tostring
	public String toString() {
		return pseudo + " " + mp + " " + nom + " " + prenom + " " + age + " " + adresse;
	}
	
	public boolean Creer() {
		PersonneDAO persDao = new PersonneDAO();
		return persDao.create(this);
	}
	
	public boolean Delete() {
		PersonneDAO persDao = new PersonneDAO();
		return persDao.delete(this);
	}
	
	public boolean Update() {
		PersonneDAO persDao = new PersonneDAO();
		return persDao.update(this);
	}
	
	public boolean Verif()
	{
		PersonneDAO persDao = new PersonneDAO();
		return persDao.verif(this);
	}
	
	public static Personne Verif(String pseudo, String mp)
	{
		PersonneDAO persDao = new PersonneDAO();
		return persDao.verif(pseudo, mp);
	}
}
