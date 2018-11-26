package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeAdmin extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JButton btnGestionUtilisateur;
	private JButton btnProfil;
	private JButton btnGestionJeux;
	private JButton btnDeconnexion;

	//Creation de la frame
	public HomeAdmin(Administrateur admin) {
		setTitle("Home Administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btn gestion users
		btnGestionUtilisateur = new JButton("Gestion des utilisateurs");
		btnGestionUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateur_Gestion p = new Utilisateur_Gestion(admin);
				p.setVisible(true);
				dispose();
			}
		});
		
		//btn profil
		btnProfil = new JButton("Profil");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profil_Admin p = new Profil_Admin(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnProfil.setBounds(308, 24, 89, 23);
		contentPane.add(btnProfil);
		btnGestionUtilisateur.setBounds(27, 126, 170, 23);
		contentPane.add(btnGestionUtilisateur);
		
		//btn gestion jeux
		btnGestionJeux = new JButton("Gestion des jeux");
		btnGestionJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu_Gestion p = new Jeu_Gestion(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnGestionJeux.setBounds(233, 126, 143, 23);
		contentPane.add(btnGestionJeux);
		
		//btn deco
		btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Accueil p = new Accueil();
				p.setVisible(true);
				dispose();
			}
		});
		btnDeconnexion.setBounds(265, 203, 110, 23);
		contentPane.add(btnDeconnexion);
	}
}
