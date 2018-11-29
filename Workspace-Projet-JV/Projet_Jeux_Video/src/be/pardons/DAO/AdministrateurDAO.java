package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import be.pardons.POJO.Administrateur;


public class AdministrateurDAO extends DAO<Administrateur> {
	
	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public AdministrateurDAO(){
		super(connec);
	}
	
	//Creer
	public boolean create(Administrateur admin){		
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(admin.GetPseudo());
			
			stmt = connec.createStatement();
			String insertion = "Insert into Administrateur(num_admin_PK) values (" + id + ");";
			
			int res = stmt.executeUpdate(insertion);

			if(res == 1) //Cree
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
	public boolean delete(Administrateur admin){
		try
		{						
			stmt = connec.createStatement();
			
			String del = "delete from Administrateur where num_admin_PK = '" + admin.GetId() + "';";
			
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
	public boolean update(Administrateur admin){		
		return false;
	}
	
	//Verif
	public Administrateur verif(String pseudo, String mp){
		Administrateur admin = new Administrateur();
		try
		{
			stmt = connec.createStatement();
			
			String verif1 = "SELECT Personne.*, Administrateur.* FROM Personne INNER JOIN Administrateur ON Personne.num_pers_PK = Administrateur.num_admin_PK where Personne.pseudo = '" + pseudo + "' and Personne.mp = '" + mp +"';";
			res = stmt.executeQuery(verif1);			
			
			if(res.next()) //verif pri
			{
				admin.SetId(res.getInt("Personne.num_pers_PK"));
				admin.SetPseudo(res.getString("Personne.pseudo"));
				admin.SetNom(res.getString("Personne.nom"));
				admin.SetPrenom(res.getString("Personne.prenom"));
				admin.SetAge(res.getInt("Personne.age"));
				admin.SetAdresse(res.getString("Personne.adresse"));
			}			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return admin;
	}
	
	//List
	public List<Administrateur> list(){
		List<Administrateur> list = new ArrayList<Administrateur>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Personne.* FROM Personne INNER JOIN Administrateur ON Personne.num_pers_PK = Administrateur.num_admin_PK;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Administrateur(res.getInt("Personne.num_pers_PK"), res.getString("Personne.pseudo"), res.getString("Personne.mp"), res.getString("Personne.nom"), res.getString("Personne.prenom"), res.getInt("Personne.age"), res.getString("Personne.adresse")));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
}