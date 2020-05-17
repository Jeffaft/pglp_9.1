
public class Rectangle implements Forme {
	private String nom;
	private int hauteur;
	private int longueur;
	private Point bas_gauche;
	
	public Rectangle (String nom, Point p, int h, int l) {
		this.nom = nom;
		this.bas_gauche = p;
		this.hauteur = h;
		this.longueur = l;
	}
	public String getNom() {
		return nom;
	}
	public int getHauteur() {
		return hauteur;
	}
	public int getLongueur() {
		return longueur;
	}
	public Point getBas_gauche() {
		return bas_gauche;
	}	
}
