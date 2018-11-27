package be.pardons.View;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Reservation;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Ex_Jeu_Creation extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JList<Jeu> list;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnDispoVrai;
	private JRadioButton rdbtnDispoFaux;
	private ButtonGroup group;
	private JButton btnRetour;
	private JButton btnValider;
	private DefaultListModel<Jeu> listModelJeu;
	
	//Creation de la frame
	public Ex_Jeu_Creation(Joueur joueur) {
		
		//create the model and add elements
		listModelJeu = new DefaultListModel<>();
		for (Jeu num : Jeu.List())  
        { 
            listModelJeu.addElement(num);
        }

		setTitle("Creation d'un exemplaire de jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//List de jeux
		list = new JList<>(listModelJeu);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent event) {
            	if (event.getClickCount() == 2) {
            		Jeu jeu = list.getSelectedValue();
            		JOptionPane.showMessageDialog(rootPane, jeu.toString(), "Information Jeu : ", JOptionPane.INFORMATION_MESSAGE);
            	  }
            }
        });
		
		//Scroll
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 11, 377, 148);
		contentPane.add(scrollPane);
		
		//radio dispo
		rdbtnDispoVrai = new JRadioButton("Dispo");
		rdbtnDispoVrai.setSelected(true);
		rdbtnDispoVrai.setBounds(20, 195, 109, 23);
		contentPane.add(rdbtnDispoVrai);
		
		//radio non dispo
		rdbtnDispoFaux = new JRadioButton("Pas dispo");
		rdbtnDispoFaux.setBounds(153, 195, 109, 23);
		contentPane.add(rdbtnDispoFaux);
		
		//Group the radio buttons.
	    group = new ButtonGroup();
	    group.add(rdbtnDispoVrai);
	    group.add(rdbtnDispoFaux);
	    
	    //btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ex_Jeu_Gestion p = new Ex_Jeu_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(30, 225, 89, 23);
		contentPane.add(btnRetour);
		
		//btn valider
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu jeu = list.getSelectedValue();
				boolean d = rdbtnDispoVrai.isSelected();
				
				if(jeu!=null)
				{
					try
					{
						Ex_Jeu ex_jeu = new Ex_Jeu(jeu, d);
						if(ex_jeu.Creer(joueur) == true){
							
							//Refresh
							joueur.GetListJeu().clear();
							joueur.SetListJeu(Ex_Jeu.List(joueur));
							
							if(d==true)
							{
								
								Reservation res = new Reservation(jeu);
								for (Reservation reserv : Reservation.List()) {
							        if (reserv.GetJeu().GetId() == jeu.GetId()) {
							            res =  reserv;
							        }
							    }
								
								if(res.VerifPreteur(joueur)==true) //verifie les Ex Dispo + Solde
									JOptionPane.showMessageDialog(rootPane, "Exemplaire disponible trouvé => voir Gestion des prets", "Information : ", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(rootPane, "Reservation Créé", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							}
							
							
							Ex_Jeu_Gestion p = new Ex_Jeu_Gestion(joueur);
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
					System.out.println("ereur");
			}
		});
		btnValider.setBounds(298, 210, 89, 23);
		contentPane.add(btnValider);
	}
}
