package be.pardons.POJO;

import java.util.ArrayList;
import java.util.List;

import be.pardons.DAO.Ex_JeuDAO;

public class Ex_Jeu {

	//Variable
	private int id;
	private Jeu jeu;
	private boolean dispo;
		
	//Setteur
	public void SetId(int id) {
		this.id = id;
	}	
	
	public void SetJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void SetDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	//Getteur
	public int GetId() {
		return this.id;
	}
	
	public Jeu GetJeu() {
		return this.jeu;
	}
	
	public boolean GetDispo() {
		return this.dispo;
	}
	
	//Constructeur
	public Ex_Jeu(Jeu jeu, boolean dispo)
	{
		this.jeu = jeu;
		this.dispo = dispo;
	}
	
	public Ex_Jeu(int id, Jeu jeu, boolean dispo)
	{
		this.id = id;
		this.jeu = jeu;
		this.dispo = dispo;
	}
	
	//Methode
	//Creer
	public boolean Creer(Joueur joueur) {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.create(this, joueur);
	}
	
	//Delete
	public boolean Delete() {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.delete(this);
	}
	
	//Update
	public boolean Update() {
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.update(this);
	}
	
	//Creation de la liste
	public static List<Ex_Jeu> List(Joueur joueur)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.list(joueur);
	}
	
	//Creation de la liste dispo
	public static List<Ex_Jeu> ListDispo()
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.listdispo();
	}
	
	//Preteur
	public static Joueur Preteur(Ex_Jeu ex_jeu)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		return ex_jeuDao.GetPreteur(ex_jeu);
	}
	
	//VerifDispoJeu
	public static Ex_Jeu Dispo(Jeu jeu)
	{
		Ex_JeuDAO ex_jeuDao = new Ex_JeuDAO();
		
		//Recherche dans la liste
		for (Ex_Jeu customer : ex_jeuDao.listdispo()) {
	        if (customer.GetJeu().GetId() == jeu.GetId()) {
	            return customer;
	        }
	    }
		return null;
	}
	
	
	//Verifie les reservations
	public boolean VerifPreteur(Joueur joueur)
	{
		List<Reservation> listResPot = new ArrayList<Reservation>();
		Joueur demandeur = new Joueur();
		
		//Liste de reservtion potentiel
		for (Reservation reserv : Reservation.List()) 
		{
			System.out.println(reserv);
	        if (reserv.GetJeu().GetId() == this.GetJeu().GetId()) 
	        {
	        	//listResPot.add(reserv);
	        	demandeur = Reservation.Demandeur(reserv);
	        	
				if(demandeur.GetSolde() >= this.GetJeu().GetTarif()) //verifie le solde
				{
					listResPot.add(reserv);					
				}
	        }
	    }
		
		if(!listResPot.isEmpty())
		{
			Reservation resfinal = listResPot.get(0);
			
			for (Reservation reserv : listResPot) 
			{
				//Si les 2 res sont le meme
				if(resfinal.GetDateRes().compareTo(reserv.GetDateRes()) == 0)
				{
					if(Reservation.Demandeur(reserv).GetSolde() == Reservation.Demandeur(resfinal).GetSolde()) //Solde le meme
					{
						if(Reservation.Demandeur(reserv).GetAge() == Reservation.Demandeur(resfinal).GetAge()) //Age le meme
						{
							//Choix aleatoire
							if(Math.random() == 1)
								resfinal = reserv;
						}
						else if(Reservation.Demandeur(reserv).GetAge() > Reservation.Demandeur(resfinal).GetAge()) //Age plus grand
							resfinal = reserv;
					}
					else if (Reservation.Demandeur(reserv).GetSolde() > Reservation.Demandeur(resfinal).GetSolde()) //Solde plus grand
						resfinal = reserv;
				}
				else if(reserv.GetDateRes().compareTo(resfinal.GetDateRes()) > 0) //Si la premiere est plus grand
					resfinal = reserv;
			}
			
			demandeur = Reservation.Demandeur(resfinal); //
			
			if(resfinal != null)
			{
				Pret pret = new Pret(this);
				if(pret.Creer(demandeur) == true) //creer pret
				{
					this.SetDispo(false);
					this.Update(); //update dispo
					
					//Solde du joueur preteur
					int newsolde = joueur.GetSolde() + this.GetJeu().GetTarif();
					joueur.SetSolde(newsolde);
					joueur.UpdateSolde();
					
					//Solde du receveur
					newsolde = demandeur.GetSolde() - this.GetJeu().GetTarif();
					demandeur.SetSolde(newsolde);
					demandeur.UpdateSolde();
					
					resfinal.Delete(); //Delete la reservation du demandeur
					
					return true;
				}	
			}
			else
				System.out.println("Erreur System");
		}
		else
			System.out.println("Pas de reservation dispo");
		
		return false;
	}
	
	// Tostring
	@Override
	public String toString() {
		return "Id : "+ id + ", Nom : " + jeu.GetNom() + ", Dispo : " + dispo;
	}
}