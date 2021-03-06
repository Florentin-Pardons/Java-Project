package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Joueur;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profil_Joueur extends JFrame {

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
	private JLabel lblSolde;
	private JTextField txtSolde;
	private JButton btnRetour;
	private JButton btnModifier;
	
	//Creation de la frame
	public Profil_Joueur(Joueur joueur) {
		setTitle("Profil Joueur");
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
		txtPseudo = new JTextField(joueur.GetPseudo());
		txtPseudo.setEnabled(false);
		txtPseudo.setBounds(99, 25, 110, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		//lbl nom
		lblNom = new JLabel("Nom");
		lblNom.setBounds(22, 56, 46, 14);
		contentPane.add(lblNom);
		
		//txt nom
		txtNom = new JTextField(joueur.GetNom());
		txtNom.setBounds(99, 56, 110, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		//lbl prenom
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(22, 90, 46, 14);
		contentPane.add(lblPrenom);
		
		//txt prenom
		txtPrenom = new JTextField(joueur.GetPrenom());
		txtPrenom.setBounds(99, 87, 110, 20);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		//lbl age
		lblAge = new JLabel("Age");
		lblAge.setBounds(22, 121, 46, 14);
		contentPane.add(lblAge);
		
		//txt age
		txtAge = new JTextField(joueur.GetAge().toString());
		txtAge.setBounds(99, 118, 110, 20);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		//lbl adresse
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(22, 152, 67, 14);
		contentPane.add(lblAdresse);
		
		//txt adresse
		txtadresse = new JTextField(joueur.GetAdresse());
		txtadresse.setBounds(99, 149, 110, 20);
		contentPane.add(txtadresse);
		txtadresse.setColumns(10);
		
		//lbl solde
		lblSolde = new JLabel("Solde");
		lblSolde.setBounds(219, 28, 46, 14);
		contentPane.add(lblSolde);
		
		//txt solde
		txtSolde = new JTextField(Double.toString(joueur.GetSolde()));
		txtSolde.setEditable(false);
		txtSolde.setBounds(270, 25, 86, 20);
		contentPane.add(txtSolde);
		txtSolde.setColumns(10);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeJoueur p = new HomeJoueur(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(10, 195, 89, 23);
		contentPane.add(btnRetour);
		
		//btn modifier
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom = txtNom.getText().toString();
				String prenom = txtPrenom.getText().toString();
				String age = txtAge.getText().toString();
				String adresse = txtadresse.getText().toString();
				
				if(!nom.isEmpty() && !prenom.isEmpty() && !age.isEmpty() && Integer.parseInt(age) > 0 && !adresse.isEmpty())
				{
					try {
						joueur.SetNom(nom);
						joueur.SetPrenom(prenom);
						joueur.SetAge(Integer.parseInt(age));
						joueur.SetAdresse(adresse);
						
						if(joueur.Update() == true)
						{			
							JOptionPane.showMessageDialog(rootPane, "Profil mis a jour", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							Profil_Joueur p = new Profil_Joueur(joueur);
							p.setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(rootPane, "Erreur system : echec de l'update", "Erreur : ", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception err)
					{
						System.out.println(err);
					}
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Erreur d'encodage dans le formulaire", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnModifier.setBounds(267, 195, 89, 23);
		contentPane.add(btnModifier);
	}
}
