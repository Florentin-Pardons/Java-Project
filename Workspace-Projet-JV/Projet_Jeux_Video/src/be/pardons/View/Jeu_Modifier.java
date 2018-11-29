package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Jeu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Jeu_Modifier extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JLabel lblNom;
	private JTextField txtNom;
	private JLabel lblTarif;
	private JTextField txtTarif;
	private JLabel lblDateSortie;
	private JTextField txtDateSortie;
	private JLabel lblDev;
	private JTextField txtDev;
	private JLabel lblEditeur;
	private JTextField txtEditeur;
	private JButton btnValider;
	private JButton btnRetour;

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	//Creation de la frame
	public Jeu_Modifier(Administrateur admin, Jeu jeu) {
		setTitle("Modifier un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lbl nom
		lblNom = new JLabel("Nom");
		lblNom.setBounds(25, 25, 76, 14);
		contentPane.add(lblNom);
		
		//txt nom
		txtNom = new JTextField(jeu.GetNom());
		txtNom.setBounds(136, 22, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		//lbl tarif
		lblTarif = new JLabel("Tarif");
		lblTarif.setBounds(25, 61, 76, 14);
		contentPane.add(lblTarif);
		
		//txt tarif
		txtTarif = new JTextField(Integer.toString(jeu.GetTarif()));
		txtTarif.setBounds(136, 58, 86, 20);
		contentPane.add(txtTarif);
		txtTarif.setColumns(10);
		
		//lbl date sortie
		lblDateSortie = new JLabel("Date de sortie");
		lblDateSortie.setBounds(25, 94, 76, 14);
		contentPane.add(lblDateSortie);
		
		//txt date sortie	
		txtDateSortie = new JTextField(formatter.format(jeu.GetDatesortie()));
		txtDateSortie.setBounds(136, 91, 86, 20);
		contentPane.add(txtDateSortie);
		txtDateSortie.setColumns(10);
		
		//lbl dev
		lblDev = new JLabel("Developpeur");
		lblDev.setBounds(25, 131, 76, 14);
		contentPane.add(lblDev);
		
		//txt dev
		txtDev = new JTextField(jeu.GetDeveloppeur());
		txtDev.setBounds(136, 128, 86, 20);
		contentPane.add(txtDev);
		txtDev.setColumns(10);
		
		//lbl editeur
		lblEditeur = new JLabel("Editeur");
		lblEditeur.setBounds(25, 162, 76, 14);
		contentPane.add(lblEditeur);
		
		//txt editeur
		txtEditeur = new JTextField(jeu.GetEditeur());
		txtEditeur.setBounds(136, 159, 86, 20);
		contentPane.add(txtEditeur);
		txtEditeur.setColumns(10);
		
		//btn valider
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				String nom = txtNom.getText().toString();
				String tarif = txtTarif.getText().toString();
				
				//Convertir le string en date
				Date dateCrea = null;
				try {
					dateCrea = formatter.parse(txtDateSortie.getText().toString());
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Mauvais format de date. Requis : 01/01/2001", "Erreur : ", JOptionPane.ERROR_MESSAGE);
				}
				String dev = txtDev.getText().toString();
				String editeur = txtEditeur.getText().toString();
				
				if(!nom.isEmpty() && !tarif.isEmpty() && Integer.parseInt(tarif) > 0 && dateCrea != null && !dev.isEmpty() && !editeur.isEmpty())
				{
					try
					{
						jeu.SetNom(nom);
						jeu.SetTarif(Integer.parseInt(tarif));
						jeu.SetDatesortie(dateCrea);
						jeu.SetDeveloppeur(dev);
						jeu.SetEditeur(editeur);
						
						if(jeu.Update() == true){
							JOptionPane.showMessageDialog(rootPane, "Jeu mis a jour", "Information : ", JOptionPane.INFORMATION_MESSAGE);
							Jeu_Gestion p = new Jeu_Gestion(admin);
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
		btnValider.setBounds(312, 213, 89, 23);
		contentPane.add(btnValider);
		
		//btn retour
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jeu_Gestion p = new Jeu_Gestion(admin);
				p.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(25, 213, 89, 23);
		contentPane.add(btnRetour);
	}
}
