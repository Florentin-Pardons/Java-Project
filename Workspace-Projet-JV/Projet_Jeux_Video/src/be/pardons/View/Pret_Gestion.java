package be.pardons.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Pret;

public class Pret_Gestion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JList<Pret> list;
	private JScrollPane scrollPane;
	private JButton btnCreerPret;
	private JButton btnSupprimerPret;
	private JButton btnRetour;
	private DefaultListModel<Pret> listModelPret;

	//Creation de la frame
	public Pret_Gestion(Joueur joueur) {
		
		//create the model and add elements
		listModelPret = new DefaultListModel<>();
		/*for (Pret num : Pret.List(joueur))  
        { 
			listModelPret.addElement(num);
        }*/
		
		for (Pret num : joueur.GetListPret())  
        { 
			listModelPret.addElement(num);
        }
		
		setTitle("Gestion des prets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//list pret
		list = new JList<>(listModelPret);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 list.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent event) {
	            	if (event.getClickCount() == 2) {
	            		Jeu jeu = list.getSelectedValue().GetEx_Jeu().GetJeu();
	            		JOptionPane.showMessageDialog(rootPane, jeu.message(), "Information Jeu : ", JOptionPane.INFORMATION_MESSAGE);
	            	  }
	            }
	        });	
		
		//Scroll
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 414, 166);
		contentPane.add(scrollPane);
		
		//btn creer
		btnCreerPret = new JButton("Modifier");
		btnCreerPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pret pret = list.getSelectedValue();
				if(pret != null)
				{
					Pret_Modifier p = new Pret_Modifier(joueur, list.getSelectedValue());
					p.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Aucun pret selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnCreerPret.setBounds(60, 188, 104, 23);
		contentPane.add(btnCreerPret);
		
		//btn supprimer
		btnSupprimerPret = new JButton("Supprimer");
		btnSupprimerPret.setEnabled(false);
		btnSupprimerPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Automatique
			}
		});
		btnSupprimerPret.setBounds(268, 188, 104, 23);
		contentPane.add(btnSupprimerPret);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeJoueur p = new HomeJoueur(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(318, 227, 89, 23);
		contentPane.add(btnRetour);
	}

}
