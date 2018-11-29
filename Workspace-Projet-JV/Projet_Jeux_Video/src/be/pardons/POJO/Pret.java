package be.pardons.POJO;

import java.text.SimpleDateFormat;
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
	//Creer
	public boolean Creer(Joueur joueur) {
		PretDAO pretDao = new PretDAO();
		return pretDao.create(this, joueur);
	}
	
	//Delete
	public boolean Delete() {
		PretDAO pretDao = new PretDAO();
		return pretDao.delete(this);
	}
		
	//UpdateDate
	public boolean UpdateDate() {
		PretDAO pretDao = new PretDAO();
		return pretDao.update(this);
	}
	
	//Creation de la liste
	public static List<Pret> List(Joueur joueur)
	{
		PretDAO pretDao = new PretDAO();
		return pretDao.list(joueur);
	}
	
	//Delete le pret si ajd > date fin
	public static void Verif()
	{
		PretDAO pretDao = new PretDAO();
		Date now = new Date();
		
		for(Pret p : pretDao.list())
		{
			if(now.compareTo(p.GetDateFin()) > 0 )
			{
				p.Delete();
				p.GetEx_Jeu().SetDispo(false);
				p.GetEx_Jeu().Update();
			}
		}
	}
	
	// Tostring
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return "Nom Jeu : " + exjeu.GetJeu().GetNom() + ", Date Deb : " + formatter.format(datedeb) + ", Date fin : " + formatter.format(datefin) + ", Id Exempl : " + exjeu.GetId() ;
	}
}