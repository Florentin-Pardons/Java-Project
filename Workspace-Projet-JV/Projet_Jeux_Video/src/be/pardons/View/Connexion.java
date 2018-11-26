package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Pret;
import be.pardons.POJO.Reservation;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Connexion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JLabel lblPseudo;
	private JTextField txtPseudo;
	private JLabel lblMp;
	private JTextField txtMp;
	private JRadioButton rdbtnJoueur;
	private JRadioButton rdbtnAdmin;
	private JButton btnRetour;
	private JButton btnValider;
	private ButtonGroup group;
	
	//Creation de la frame
	public Connexion() {
		setTitle("Connexion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lbl pseudo
		lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(29, 28, 46, 14);
		contentPane.add(lblPseudo);
		
		//txt pseudo
		txtPseudo = new JTextField();
		txtPseudo.setBounds(177, 25, 86, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		//lbl mp
		lblMp = new JLabel("Mot de passe");
		lblMp.setBounds(29, 62, 89, 14);
		contentPane.add(lblMp);
		
		//txt mp
		txtMp = new JTextField();
		txtMp.setBounds(177, 59, 86, 20);
		contentPane.add(txtMp);
		txtMp.setColumns(10);
		
		//radio joueur
		rdbtnJoueur = new JRadioButton("Joueur");
		rdbtnJoueur.setSelected(true);
		rdbtnJoueur.setBounds(29, 95, 109, 23);
		contentPane.add(rdbtnJoueur);
		
		//radio admin
		rdbtnAdmin = new JRadioButton("Administrateur");
		rdbtnAdmin.setBounds(173, 95, 126, 23);
		contentPane.add(rdbtnAdmin);
		
		//Group the radio buttons.
	    group = new ButtonGroup();
	    group.add(rdbtnJoueur);
	    group.add(rdbtnAdmin);
	    
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Accueil a = new Accueil();
				a.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(29, 143, 89, 23);
		contentPane.add(btnRetour);
	    
		//btn valider
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pseudo = txtPseudo.getText().toString();
				String mp = txtMp.getText().toString();
				
				if(!pseudo.isEmpty() && !mp.isEmpty())
				{
					if(rdbtnJoueur.isSelected())
					{
						try
						{
							Joueur joueur = Joueur.Verif(pseudo, mp);
							if(joueur.GetPseudo() != null) {
								
								//Init les listes
								joueur.SetListRes(Reservation.List(joueur));
								joueur.SetListPret(Pret.List(joueur));
								joueur.SetListJeu(Ex_Jeu.List(joueur));
								
								HomeJoueur p = new HomeJoueur(joueur);
								p.setVisible(true);
								dispose();
							}
						}
						catch(Exception err)
						{
							System.out.println(err);
						}
					}
					else if(rdbtnAdmin.isSelected())
					{
						try
						{
							Administrateur admin = Administrateur.Verif(pseudo, mp);
							if(admin.GetPseudo() != null) {
								HomeAdmin p = new HomeAdmin(admin);
								p.setVisible(true);
								dispose();
							}
						}
						catch(Exception err)
						{
							System.out.println(err);
						}
					}
					else
					{
						System.out.println("Erreur");
					}
				}
				else
					System.out.println("Erreur");
			}
		});
		btnValider.setBounds(174, 143, 89, 23);
		contentPane.add(btnValider);
	}
}
