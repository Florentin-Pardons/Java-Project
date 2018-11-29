package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Reservation;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reservation_Gestion extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JList<Reservation> list;
	private JScrollPane scrollPane;
	private JButton btnCreerReservation;
	private JButton btnModifierReservation;
	private JButton btnSupprimerReservation;
	private JButton btnRetour;
	private DefaultListModel<Reservation> listModelReservation;
	
	//Creation de la frame
	public Reservation_Gestion(Joueur joueur) {
		
		//create the model and add elements
		listModelReservation = new DefaultListModel<>();
		/*for (Reservation num : Reservation.List(joueur))
        { 
			listModelReservation.addElement(num);
        }*/
		
		for (Reservation num : joueur.GetListRes())
        { 
			listModelReservation.addElement(num);
        }
		
		setTitle("Gestion des Reservations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//list reservation
		list = new JList<>(listModelReservation);
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
		scrollPane.setBounds(20, 11, 392, 165);
		contentPane.add(scrollPane);
		
		//btn creer
		btnCreerReservation = new JButton("Creer");
		btnCreerReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reservation_Creation p = new Reservation_Creation(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnCreerReservation.setBounds(20, 192, 104, 23);
		contentPane.add(btnCreerReservation);
		
		//btn modifier
		btnModifierReservation = new JButton("Modifier");
		btnModifierReservation.setEnabled(false);
		btnModifierReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
			}
		});
		btnModifierReservation.setBounds(171, 192, 104, 23);
		contentPane.add(btnModifierReservation);
		
		//btn supprimer
		btnSupprimerReservation = new JButton("Supprimer");
		btnSupprimerReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reservation res = list.getSelectedValue();
				
				if(res != null)
				{
					try
					{
						if(res.Delete() == true)
						{						
							//Refresh
							joueur.GetListRes().clear();
							joueur.SetListRes(Reservation.List(joueur));
							
							JOptionPane.showMessageDialog(rootPane, "Reservation Supprimée", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							Reservation_Gestion p = new Reservation_Gestion(joueur);
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
					JOptionPane.showMessageDialog(rootPane, "Aucune reservation selectionnée", "Erreur : ", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnSupprimerReservation.setBounds(308, 192, 104, 23);
		contentPane.add(btnSupprimerReservation);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeJoueur p = new HomeJoueur(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(323, 227, 89, 23);
		contentPane.add(btnRetour);
	}
}
