package be.pardons.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;

public class Ex_JeuDAO {
	
	static Connection connec = JVConnection.getInstance();
	Statement stmt = null;
	ResultSet res = null;
	
	public Ex_JeuDAO(){
		//super(connec);
	}
	
	/*
	public JeuDAO(Connection conn){
		super(conn);
	}
	*/
	
	public boolean create(Ex_Jeu ex_jeux, Joueur joueur){		
		try
		{
			stmt = connec.createStatement();
			
			String insertion = "Insert into Ex_Jeu(num_Jeu_FK, num_Joueur_FK, dispo) values ('" + ex_jeux.GetJeu().GetId() + "', '"+ joueur.GetId() + "', '" + ex_jeux.GetDispo() + "');";
			int res = stmt.executeUpdate(insertion);
			
			if(res == 1) //Cree
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
	
	public boolean delete(Ex_Jeu ex_jeu){
		try
		{
			stmt = connec.createStatement();
			
			String del = "delete from Ex_Jeu where num_exjeux_PK = '" + ex_jeu.GetId() + "';";
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
	
	public boolean update(Ex_Jeu ex_jeu){
		try
		{
			stmt = connec.createStatement();
			
			String upd = "update Ex_Jeu set dispo = '" + ex_jeu.GetDispo() + "' where num_exjeux_PK = '" + ex_jeu.GetId() + "';";
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
	
	public List<Ex_Jeu> list(Joueur joueur){
		List<Ex_Jeu> list = new ArrayList<Ex_Jeu>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Ex_Jeu.num_exjeux_PK, Ex_Jeu.dispo, Jeu.* FROM Jeu INNER JOIN Ex_Jeu ON Jeu.num_jeu_PK = Ex_Jeu.num_jeu_FK where Ex_Jeu.num_joueur_FK = " + joueur.GetId() +";";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Ex_Jeu(res.getInt("Ex_Jeu.num_exjeux_PK"), new Jeu(res.getInt("Jeu.num_jeu_PK"), res.getString("Jeu.nom"), res.getInt("Jeu.tarif"), res.getDate("Jeu.datesortie"), res.getString("Jeu.developpeur"), res.getString("Jeu.editeur")), res.getBoolean("Ex_Jeu.dispo")));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
	
	//Dispo
	public List<Ex_Jeu> listdispo(){
		List<Ex_Jeu> list = new ArrayList<Ex_Jeu>();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT EX_JEU.*, JEU.* FROM EX_JEU INNER JOIN JEU ON EX_JEU.NUM_JEU_FK = JEU.NUM_JEU_PK WHERE DISPO = TRUE;";
			res = stmt.executeQuery(result);
			
			while(res.next()) //verif pri
			{
				list.add(new Ex_Jeu(res.getInt("EX_JEU.num_exjeux_PK"), new Jeu(res.getInt("Jeu.num_jeu_PK"), res.getString("Jeu.nom"), res.getInt("Jeu.tarif"), res.getDate("Jeu.datesortie"), res.getString("Jeu.developpeur"), res.getString("Jeu.editeur")), res.getBoolean("Ex_Jeu.dispo")));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		
		return list;
	}
	
	//Preteur
	public Joueur GetPreteur(Ex_Jeu ex_jeu) {
		Joueur joueur = new Joueur();
		try
		{
			stmt = connec.createStatement();
			
			String result = "SELECT Ex_Jeu.num_exjeux_PK, Joueur.*, Personne.* FROM Personne INNER JOIN (Joueur INNER JOIN Ex_Jeu ON Joueur.num_joueur_PK = Ex_Jeu.num_joueur_FK) ON Personne.num_pers_PK = Joueur.num_joueur_PK where Ex_Jeu.num_exjeux_PK =" + ex_jeu.GetId() + ";";
			res = stmt.executeQuery(result);
			
			if(res.next()) //verif pri
			{
				joueur.SetId(res.getInt("Personne.num_pers_PK"));
				joueur.SetPseudo(res.getString("Personne.pseudo"));
				joueur.SetNom(res.getString("Personne.nom"));
				joueur.SetPrenom(res.getString("Personne.prenom"));
				joueur.SetAge(res.getInt("Personne.age"));
				joueur.SetAdresse(res.getString("Personne.adresse"));
				joueur.SetSolde(res.getDouble("Joueur.solde"));
			}
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		return joueur;
	}
}