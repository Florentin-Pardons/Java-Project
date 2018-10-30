package be.pardons.POJO;

import java.util.Date;

public class Reservation {

	//Variable
	private static int refBase = 0;
	private String ref;
	private Date dateres ;
	private Jeu jeu;
	
	//Getteur
	public String GetRef() {
		return ref;
	}
	
	//Setteur
	public void SetRef(String ref) {
		this.ref = ref;
	}
	
	//Constructeur
	public Reservation(String ref, Date dateres, Jeu jeu)
	{
		this.ref = ref;
		this.dateres = dateres;
		this.jeu = jeu;
	}
	
	public Reservation(Jeu jeu)
	{
		this.ref = "&" + refBase;
		refBase++;
		this.dateres = new Date();
		this.jeu = jeu;
	}
	
	// Tostring
	@Override
	public String toString() {
		return ref + " " + dateres + " " + jeu;
	}	
}