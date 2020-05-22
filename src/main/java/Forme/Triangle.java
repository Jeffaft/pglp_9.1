package Forme;
public class Triangle implements Forme {
	private String nom;
	private Point A;
	private Point B;
	private Point C;
	
	public Triangle (String nom, Point a, Point b, Point c) {
		this.nom = nom;
		A = a;
		B = b;
		C = c;
	}
	
	public String toString() {
		return "Triangle ("+nom+", "+A.toString()+", "+B.toString()
				+", "+C.toString()+")";
	}
	public String getNom() {
		return nom;
	}

	public Point getA() {
		return A;
	}

	public Point getB() {
		return B;
	}

	public Point getC() {
		return C;
	}
}
