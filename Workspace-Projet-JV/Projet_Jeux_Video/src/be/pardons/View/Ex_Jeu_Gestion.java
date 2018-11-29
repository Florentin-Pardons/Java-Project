package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ex_Jeu_Gestion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JList<Ex_Jeu> list;
	private JScrollPane scrollPane;
	private JButton btnCreer;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnRetour;
	private DefaultListModel<Ex_Jeu> listModelEx_Jeu;

	//Creation de la frame
	public Ex_Jeu_Gestion(Joueur joueur) {
		
		//create the model and add elements
		listModelEx_Jeu = new DefaultListModel<>();
		
		for (Ex_Jeu num : joueur.GetListJeu())  
        { 
			listModelEx_Jeu.addElement(num);
        }
		
		setTitle("Gestion des exemplaires");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//list d'ex jeu
		list = new JList<>(listModelEx_Jeu);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
            	if (event.getClickCount() == 2) {
            		Jeu jeu = list.getSelectedValue().GetJeu();
            		JOptionPane.showMessageDialog(rootPane, jeu.message(), "Information Jeu : ", JOptionPane.INFORMATION_MESSAGE);
            	  }
            }
        });
		
		//Scroll
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 383, 113);
		contentPane.add(scrollPane);
		
		//btn creer
		btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ex_Jeu_Creation p = new Ex_Jeu_Creation(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnCreer.setBounds(23, 155, 89, 23);
		contentPane.add(btnCreer);
		
		//btn modifier
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ex_Jeu ex_jeu = list.getSelectedValue();
				
				if(ex_jeu != null)
				{
					try
					{
						if(ex_jeu.GetDispo() == true)
							ex_jeu.SetDispo(false);
						else
							ex_jeu.SetDispo(true);
						
						if(ex_jeu.Update()==true)
						{
							Ex_Jeu_Gestion p = new Ex_Jeu_Gestion(joueur);
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
					JOptionPane.showMessageDialog(rootPane, "Aucun exemplaire selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnModifier.setBounds(164, 155, 89, 23);
		contentPane.add(btnModifier);
		
		//btn supprimer
		btnSupprimer = new JButton("Delete");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ex_Jeu ex_jeu = list.getSelectedValue();
				
				if(ex_jeu != null)
				{
					try
					{
						if(ex_jeu.Delete()==true)
						{
							joueur.GetListJeu().clear();
							joueur.SetListJeu(Ex_Jeu.List(joueur));
							
							JOptionPane.showMessageDialog(rootPane, "Exemplaire delete", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							Ex_Jeu_Gestion p = new Ex_Jeu_Gestion(joueur);
							p.setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(rootPane, "Erreur system : echec du delete", "Erreur : ", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception err)
					{
						System.out.println(err);
					}
				}
				else
					JOptionPane.showMessageDialog(rootPane, "Aucun exemplaire selectionné", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnSupprimer.setBounds(304, 155, 89, 23);
		contentPane.add(btnSupprimer);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeJoueur p = new HomeJoueur(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(304, 211, 89, 23);
		contentPane.add(btnRetour);
	}
}
