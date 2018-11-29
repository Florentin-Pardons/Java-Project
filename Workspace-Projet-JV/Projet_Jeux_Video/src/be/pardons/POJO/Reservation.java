package be.pardons.POJO;

import java.text.SimpleDateFormat;
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
	//Creer
	public boolean Creer(Joueur joueur) {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.create(this, joueur);
	}
	
	//Delete
	public boolean Delete() {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.delete(this);
	}
	
	//Update
	public boolean Update() {
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.update(this);
	}
	
	//Creation de la liste reservation du joueur
	public static List<Reservation> List(Joueur joueur)
	{
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.list(joueur);
	}
	
	//Liste de tt les reservations
	public static List<Reservation> List()
	{
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.list();
	}
		
	//Receveur
	public static Joueur Demandeur(Reservation res)
	{
		ReservationDAO reservationDao = new ReservationDAO();
		return reservationDao.getDemandeur(res);
	}
		
	//Verifie les reservations
	public boolean VerifJoueur(Joueur joueur)
	{
		if(joueur.GetSolde() > this.GetJeu().GetTarif()) //verifie le solde du demandeur
		{
			Ex_Jeu exjeu = Ex_Jeu.Dispo(this.GetJeu()); //si il y a un ex dispo
			
			//////
			/*
			List<Ex_Jeu> listExPot = new ArrayList<Ex_Jeu>();
			Joueur Preteur = new Joueur();
			
			//Liste d'Ex potentiel
			for (Ex_Jeu ex : Ex_Jeu.ListDispo()) 
			{
		        if (ex.GetJeu().GetId() == this.GetJeu().GetId()) 
		        {        	
		        	listExPot.add(ex);
		        }
		    }
			
			if(!listExPot.isEmpty())
			{
				Ex_Jeu exfinal = listExPot.get(0);
				
				for (Ex_Jeu ex : listExPot) 
				{
					//Si les 2 res sont le meme
					if( Ex_Jeu.Preteur(ex).GetSolde() ==  Ex_Jeu.Preteur(exfinal).GetSolde()) //Solde le meme
					{
						if( Ex_Jeu.Preteur(ex).GetAge() ==  Ex_Jeu.Preteur(exfinal).GetAge()) //Age le meme
						{
							//Choix aleatoire
							if(Math.random() == 1)
								exfinal = ex;
						}
						else if( Ex_Jeu.Preteur(ex).GetAge() >  Ex_Jeu.Preteur(exfinal).GetAge()) //Age plus grand
							exfinal = ex;
					}
					else if ( Ex_Jeu.Preteur(ex).GetSolde() >  Ex_Jeu.Preteur(exfinal).GetSolde()) //Solde plus grand
						exfinal = ex;
				}
				}
				
				Preteur = Ex_Jeu.Preteur(exfinal); //
			/////
			*/
			
			
			if(exjeu != null)
			{
				Pret pret = new Pret(exjeu);
				if(pret.Creer(joueur) == true) //creer pret
				{
					exjeu.SetDispo(false);
					exjeu.Update(); //update dispo
					
					//Solde du joueur
					int newsolde = joueur.GetSolde() - this.GetJeu().GetTarif();
					joueur.SetSolde(newsolde);
					joueur.UpdateSolde();
					
					//Solde du preteur
					Joueur preteur = Ex_Jeu.Preteur(exjeu);
					newsolde = preteur.GetSolde() + this.GetJeu().GetTarif();
					preteur.SetSolde(newsolde);
					preteur.UpdateSolde();
					
					return true;
				}
				else
					System.out.println("Erreur lors de la creation du pret");
			}
			else
				System.out.println("Aucun exemplaire trouvé");
		}
		return false;
	}
		
	// Tostring
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return "Date res : " + formatter.format(dateres) + ", Nom : " + jeu.GetNom();
	}	
}