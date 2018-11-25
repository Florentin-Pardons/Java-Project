package be.pardons.POJO;

import java.util.Date;
import java.util.List;
import be.pardons.DAO.ReservationDAO;

public class Reservation {

	//Variable
	private int id;
	private Date dateres ;
	private Jeu jeu;
	
	//Getteur
	public int GetId() {
		return id;
	}
	public Date GetDateRes() {
		return dateres;
	}
	public Jeu GetJeu() {
		return jeu;
	}
	
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}
	public void SetDateRes(Date dateres) {
		this.dateres = dateres;
	}
	public void SetJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	//Constructeur
	public Reservation(int id, Date dateres, Jeu jeu)
	{
		this.id = id;
		this.dateres = dateres;
		this.jeu = jeu;
	}
	
	public Reservation(Jeu jeu)
	{
		this.dateres = new Date();
		this.jeu = jeu;
	}
	
	//Methode
	
	//Creation de la liste
	public static List<Reservation> List(Joueur joueur)
	{
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.list(joueur);
	}
	
	public boolean Creer(Joueur joueur) {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.create(this, joueur);
	}
	
	public boolean Delete() {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.delete(this);
	}
	
	public boolean Update() {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.update(this);
	}
	
	//Verifie les reservations
	public boolean Verif(Joueur joueur)
	{
		if(joueur.GetSolde() > this.GetJeu().GetTarif()) //verifie le solde
		{
			Ex_Jeu exjeu = Ex_Jeu.Dispo(this.GetJeu()); //si il y a un ex dispo
			
			if(exjeu != null)
			{
				Pret pret = new Pret(exjeu);
				if(pret.Creer(joueur) == true) //creer pret
				{
					exjeu.SetDispo(false);
					exjeu.Update(); //update dispo
					
					//solde
					double newsolde = joueur.GetSolde() - this.GetJeu().GetTarif();
					joueur.SetSolde(newsolde);
					joueur.UpdateSolde();
					
					return true;
				}	
			}
		}
		return false;
	}
		
	// Tostring
	@Override
	public String toString() {
		return id + " " + dateres + " " + jeu;
	}	
}