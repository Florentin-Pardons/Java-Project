package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Pret;
import be.pardons.POJO.Reservation;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Reservation_Creation extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JTextField txtRecherche;
	private JList<Jeu> list;
	private JScrollPane scrollPane;
	private JButton btnValider;
	private JButton btnRetour;
	private DefaultListModel<Jeu> listModelJeu = new DefaultListModel<>();

	//Liste de Jeux
	private ArrayList<Jeu> getJeux()
    {
		ArrayList<Jeu> Listjeux = new ArrayList<Jeu>();
		for (Jeu num : Jeu.List())
        { 
			Listjeux.add(num);
        }
		return Listjeux;
    }
	
	//Remplir listModelJeu	
    private void bindData(){
        //foreach
        getJeux().stream().forEach((jeu) -> {
            listModelJeu.addElement(jeu);
        });
    }
    
    //Filter
    @SuppressWarnings({ "unchecked" })
	private void recherFiltre(String nomRech)
    {
        @SuppressWarnings("rawtypes")
		DefaultListModel jeuxFiltre = new DefaultListModel();
        @SuppressWarnings("rawtypes")
		ArrayList Jeux = getJeux();

        Jeux.stream().forEach((jeu) -> {
            String jeuNom=jeu.toString().toLowerCase();
            if (jeuNom.contains(nomRech.toLowerCase())) {
                jeuxFiltre.addElement(jeu);
            }
        });
        listModelJeu = jeuxFiltre;
        list.setModel(listModelJeu);
    }
    
    //Creation de la frame
	public Reservation_Creation(Joueur joueur) {		
		
		this.bindData();
		
		setTitle("Creation d'un reservation");
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
		scrollPane.setBounds(23, 11, 387, 208);
		contentPane.add(scrollPane);
		
		//txt recherhce
		txtRecherche = new JTextField();
		txtRecherche.setBounds(23, 230, 113, 20);
		txtRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            	recherFiltre(txtRecherche.getText());
            }
        });
		contentPane.add(txtRecherche);
		
		//btn valider
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu jeu = list.getSelectedValue();
				
				if(jeu!=null)
				{
					try
					{
						Reservation res = new Reservation(jeu);
						if(res.Creer(joueur) == true)
						{							
							for (Reservation reserv : Reservation.List(joueur)) {
						        if (reserv.GetJeu().GetId() == jeu.GetId()) {
						            res =  reserv;
						        }
						    }
							
							if(res.VerifJoueur(joueur)==true) //verifie les Ex Dispo + Solde
							{
								res.Delete();
								JOptionPane.showMessageDialog(rootPane, "Exemplaire disponible trouvé => voir Gestion des prets", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(rootPane, "Reservation Créé", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							
							//Refresh
							joueur.GetListRes().clear();
							joueur.SetListRes(Reservation.List(joueur));
							
							joueur.GetListPret().clear();
							joueur.SetListPret(Pret.List(joueur));
							
							//Retour vers gestion
							Reservation_Gestion p = new Reservation_Gestion(joueur);
							p.setVisible(true);
							dispose();
						}
						else
							JOptionPane.showMessageDialog(rootPane, "Erreur system : echec lors de la creation de la reservation", "Erreur : ", JOptionPane.ERROR_MESSAGE);
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
		btnValider.setBounds(222, 230, 89, 23);
		contentPane.add(btnValider);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reservation_Gestion p = new Reservation_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(321, 230, 89, 23);
		contentPane.add(btnRetour);
	}
}
