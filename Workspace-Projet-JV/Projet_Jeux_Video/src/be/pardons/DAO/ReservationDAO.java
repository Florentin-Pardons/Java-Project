package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Reservation;

public class ReservationDAO extends DAO<Reservation>{

	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public ReservationDAO(){
		super(connec);
	}
	
	//Creer
	public boolean create(Reservation reservation, Joueur joueur){		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			stmt = connec.createStatement();
			String insertion = "INSERT INTO Reservation( num_joueur_FK, num_jeu_FK, dateRes ) values ('" + joueur.GetId() + "', '" + reservation.GetJeu().GetId() + "', #"  + formatter.format(reservation.GetDateRes()) + "#);";
			System.out.println(insertion);
			int res1 = stmt.executeUpdate(insertion);

			if(res1 == 1) //Cree
			{
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Delete
	public boolean delete(Reservation reservation){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Reservation where num_res_PK = '" + reservation.GetId() + "';";
			int res = stmt.executeUpdate(del);
			
			if(res == 1) //del ok
			{
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Update
	public boolean update(Reservation reservation){		
		return false;
	}
	
	//List
	public List<Reservation> list(Joueur joueur){
		List<Reservation> list = new ArrayList<Reservation>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Jeu.*, Reservation.* FROM Jeu INNER JOIN Reservation ON Jeu.num_jeu_PK = Reservation.num_jeu_FK where Reservation.num_joueur_FK = " + joueur.GetId() + ";";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Reservation(res.getInt("Reservation.num_res_PK"), res.getDate("dateres"), new Jeu(res.getInt("Jeu.num_jeu_PK"), res.getString("Jeu.nom"), res.getInt("Jeu.tarif"), res.getDate("Jeu.datesortie"), res.getString("Jeu.developpeur"), res.getString("Jeu.editeur"))));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	//List All
	public List<Reservation> list(){
		List<Reservation> list = new ArrayList<Reservation>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Jeu.*, Reservation.* FROM Jeu INNER JOIN Reservation ON Jeu.num_jeu_PK = Reservation.num_jeu_FK ;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Reservation(res.getInt("Reservation.num_res_PK"), res.getDate("dateres"), new Jeu(res.getInt("Jeu.num_jeu_PK"), res.getString("Jeu.nom"), res.getInt("Jeu.tarif"), res.getDate("Jeu.datesortie"), res.getString("Jeu.developpeur"), res.getString("Jeu.editeur"))));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	//Recupérer le demandeur
	public Joueur getDemandeur(Reservation reserv) {
		Joueur joueur = new Joueur();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Joueur.*, Personne.*, Reservation.* FROM (Personne INNER JOIN Joueur ON Personne.num_pers_PK = Joueur.num_joueur_PK) INNER JOIN Reservation ON Joueur.num_joueur_PK = Reservation.num_joueur_FK where  Reservation.num_res_PK = " + reserv.GetId() + ";";
			res = stmt.executeQuery(result);
			
			if(res.next()) //verif pri
			{
				joueur.SetId(res.getInt("num_pers_PK"));
				joueur.SetPseudo(res.getString("pseudo"));
				joueur.SetNom(res.getString("nom"));
				joueur.SetPrenom(res.getString("prenom"));
				joueur.SetAge(res.getInt("age"));
				joueur.SetAdresse(res.getString("adresse"));
				joueur.SetSolde(res.getInt("solde"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return joueur;
	}

	
	
	public boolean create(Reservation obj) {
		return false;
	}
}