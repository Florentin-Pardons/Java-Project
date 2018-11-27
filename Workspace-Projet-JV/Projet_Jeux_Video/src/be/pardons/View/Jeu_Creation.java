package be.pardons.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import be.pardons.POJO.Administrateur;
import be.pardons.POJO.Jeu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Jeu_Creation extends JFrame {

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

	//Creation de la frame
	public Jeu_Creation(Administrateur admin) {
		setTitle("Creation d'un jeu");
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
		txtNom = new JTextField();
		txtNom.setBounds(117, 22, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		//lbl tarif
		lblTarif = new JLabel("Tarif");
		lblTarif.setBounds(25, 61, 76, 14);
		contentPane.add(lblTarif);
		
		//txt tarif
		txtTarif = new JTextField();
		txtTarif.setBounds(117, 58, 86, 20);
		contentPane.add(txtTarif);
		txtTarif.setColumns(10);
		
		//lbl date sortie
		lblDateSortie = new JLabel("Date de sortie");
		lblDateSortie.setBounds(25, 94, 76, 14);
		contentPane.add(lblDateSortie);
		
		//txt date sortie
		txtDateSortie = new JTextField();
		txtDateSortie.setBounds(117, 91, 86, 20);
		contentPane.add(txtDateSortie);
		txtDateSortie.setColumns(10);
		
		//lbl dev
		lblDev = new JLabel("Developpeur");
		lblDev.setBounds(25, 131, 76, 14);
		contentPane.add(lblDev);
		
		//txt dev
		txtDev = new JTextField();
		txtDev.setBounds(117, 128, 86, 20);
		contentPane.add(txtDev);
		txtDev.setColumns(10);
		
		//lbl editeur
		lblEditeur = new JLabel("Editeur");
		lblEditeur.setBounds(25, 162, 76, 14);
		contentPane.add(lblEditeur);
		
		//txt editeur
		txtEditeur = new JTextField();
		txtEditeur.setBounds(117, 159, 86, 20);
		contentPane.add(txtEditeur);
		txtEditeur.setColumns(10);
		
		//btn valider
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String nom = txtNom.getText().toString();
				int tarif = Integer.parseInt(txtTarif.getText().toString());
				Date dateCrea = null;
				try {
					dateCrea = formatter.parse(txtDateSortie.getText().toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String dev = txtDev.getText().toString();
				String editeur = txtEditeur.getText().toString();
				
				if(!nom.isEmpty() && tarif > 0 && dateCrea != null && !dev.isEmpty() && !editeur.isEmpty())
				{
					try
					{
						Jeu jeu = new Jeu(nom, tarif, dateCrea, dev, editeur);
						if(jeu.Creer() == true){
							Jeu_Gestion p = new Jeu_Gestion(admin);
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
					System.out.println("erreur");
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
