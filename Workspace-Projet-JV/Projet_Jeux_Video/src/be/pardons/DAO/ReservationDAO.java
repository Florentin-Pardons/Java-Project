package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Reservation;

public class ReservationDAO {

	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public ReservationDAO(){
		//super(connec);
	}
	
	/*
	public ReservationDAO(Connection conn){
		super(conn);
	}
	*/
	
	public boolean create(Reservation reservation, Joueur joueur){		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			stmt = connec.createStatement();
			//#" + formatter.format(reservation.GetDatesortie()) + "#
			String insertion = "INSERT INTO Reservation( num_joueur_FK, num_jeu_FK, dateRes ) values ('" + joueur.GetId() + "', '" + reservation.GetJeu().GetId() + "', #"  + formatter.format(reservation.GetDateRes()) + "#);";
			System.out.println(insertion);
			int res1 = stmt.executeUpdate(insertion);

			if(res1 == 1) //Cree
			{
				return true;
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return false;
	}
	
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
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return false;
	}
	
	public boolean update(Reservation reservation){
		try
		{/*
			stmt = connec.createStatement();
			
			String upd = "update Reservation set nom = '" + reservation.GetNom() + "', tarif = '" + reservation.GetTarif() + "', datesortie = '" + reservation.GetDatesortie() + "', developpeur = '" + reservation.GetDeveloppeur() + "', editeur = '" + reservation.GetEditeur() + "'  where num_reservation_PK = '" + reservation.GetId() + "';";
			int res = stmt.executeUpdate(upd);
			
			if(res == 1) //update ok
			{
				return true;
			}*/
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return false;
	}
	
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
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
}