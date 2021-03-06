package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import be.pardons.POJO.Joueur;

public class JoueurDAO extends DAO<Joueur>{
	
	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public JoueurDAO(){
		super(connec);
	}
	
	//Creer
	public boolean create(Joueur joueur){		
		try
		{
			PersonneDAO pers = new PersonneDAO();
			int id = pers.findId(joueur.GetPseudo());
			
			stmt = connec.createStatement();
			String insertion = "Insert into Joueur(num_joueur_PK, solde) values (" + id + ", '" + joueur.GetSolde() + "');";
			//String insertion = "Insert into Joueur(num_joueur_PK, solde) values ((\"SELECT num_pers_PK from personne WHERE pseudo='" + joueur.GetPseudo() + "'\")" + ", '" + joueur.GetSolde() + "');";
			
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
	public boolean delete(Joueur joueur){
		try
		{			
			stmt = connec.createStatement();
			
			String del = "delete from Joueur where num_joueur_PK = '" + joueur.GetId() + "';";
			
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
	public boolean update(Joueur joueur){		
		return false;
	}
	
	//Update solde
	public boolean updatesolde(Joueur joueur){
		try
		{	
			System.out.println(joueur);
			stmt = connec.createStatement();
			
			String upd = "update Joueur set solde = " + joueur.GetSolde() + " where num_joueur_PK = " + joueur.GetId() + ";";
			System.out.println(upd);
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
	
	//Verif
	public Joueur verif(String pseudo, String mp){
		Joueur joueur = new Joueur();
		try
		{
			stmt = connec.createStatement();
			
			String verif = "SELECT Personne.*, Joueur.* FROM Personne INNER JOIN Joueur ON Personne.num_pers_PK = Joueur.num_joueur_PK where Personne.pseudo = '" + pseudo + "' and Personne.mp = '" + mp +"';";
			res = stmt.executeQuery(verif);			
			
			if(res.next()) //verif ok
			{
				joueur.SetId(res.getInt("Personne.num_pers_PK"));
				joueur.SetPseudo(res.getString("Personne.pseudo"));
				joueur.SetNom(res.getString("Personne.nom"));
				joueur.SetPrenom(res.getString("Personne.prenom"));
				joueur.SetAge(res.getInt("Personne.age"));
				joueur.SetAdresse(res.getString("Personne.adresse"));
				joueur.SetSolde(res.getInt("Joueur.solde"));
			}			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return joueur;
	}
	
	//List
	public List<Joueur> list(){
		List<Joueur> list = new ArrayList<Joueur>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Joueur.solde, Personne.* FROM Personne INNER JOIN Joueur ON Personne.num_pers_PK = Joueur.num_joueur_PK;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Joueur(res.getInt("Joueur.solde"), res.getInt("Personne.num_pers_PK"), res.getString("Personne.pseudo"), res.getString("Personne.mp"), res.getString("Personne.nom"), res.getString("Personne.prenom"), res.getInt("Personne.age"), res.getString("Personne.adresse")));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
}