package be.pardons.DAO;

import be.pardons.POJO.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonneDAO extends DAO<Personne>{
	
	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public PersonneDAO(){
		super(connec);
	}
	
	public PersonneDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Personne pers){		
		try
		{
			stmt = connec.createStatement();
			
			String insertion = "Insert into Personne(pseudo, mp, nom, prenom, age, adresse) values ('" + pers.GetPseudo() + "', '"+ pers.GetMp() + "', '"+ pers.GetNom() +  "', '" + pers.GetPrenom() +  "', '" + pers.GetAge()+ "', '" + pers.GetAdresse() + "');";
			stmt.executeUpdate(insertion);
			if(res.next()) //Cree
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
	
	public boolean verif(Personne pers){		
		try
		{
			stmt = connec.createStatement();
			
			String verif = "select pseudo from Personne where pseudo = '" + pers.GetPseudo() + "';";
			res = stmt.executeQuery(verif);
			
			if(res.next()) //verif pri
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
	
	public Personne verif(String pseudo, String mp){
		Personne pers = new Personne();
		try
		{
			stmt = connec.createStatement();
			
			String verif = "select * from Personne where pseudo = '" + pseudo + "' and mp = '" + mp +"';";
			res = stmt.executeQuery(verif);
			if(res.next()) //verif pri
			{
				pers = new Personne(res.getInt("num_pers_PK"), res.getString("pseudo"), res.getString("mp"), res.getString("nom"), res.getString("prenom"), res.getInt("age"), res.getString("adresse"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pers;
	}
	
	public boolean delete(Personne pers){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Personne where pseudo = '" + pers.GetPseudo() + "';";
			stmt.executeUpdate(del);
			
			/*
			if(res.next()) //del ok
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
	
	public boolean update(Personne pers){
		try
		{
			stmt = connec.createStatement();
			
			//String upd = "update Personne set pseudo = '" + pers.GetPseudo() + " mp = " + pers.GetMp() + " mp = " + pers.GetMp() + " nom = " + pers.GetNom() + " prenom = " + pers.GetPrenom() + " age = " + pers.GetAge() + " adresse = " + pers.GetAdresse() + " where pseudo = '" + pers.GetPseudo() + "';";
			String upd = "update Personne set nom = '" + pers.GetNom() + "', prenom = '" + pers.GetPrenom() + "', age = '" + pers.GetAge() + "', adresse = '" + pers.GetAdresse() + "' where num_pers_PK = '" + pers.GetId() + "';";
			stmt.executeUpdate(upd);
			
			/*
			if(res.next()) //update ok
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
	
	public Personne find(String pseudo){
		Personne pers = new Personne();
		try
		{
			stmt = connec.createStatement();
			
			String find = "SELECT * FROM Personne WHERE pseudo = " + pseudo;
			res = stmt.executeQuery(find);
			
			if(res.first()) //update ok
			{
				pers = new Personne(res.getInt("num_pers_PK"), res.getString("pseudo"), res.getString("mp"), res.getString("nom"), res.getString("prenom"), res.getInt("age"), res.getString("adresse"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return pers;
	}
	
	public int findId(String pseudo){
		int id = 0;
		try
		{
			stmt = connec.createStatement();
			
			String find = "SELECT num_pers_PK FROM Personne WHERE pseudo = " + pseudo;
			res = stmt.executeQuery(find);
			
			if(res.first()) //update ok
			{
				id = res.getInt("num_pers_PK");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
}