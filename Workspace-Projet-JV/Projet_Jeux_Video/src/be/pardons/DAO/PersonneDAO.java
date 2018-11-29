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
	
	//Creer
	public boolean create(Personne pers){		
		try
		{
			stmt = connec.createStatement();
			
			String insertion = "Insert into Personne(pseudo, mp, nom, prenom, age, adresse) values ('" + pers.GetPseudo() + "', '"+ pers.GetMp() + "', '"+ pers.GetNom() +  "', '" + pers.GetPrenom() +  "', '" + pers.GetAge()+ "', '" + pers.GetAdresse() + "');";
			int res = stmt.executeUpdate(insertion);
			
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
	
	//Delete
	public boolean delete(Personne pers){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Personne where pseudo = '" + pers.GetPseudo() + "';";
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
	public boolean update(Personne pers){
		try
		{
			stmt = connec.createStatement();
			
			String upd = "update Personne set nom = '" + pers.GetNom() + "', prenom = '" + pers.GetPrenom() + "', age = '" + pers.GetAge() + "', adresse = '" + pers.GetAdresse() + "' where num_pers_PK = '" + pers.GetId() + "';";
			int res = stmt.executeUpdate(upd);
			
			if(res == 1) //update ok
			{
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Verif (Inscription)
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
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Find (Creation d'un user)
	public int findId(String pseudo){
		int id = 0;
		try
		{
			stmt = connec.createStatement();
			
			String find = "SELECT num_pers_PK FROM Personne WHERE pseudo = '" + pseudo + "';";
			res = stmt.executeQuery(find);
			
			if(res.next()) //update ok
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