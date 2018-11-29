package be.pardons.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Joueur;
import javax.swing.JRadioButton;

public class Utilisateur_Creation extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JLabel lblPseudo;
	private JTextField txtPseudo;
	private JLabel lblNom;
	private JTextField txtNom;
	private JLabel lblPrenom;
	private JTextField txtPrenom;
	private JLabel lblAge;
	private JTextField txtAge;
	private JLabel lblAdresse;
	private JTextField txtadresse;
	private JLabel lblMp1;
	private JTextField txtMp1;
	private JLabel lblMp2;
	private JTextField txtMp2;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnJoueur;
	private JButton btnRetour;
	private JButton btnEnregister;

	//Creation de la frame
	public Utilisateur_Creation(Administrateur admin) {
		setTitle("Creation d'un utilisateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lbl pseudo
		lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(22, 28, 51, 14);
		contentPane.add(lblPseudo);
		
		//txt pseudo
		txtPseudo = new JTextField();
		txtPseudo.setBounds(99, 25, 86, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		//lbl nom
		lblNom = new JLabel("Nom");
		lblNom.setBounds(22, 56, 46, 14);
		contentPane.add(lblNom);
		
		//txt nom
		txtNom = new JTextField();
		txtNom.setBounds(99, 56, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		//lbl prenom
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(22, 90, 46, 14);
		contentPane.add(lblPrenom);
		
		//txt prenom
		txtPrenom = new JTextField();
		txtPrenom.setBounds(99, 87, 86, 20);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		//lbl age
		lblAge = new JLabel("Age");
		lblAge.setBounds(22, 121, 46, 14);
		contentPane.add(lblAge);
		
		//txt age
		txtAge = new JTextField();
		txtAge.setBounds(99, 118, 86, 20);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		//lbl adresse
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(22, 152, 67, 14);
		contentPane.add(lblAdresse);
		
		//txt adresse
		txtadresse = new JTextField();
		txtadresse.setBounds(99, 149, 86, 20);
		contentPane.add(txtadresse);
		txtadresse.setColumns(10);
		
		//lbl mp1
		lblMp1 = new JLabel("Mot de passe N\u00B01");
		lblMp1.setBounds(244, 28, 100, 14);
		contentPane.add(lblMp1);
		
		//txt mp1
		txtMp1 = new JTextField();
		txtMp1.setBounds(244, 56, 86, 20);
		contentPane.add(txtMp1);
		txtMp1.setColumns(10);
		
		//lbl mp2
		lblMp2 = new JLabel("Mot de passe confirmation");
		lblMp2.setBounds(222, 90, 148, 14);
		contentPane.add(lblMp2);
		
		//txt mp2
		txtMp2 = new JTextField();
		txtMp2.setBounds(244, 118, 86, 20);
		contentPane.add(txtMp2);
		txtMp2.setColumns(10);
		
		//radio admin
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setSelected(true);
		rdbtnAdmin.setBounds(222, 148, 100, 23);
		contentPane.add(rdbtnAdmin);
		
		//radio joueur
		rdbtnJoueur = new JRadioButton("Joueur");
		rdbtnJoueur.setBounds(329, 148, 73, 23);
		contentPane.add(rdbtnJoueur);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnJoueur);
	    group.add(rdbtnAdmin);
	    
	    //btn retour
		btnRetour = new JButton("Annuler");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateur_Gestion p = new Utilisateur_Gestion(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(22, 196, 89, 23);
		contentPane.add(btnRetour);
		
		//btn enr
		btnEnregister = new JButton("Enregister");
		btnEnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pseudo = txtPseudo.getText().toString();
				String nom = txtNom.getText().toString();
				String prenom = txtPrenom.getText().toString();
				String age = txtAge.getText().toString();
				String adresse = txtadresse.getText().toString();
				String mdp1 = txtMp1.getText().toString();
				String mdp2 = txtMp2.getText().toString();
				
				if(!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !age.isEmpty() && Integer.parseInt(age) > 0 && !adresse.isEmpty() && !mdp1.isEmpty() && !mdp2.isEmpty() && mdp1.equals(mdp2))
				{
					if(rdbtnJoueur.isSelected())
					{
						try
						{
							Joueur joueur = new Joueur(pseudo, mdp1, nom, prenom, Integer.parseInt(age), adresse);
							if(joueur.Verif() == false) {
								if(joueur.Creer() == true){
									JOptionPane.showMessageDialog(rootPane, "Utilisateur créé", "Information : ", JOptionPane.INFORMATION_MESSAGE);
									Utilisateur_Gestion p = new Utilisateur_Gestion(admin);
									p.setVisible(true);
									dispose();
								}
								else
									JOptionPane.showMessageDialog(rootPane, "Erreur system : echec de l'update", "Erreur : ", JOptionPane.ERROR_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(rootPane, "Utilisateur deja existant", "Erreur : ", JOptionPane.ERROR_MESSAGE);
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
							Administrateur admin = new Administrateur(pseudo, mdp1, nom, prenom, Integer.parseInt(age), adresse);
							if(admin.Verif() == false) {
								if(admin.Creer() == true){
									Utilisateur_Gestion p = new Utilisateur_Gestion(admin);
									p.setVisible(true);
									dispose();
								}
								else
									JOptionPane.showMessageDialog(rootPane, "Erreur system : echec de l'update", "Erreur : ", JOptionPane.ERROR_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(rootPane, "Utilisateur deja existant", "Erreur : ", JOptionPane.ERROR_MESSAGE);
						}
						catch(Exception err)
						{
							System.out.println(err);
						}
					}
					else
						JOptionPane.showMessageDialog(rootPane, "Aucun type d'utilisateur selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Erreur d'encodage dans le formulaire", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnEnregister.setBounds(255, 196, 115, 23);
		contentPane.add(btnEnregister);
	}
}
