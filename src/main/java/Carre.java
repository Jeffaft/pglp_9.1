
public class Carre implements Forme {
	private String nom;
	private Point bas_gauche;
	private int longueur;
	
	public Carre (String nom, Point bg, int l) {
		this.nom = nom;
		this.bas_gauche = bg;
		this.longueur = l;
	}
	
	public Point getBas_gauche() {
		return this.bas_gauche;
	}
	
	public int getL() {
		return this.longueur;
	}
}
