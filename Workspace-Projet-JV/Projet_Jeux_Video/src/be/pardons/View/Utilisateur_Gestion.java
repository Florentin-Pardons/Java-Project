package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Joueur;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Utilisateur_Gestion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;	
	private JList<Joueur> listJoueur;
	private JScrollPane scrollPaneJoueur;
	private JButton btnModifierJoueur;
	private JButton btnSupprimerJoueur;
	private JList<Administrateur> listAdmin;
	private JScrollPane scrollPaneAdmin;
	private JButton btnModifierAdministrateur;
	private JButton btnSupprimerAdministrateur;
	private JButton btnAjouterUtilisateur;
	private JButton btnRetour;
	private DefaultListModel<Joueur> listModelJoueur;
	private DefaultListModel<Administrateur> listModelAdmin;
	
	//Creation de la frame
	public Utilisateur_Gestion(Administrateur admin) {
		
		//create the model and add elements
		listModelJoueur = new DefaultListModel<>();
		for (Joueur num : Joueur.List())  
        { 
			listModelJoueur.addElement(num);
        }
		
		listModelAdmin = new DefaultListModel<>();
		for (Administrateur num : Administrateur.List())  
        { 
			listModelAdmin.addElement(num);
        }
		
		setTitle("Gestion des utilisateurs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//list joueurs
		listJoueur = new JList<>(listModelJoueur);
		listJoueur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJoueur.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent event) {
	            	if (event.getClickCount() == 2) {
	            		Joueur joueur = listJoueur.getSelectedValue();
	            		JOptionPane.showMessageDialog(rootPane, joueur.message(), "Information Joueur : ", JOptionPane.INFORMATION_MESSAGE);
	            	  }
	            }
	        });
		
		//Scroll
		scrollPaneJoueur = new JScrollPane(listJoueur);
		scrollPaneJoueur.setBounds(21, 11, 388, 67);
		contentPane.add(scrollPaneJoueur);
		
		//btn modifier
		btnModifierJoueur = new JButton("Modifier Joueur");
		btnModifierJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModifierJoueur.setBounds(21, 89, 162, 23);
		contentPane.add(btnModifierJoueur);
		
		//btn supprimer
		btnSupprimerJoueur = new JButton("Supprimer Joueur");
		btnSupprimerJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Joueur joueur = listJoueur.getSelectedValue();
				
				if(joueur != null)
				{
					try
					{
						joueur.Delete();
						Utilisateur_Gestion p = new Utilisateur_Gestion(admin);
						p.setVisible(true);
						dispose();
					}
					catch(Exception err)
					{
						System.out.println(err);
					}
				}
				else
					System.out.println("ereur");
			}
		});
		btnSupprimerJoueur.setBounds(247, 89, 162, 23);
		contentPane.add(btnSupprimerJoueur);
		
		//list admin
		listAdmin = new JList<>(listModelAdmin);
		listAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
            	if (event.getClickCount() == 2) {
            		Administrateur admini = listAdmin.getSelectedValue();
            		JOptionPane.showMessageDialog(rootPane, admini.message(), "Information Administrateur : ", JOptionPane.INFORMATION_MESSAGE);
            	  }
            }
        });
		
		//Scroll
		scrollPaneAdmin = new JScrollPane(listAdmin);
		scrollPaneAdmin.setBounds(21, 123, 388, 59);
		contentPane.add(scrollPaneAdmin);
		
		//btn modifier
		btnModifierAdministrateur = new JButton("Modifier Administrateur");
		btnModifierAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModifierAdministrateur.setBounds(21, 193, 162, 23);
		contentPane.add(btnModifierAdministrateur);
		
		//btn supprimer
		btnSupprimerAdministrateur = new JButton("Supprimer Administrateur");
		btnSupprimerAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSupprimerAdministrateur.setBounds(247, 193, 162, 23);
		contentPane.add(btnSupprimerAdministrateur);
		
		//btn ajouter
		btnAjouterUtilisateur = new JButton("Ajouter Utilisateur");
		btnAjouterUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utilisateur_Creation p = new Utilisateur_Creation(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnAjouterUtilisateur.setBounds(21, 227, 130, 23);
		contentPane.add(btnAjouterUtilisateur);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeAdmin p = new HomeAdmin(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(320, 227, 89, 23);
		contentPane.add(btnRetour);
	}
}
