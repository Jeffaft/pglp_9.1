
public class Cercle implements Forme {
	private String nom;
	private Point centre;
	private int rayon;
	
	public Cercle (String nom, Point p, int r) {
		this.nom = nom;
		this.centre = p;
		this.rayon = r;
	}

	public String getNom() {
		return nom;
	}

	public Point getCentre() {
		return centre;
	}

	public int getRayon() {
		return rayon;
	}
}
