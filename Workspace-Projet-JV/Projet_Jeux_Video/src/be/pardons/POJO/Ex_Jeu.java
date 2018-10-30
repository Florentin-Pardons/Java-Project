package be.pardons.POJO;

public class Ex_Jeu {

	//Variable
	private static int refBase = 0;
	private String ref;
	private Jeu jeu;
	private boolean dispo;
		
	//Setteur
	public void SetJeu(Jeu jeu) {
		this.jeu = jeu;
	}	
	public void SetRef(String ref) {
		this.ref = ref;
	}
	
	public void SetDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	//Getteur
	public Jeu GetJeu() {
		return this.jeu;
	}
	
	public String GetRef() {
		return this.ref;
	}
	
	public boolean GetDispo() {
		return this.dispo;
	}
	
	//Constructeur
	public Ex_Jeu(Jeu jeu, String ref, boolean dispo)
	{
		this.jeu = jeu;
		this.ref = ref;
		this.dispo = dispo;
	}
	
	public Ex_Jeu(Jeu jeu)
	{
		this.jeu = jeu;
		this.ref = "#" + refBase;
		refBase++;
		this.dispo = false;
	}
	
	// Tostring
	@Override
	public String toString() {
		return jeu + " " + ref + " " + dispo;
	}
}