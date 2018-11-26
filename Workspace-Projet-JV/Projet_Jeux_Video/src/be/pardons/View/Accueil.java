package be.pardons.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Accueil extends JFrame {

	//Variables des elements graphique
	private JPanel contentPane;
	private JButton btnInscription;
	private JButton btnConnexion;

	//Main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Creation de la frame
	public Accueil() {
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btn inscription
		btnInscription = new JButton("S'inscrire");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inscription i = new Inscription();
				i.setVisible(true);
				dispose();
			}
		});
		btnInscription.setBounds(66, 96, 89, 23);
		contentPane.add(btnInscription);
		
		//btn connexion
		btnConnexion = new JButton("Se connecter");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connexion c = new Connexion();
				c.setVisible(true);
				dispose();
			}
		});
		btnConnexion.setBounds(227, 96, 111, 23);
		contentPane.add(btnConnexion);
	}
}
