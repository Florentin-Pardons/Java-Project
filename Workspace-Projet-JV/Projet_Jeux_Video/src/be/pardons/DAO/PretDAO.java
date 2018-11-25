package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Pret;

public class PretDAO {

	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public PretDAO(){
		//super(connec);
	}
	
	/*
	public PretDAO(Connection conn){
		super(conn);
	}
	*/
	
	public boolean create(Pret pret, Joueur joueur){		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			stmt = connec.createStatement();
			//#" + formatter.format(pret.GetDatesortie()) + "#
			String insertion = "INSERT INTO Pret( num_joueur_FK, num_exjeu_FK, datedeb, datefin) values ('" + joueur.GetId() + "', '"+ pret.GetEx_Jeu().GetId() + "', #"  + formatter.format(pret.GetDateDeb()) + "#, #" + formatter.format(pret.GetDateFin()) + "#);";
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
	
	public boolean delete(Pret pret){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Pret where num_pret_PK = '" + pret.GetId() + "';";
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
	
	public boolean update(Pret pret){
		try
		{
			/*
			stmt = connec.createStatement();
			
			String upd = "update Pret set nom = '" + pret.GetNom() + "', tarif = '" + pret.GetTarif() + "', datesortie = '" + pret.GetDatesortie() + "', developpeur = '" + pret.GetDeveloppeur() + "', editeur = '" + pret.GetEditeur() + "'  where num_pret_PK = '" + pret.GetId() + "';";
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
	
	public boolean updatedate(Pret pret){
		try
		{
			System.out.println(pret);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			stmt = connec.createStatement();
			
			String upd = "update Pret set datefin = #"  + formatter.format(pret.GetDateFin()) + "# where num_pret_PK = '" + pret.GetId() + "';";
			System.out.println(upd);
			int res = stmt.executeUpdate(upd);
			
			if(res == 1) //update ok
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
	
	public List<Pret> list(Joueur joueur){
		List<Pret> list = new ArrayList<Pret>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Jeu.*, Pret.*, Ex_Jeu.* FROM (Jeu INNER JOIN Ex_Jeu ON Jeu.num_jeu_PK = Ex_Jeu.num_jeu_FK) INNER JOIN Pret ON Ex_Jeu.num_exjeux_PK = Pret.num_exjeu_FK where Pret.num_joueur_FK = " + joueur.GetId() +";";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Pret(res.getInt("Pret.num_pret_PK"), new Ex_Jeu(res.getInt("num_exjeux_PK"), new Jeu(res.getInt("num_jeu_PK"), res.getString("nom"), res.getInt("tarif"), res.getDate("datesortie"), res.getString("developpeur"), res.getString("editeur")), res.getBoolean("dispo")), res.getDate("datedeb"), res.getDate("datefin")));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
}