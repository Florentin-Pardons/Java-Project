package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Jeu;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Jeu_Gestion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JList<Jeu> list;
	private JScrollPane scrollPane;
	private JButton btnAjouterJeu;
	private JButton btnModifierJeu;
	private JButton btnSupprimerJeu;
	private JButton btnRetour;
	private DefaultListModel<Jeu> listModelJeu;

	//Creation de la frame
	public Jeu_Gestion(Administrateur admin) {
		
		//create the model and add elements
		listModelJeu = new DefaultListModel<>();
		for (Jeu num : Jeu.List())  
        { 
            listModelJeu.addElement(num);
        }
		
		setTitle("Gestion des jeux");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//list jeux
		list = new JList<>(listModelJeu);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 list.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent event) {
	            	if (event.getClickCount() == 2) {
	            		Jeu jeu = list.getSelectedValue();
	            		JOptionPane.showMessageDialog(rootPane, jeu.message(), "Information Jeu : ", JOptionPane.INFORMATION_MESSAGE);
	            	  }
	            }
	        });	
		
		//Scroll
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(31, 11, 371, 165);
		contentPane.add(scrollPane);
		
		//btn ajouter
		btnAjouterJeu = new JButton("Ajouter");
		btnAjouterJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu_Creation p = new Jeu_Creation(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnAjouterJeu.setBounds(31, 187, 89, 23);
		contentPane.add(btnAjouterJeu);
		
		//btn modifier
		btnModifierJeu = new JButton("Modifier");
		btnModifierJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu j = list.getSelectedValue();
				if(j != null)
				{
					Jeu_Modifier p = new Jeu_Modifier(admin, list.getSelectedValue());
					p.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Aucun jeu selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnModifierJeu.setBounds(163, 187, 100, 23);
		contentPane.add(btnModifierJeu);
		
		//btn supprimer
		btnSupprimerJeu = new JButton("Supprimer");
		btnSupprimerJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu jeu = list.getSelectedValue();
				
				if(jeu != null)
				{
					try
					{
						if(jeu.Delete() == true)
						{
							Jeu_Gestion p = new Jeu_Gestion(admin);
							p.setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(rootPane, "Erreur system : echec delete", "Erreur : ", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception err)
					{
						System.out.println(err);
					}
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Aucun jeu selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnSupprimerJeu.setBounds(302, 187, 100, 23);
		contentPane.add(btnSupprimerJeu);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeAdmin p = new HomeAdmin(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(313, 227, 89, 23);
		contentPane.add(btnRetour);
	}
}
