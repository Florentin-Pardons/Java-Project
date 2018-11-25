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
	
	public AdministrateurDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Administrateur joueur){		
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(joueur.GetPseudo());
			
			stmt = connec.createStatement();
			
			String insertion = "Insert into Admin(num_pers_FK) values ('" + id + "');";
			res = stmt.executeQuery(insertion);
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
	
	public boolean delete(Administrateur admin){
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(admin.GetPseudo());
			
			stmt = connec.createStatement();
			
			String del = "delete from Joueur where num_pers_FK = '" + id + "';";
			res = stmt.executeQuery(del);
			
			if(res.next()) //del ok
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
	
	public boolean update(Administrateur admin){
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(admin.GetPseudo());
			
			stmt = connec.createStatement();
			
			String upd = "update Joueur set solde = '" + admin + "where num_pers_FK = '" + id +"';";
			res = stmt.executeQuery(upd);
			
			if(res.next()) //update ok
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
	
	public Administrateur find(String pseudo){
		Administrateur admin = new Administrateur();
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(pseudo);
			stmt = connec.createStatement();
			
			String find = "SELECT * FROM Joueur WHERE num_pers_FK = '" + id +"';";
			res = stmt.executeQuery(find);
			
			if(res.first()) //update ok
			{
				//pers = new Joueur(res.getString("pseudo"), res.getString("mp"), res.getString("nom"), res.getString("prenom"), res.getInt("age"), res.getString("adresse"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return admin;
	}
	
	public List<Administrateur> list(){
		List<Administrateur> list = new ArrayList<Administrateur>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Administrateur.solde, Personne.* FROM Personne INNER JOIN Administrateur ON Personne.num_pers_PK = Administrateur.num_admin_PK;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Administrateur(res.getInt("Personne.num_pers_PK"), res.getString("Personne.pseudo"), res.getString("Personne.mp"), res.getString("Personne.nom"), res.getString("Personne.prenom"), res.getInt("Personne.age"), res.getString("Personne.adresse")));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
}