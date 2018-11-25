package be.pardons.POJO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import be.pardons.DAO.PretDAO;

public class Pret {

	//Variable
	private int id;
	private Date datedeb;
	private Date datefin;
	private Ex_Jeu exjeu;
	
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}
	public void SetDateDeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public void SetDateFin(Date datefin) {
		this.datefin = datefin;
	}
	public void SetEx_Jeu(Ex_Jeu exjeu) {
		this.exjeu = exjeu;
	}
	
	//Getteur
	public int GetId() {
		return this.id;
	}
	public Date GetDateDeb() {
		return this.datedeb;
	}
	public Date GetDateFin() {
		return this.datefin;
	}
	public Ex_Jeu GetEx_Jeu() {
		return this.exjeu;
	}
	
	//Constructeur
	public Pret(int id, Ex_Jeu exjeu, Date datedeb, Date datefin)
	{
		this.id = id;
		this.exjeu = exjeu;
		this.datedeb = datedeb;
		this.datefin = datefin;
	}
	
	public Pret(Ex_Jeu exjeu)
	{
		this.exjeu = exjeu;
		this.datedeb = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(datedeb);
		c.add(Calendar.DATE, 7);
		
		this.datefin = c.getTime();
		
	}
	
	//Methode
	
	//Creation de la liste
	public static List<Pret> List(Joueur joueur)
	{
		PretDAO pretDao = new PretDAO();
		return pretDao.list(joueur);
	}
	
	public boolean Creer(Joueur joueur) {
		PretDAO pretDao = new PretDAO();
		return pretDao.create(this, joueur);
	}
	
	public boolean Delete() {
		PretDAO pretDao = new PretDAO();
		return pretDao.delete(this);
	}
	
	public boolean Update() {
		PretDAO pretDao = new PretDAO();
		return pretDao.update(this);
	}
	
	public boolean UpdateDate() {
		PretDAO pretDao = new PretDAO();
		return pretDao.updatedate(this);
	}
		
	// Tostring
	@Override
	public String toString() {
		return id + " " + datedeb + " " + datefin + " " + exjeu ;
	}
}