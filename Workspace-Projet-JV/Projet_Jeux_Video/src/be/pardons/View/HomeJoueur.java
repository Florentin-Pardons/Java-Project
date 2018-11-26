package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Joueur;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeJoueur extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JButton btnResGestion;
	private JButton btnPretGestion;
	private JButton btnEx_JeuGestion;
	private JButton btnProfil;
	private JButton btnDeconnexion;

	//Creation de la frame
	public HomeJoueur(Joueur joueur) {
		setTitle("Home Joueur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btn gestion res
		btnResGestion = new JButton("Gestion des Reservations");
		btnResGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reservation_Gestion p = new Reservation_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnResGestion.setBounds(20, 58, 187, 23);
		contentPane.add(btnResGestion);
		
		//btn gestion prets
		btnPretGestion = new JButton("Gestion des Prets");
		btnPretGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pret_Gestion p = new Pret_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnPretGestion.setBounds(236, 58, 137, 23);
		contentPane.add(btnPretGestion);
		
		//btn gestion ex jeu
		btnEx_JeuGestion = new JButton("Gestion Exemplaire");
		btnEx_JeuGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ex_Jeu_Gestion p = new Ex_Jeu_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnEx_JeuGestion.setBounds(20, 106, 187, 23);
		contentPane.add(btnEx_JeuGestion);
		
		//btn profil
		btnProfil = new JButton("Profil");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profil_Joueur p = new Profil_Joueur(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnProfil.setBounds(335, 24, 89, 23);
		contentPane.add(btnProfil);
		
		//btn deco
		btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Accueil p = new Accueil();
				p.setVisible(true);
				dispose();
			}
		});
		btnDeconnexion.setBounds(309, 227, 115, 23);
		contentPane.add(btnDeconnexion);
	}
}
