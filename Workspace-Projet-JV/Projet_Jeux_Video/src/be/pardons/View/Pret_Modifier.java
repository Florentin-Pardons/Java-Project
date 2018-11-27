package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Ex_Jeu;
import be.pardons.POJO.Joueur;
import be.pardons.POJO.Pret;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;

public class Pret_Modifier extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JLabel lblDateDebut;
	private JTextField txtDebPret;
	private JLabel lblDateFin;
	private JTextField txtFinPret;
	private JLabel lblIdExemplaire;
	private JTextField txtIdEx_Jeu;
	private JLabel lblNomJeu;
	private JTextField txtNomJeu;
	private JLabel lblTarifJeu;
	private JTextField txtTarif;
	private JLabel lblPreteur;
	private JLabel lblNom;
	private JTextField txtNomPreteur;
	private JLabel lblPrenom;
	private JTextField txtPrenomPreteur;
	private JLabel lblAdresse;
	private JTextField txtAddrPreteur;
	private JLabel lblVotreSolde;
	private JTextField txtSolde;
	private JLabel lblNombreDeSemaines;
	private JSpinner spinnerAddSem;
	
	//Creation de la frame
	public Pret_Modifier(Joueur joueur, Pret pret) {
		
		Joueur preteur = Ex_Jeu.Preteur(pret.GetEx_Jeu());
		
		setTitle("Modifier un pret");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setBounds(21, 14, 62, 14);
		contentPane.add(lblDateDebut);
		
		txtDebPret = new JTextField(pret.GetDateDeb().toString());
		txtDebPret.setEditable(false);
		txtDebPret.setBounds(112, 11, 86, 20);
		contentPane.add(txtDebPret);
		txtDebPret.setColumns(10);
		
		lblDateFin = new JLabel("Date Fin");
		lblDateFin.setBounds(21, 46, 46, 14);
		contentPane.add(lblDateFin);
		
		txtFinPret = new JTextField(pret.GetDateFin().toString());
		txtFinPret.setEditable(false);
		txtFinPret.setBounds(112, 42, 86, 20);
		contentPane.add(txtFinPret);
		txtFinPret.setColumns(10);
		
		lblIdExemplaire = new JLabel("Id Exemplaire");
		lblIdExemplaire.setBounds(21, 76, 75, 14);
		contentPane.add(lblIdExemplaire);
		
		txtIdEx_Jeu = new JTextField(String.valueOf(pret.GetEx_Jeu().GetId()));
		txtIdEx_Jeu.setEditable(false);
		txtIdEx_Jeu.setBounds(112, 73, 86, 20);
		contentPane.add(txtIdEx_Jeu);
		txtIdEx_Jeu.setColumns(10);
		
		lblNomJeu = new JLabel("Nom Jeu");
		lblNomJeu.setBounds(21, 107, 46, 14);
		contentPane.add(lblNomJeu);
		
		txtNomJeu = new JTextField(pret.GetEx_Jeu().GetJeu().GetNom());
		txtNomJeu.setEditable(false);
		txtNomJeu.setBounds(112, 104, 86, 20);
		contentPane.add(txtNomJeu);
		txtNomJeu.setColumns(10);
		
		lblTarifJeu = new JLabel("Tarif Jeu");
		lblTarifJeu.setBounds(21, 138, 46, 14);
		contentPane.add(lblTarifJeu);
		
		txtTarif = new JTextField(String.valueOf(pret.GetEx_Jeu().GetJeu().GetTarif()));
		txtTarif.setEditable(false);
		txtTarif.setBounds(112, 135, 86, 20);
		contentPane.add(txtTarif);
		txtTarif.setColumns(10);
		
		lblPreteur = new JLabel("PRETEUR");
		lblPreteur.setBounds(240, 14, 46, 14);
		contentPane.add(lblPreteur);
		
		lblNom = new JLabel("Nom");
		lblNom.setBounds(240, 45, 46, 14);
		contentPane.add(lblNom);
		
		txtNomPreteur = new JTextField(preteur.GetNom());
		txtNomPreteur.setEditable(false);
		txtNomPreteur.setBounds(296, 42, 86, 20);
		contentPane.add(txtNomPreteur);
		txtNomPreteur.setColumns(10);
		
		lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(240, 76, 46, 14);
		contentPane.add(lblPrenom);
		
		txtPrenomPreteur = new JTextField(preteur.GetPrenom());
		txtPrenomPreteur.setEditable(false);
		txtPrenomPreteur.setBounds(296, 73, 86, 20);
		contentPane.add(txtPrenomPreteur);
		txtPrenomPreteur.setColumns(10);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(240, 107, 46, 14);
		contentPane.add(lblAdresse);
		
		txtAddrPreteur = new JTextField(preteur.GetAdresse());
		txtAddrPreteur.setEditable(false);
		txtAddrPreteur.setBounds(296, 104, 86, 20);
		contentPane.add(txtAddrPreteur);
		txtAddrPreteur.setColumns(10);
		
		lblVotreSolde = new JLabel("Votre Solde");
		lblVotreSolde.setBounds(21, 197, 67, 14);
		contentPane.add(lblVotreSolde);
		
		txtSolde = new JTextField(Double.toString(joueur.GetSolde()));
		txtSolde.setEditable(false);
		txtSolde.setBounds(112, 197, 86, 20);
		contentPane.add(txtSolde);
		txtSolde.setColumns(10);
		
		lblNombreDeSemaines = new JLabel("Nombre de semaines \u00E0 Ajouter");
		lblNombreDeSemaines.setBounds(10, 231, 147, 14);
		contentPane.add(lblNombreDeSemaines);
		
		spinnerAddSem = new JSpinner();
		spinnerAddSem.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerAddSem.setBounds(167, 228, 46, 20);
		contentPane.add(spinnerAddSem);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( (Integer) spinnerAddSem.getValue() > 0 && (Integer) spinnerAddSem.getValue() < 5 ) {
					try {
						int res = (Integer) spinnerAddSem.getValue() * 7;
						
						if(joueur.GetSolde() >= pret.GetEx_Jeu().GetJeu().GetTarif()*(Integer) spinnerAddSem.getValue())
						{
							//Date fin							
							//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");							
							
							Calendar c = Calendar.getInstance();
							c.setTime(pret.GetDateFin());
							c.add(Calendar.DATE, res);
							
							pret.SetDateFin(c.getTime());
							pret.UpdateDate();
							
							//Refresh
							joueur.GetListPret().clear();
							joueur.SetListPret(Pret.List(joueur));
							
							//Solde du joueur
							double newsolde = joueur.GetSolde() - pret.GetEx_Jeu().GetJeu().GetTarif()*(Integer) spinnerAddSem.getValue();
							joueur.SetSolde(newsolde);
							joueur.UpdateSolde();
							
							//Solde du preteur
							newsolde = preteur.GetSolde() + pret.GetEx_Jeu().GetJeu().GetTarif()*(Integer) spinnerAddSem.getValue();
							preteur.SetSolde(newsolde);
							preteur.UpdateSolde();
							
							Pret_Gestion p = new Pret_Gestion(joueur);
							p.setVisible(true);
							dispose();
						}
						else
							System.out.println("Pas assez de solde");
					}
					catch(Exception err)
					{
						System.out.println(err);
					}
				}
					
			}
		});
		btnValider.setBounds(296, 193, 89, 23);
		contentPane.add(btnValider);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pret_Gestion p = new Pret_Gestion(joueur);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(296, 227, 89, 23);
		contentPane.add(btnRetour);
	}
}
