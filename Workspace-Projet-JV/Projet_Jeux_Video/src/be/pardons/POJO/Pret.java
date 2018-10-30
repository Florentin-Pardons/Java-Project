package be.pardons.POJO;

import java.util.Calendar;
import java.util.Date;

public class Pret {

	//Variable
	private static int refBase = 0;
	private String ref;
	private Date datedeb;
	private Date datefin;
	private Ex_Jeu exjeu;
	
	//Setteur
	public void SetRef(String ref) {
		this.ref = ref;
	}
	
	//Getteur
	public String GetRef() {
		return this.ref;
	}
	
	//Constructeur
	public Pret(String ref, Date datedeb, Date datefin, Ex_Jeu exjeu)
	{
		this.ref = ref;
		this.datedeb = datedeb;
		this.datefin = datefin;
		this.exjeu = exjeu;
	}
	
	public Pret(int datefin, Ex_Jeu exjeu)
	{
		this.ref = "&" + refBase;
		refBase++;
		this.datedeb = new Date();
		
		//Add
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, datefin*7);
		
		this.datefin = c.getTime();
		this.exjeu = exjeu;
	}
	
	// Tostring
	@Override
	public String toString() {
		return ref + " " + datedeb + " " + datefin + " " + exjeu ;
	}
}