package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import be.pardons.POJO.Jeu;


public class JeuDAO {
	
	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public JeuDAO(){
		//super(connec);
	}
	
	/*
	public JeuDAO(Connection conn){
		super(conn);
	}
	*/
	
	public boolean create(Jeu jeu){		
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			stmt = connec.createStatement();
			//#" + formatter.format(jeu.GetDatesortie()) + "#
			String insertion = "INSERT INTO Jeu( nom, tarif, datesortie, developpeur, editeur ) values ('" + jeu.GetNom() + "', '"+ jeu.GetTarif() + "', #"  + formatter.format(jeu.GetDatesortie()) + "#, '" + jeu.GetDeveloppeur() + "', '" + jeu.GetEditeur() + "');";
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
	
	public boolean delete(Jeu jeu){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Jeu where num_jeu_PK = '" + jeu.GetId() + "';";
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
	
	public boolean update(Jeu jeu){
		try
		{
			stmt = connec.createStatement();
			
			String upd = "update Jeu set nom = '" + jeu.GetNom() + "', tarif = '" + jeu.GetTarif() + "', datesortie = '" + jeu.GetDatesortie() + "', developpeur = '" + jeu.GetDeveloppeur() + "', editeur = '" + jeu.GetEditeur() + "'  where num_jeu_PK = '" + jeu.GetId() + "';";
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
	
	public List<Jeu> list(){
		List<Jeu> list = new ArrayList<Jeu>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "select * from Jeu;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Jeu(res.getInt("num_jeu_PK"), res.getString("nom"), res.getInt("tarif"), res.getDate("datesortie"), res.getString("developpeur"), res.getString("editeur")));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
}