package Command;

import ExceptionPers.CommandeInconnueException;
import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import ExceptionPers.ObjetExistant;
import Forme.Carre;
import Forme.Cercle;
import Forme.GroupeForme;
import Forme.Point;
import Forme.Rectangle;
import Forme.Triangle;

public class CommandDraw implements Command  {
	
	public void execute(String commande,GroupeForme session,String dbname) 
			throws ExceptionPers{
		String[] str = commande.split(" ");
		//si trop peu d'arguments
		if (str.length <3)
			throw new CommandeIncorrecteException("typeForme, trop peu d'arguments.");
		// on vérifie si l'objet existe déjà
		if(session.contain(str[2]))
			throw new ObjetExistant(str[2]);
		//si carre
		if(str[1].equals("carre") || str[1].equals("Carre") ) {
			drawCarre(str,session);		
		}
		//si rectangle
		else if (str[1].equals("rectangle") || str[1].equals("Rectangle") ){
			drawRectangle(str,session);
		}
		//si cercle
		else if (str[1].equals("cercle") || str[1].equals("Cercle")){
			drawCercle(str,session);
		}
		//si triangle
		else if (str[1].equals("triangle") || str[1].equals("Triangle")) {
			drawTriangle(str,session);
		}
		else {
			throw new CommandeInconnueException();
		}
		System.out.println(session.toString());
	}
	public int readInt(String str) throws CommandeIncorrecteException{
		int result = 0;
		try {
			 result = Integer.parseInt(str);;
	        } catch (NumberFormatException e) {
	            throw new CommandeIncorrecteException("entier attendu seulement");
	        }
		return result;
	}
		
	public void drawCarre(String[] str,GroupeForme session) throws ExceptionPers {
		if (str.length !=5)
			throw new CommandeIncorrecteException("nom point taille");
		String nom = str[2];
		if(str[3].charAt(0)!= '(' || str[3].charAt(str[3].length()-1)!= ')')
			throw new CommandeIncorrecteException("point au format (x,y).");
		Point p = Point.fromString(str[3]);
		if ( p == null)
			throw new CommandeIncorrecteException("les coordonnées doivent être des entiers et avec deux valeurs (x,y)..");
		int taille = 0;
			 taille = this.readInt(str[4]);
	        
		 session.add(new Carre(nom,p,taille));
	}
	//draw rectangle nom (x,y) hauteur longueur
	public void drawRectangle(String[] str,GroupeForme session) throws ExceptionPers {
		if (str.length !=6)
			throw new CommandeIncorrecteException("nom point hauteur longeur");
		String nom = str[2];
		if(str[3].charAt(0)!= '(' || str[3].charAt(str[3].length()-1)!= ')')
			throw new CommandeIncorrecteException("point au format (x,y).");
		Point p = Point.fromString(str[3]);
		if ( p == null)
			throw new CommandeIncorrecteException("les coordonnées doivent être des entiers et avec deux valeurs (x,y).");
		int hauteur = this.readInt(str[4]);
		int longueur = this.readInt(str[5]);    
		 session.add(new Rectangle(nom,p,hauteur,longueur));
	}
	//draw cercle nom (x,y) rayon
	public void drawCercle(String[] str,GroupeForme session) throws ExceptionPers {
		if (str.length !=5)
			throw new CommandeIncorrecteException("nom point rayon");
		String nom = str[2];
		if(str[3].charAt(0)!= '(' || str[3].charAt(str[3].length()-1)!= ')')
			throw new CommandeIncorrecteException("point au format (x,y).");
		Point p = Point.fromString(str[3]);
		if ( p == null)
			throw new CommandeIncorrecteException("les coordonnées doivent être des entiers et avec deux valeurs (x,y).");
		int rayon = this.readInt(str[4]);
		 session.add(new Cercle(nom,p,rayon));
	}
	
	//draw triangle nom (x,y) (x,y) (x,y)
		public void drawTriangle(String[] str,GroupeForme session) throws ExceptionPers {
			if (str.length !=6)
				throw new CommandeIncorrecteException("nom point rayon");
			String nom = str[2];
			if(str[3].charAt(0)!= '(' || str[3].charAt(str[3].length()-1)!= ')'
					|| str[4].charAt(0)!= '(' || str[4].charAt(str[4].length()-1)!= ')'
					|| str[5].charAt(0)!= '(' || str[5].charAt(str[5].length()-1)!= ')')
				throw new CommandeIncorrecteException("point au format (x,y).");
			Point a = Point.fromString(str[3]);
			Point b = Point.fromString(str[4]);
			Point c = Point.fromString(str[5]);
			if ( a == null || b == null || c == null)
				throw new CommandeIncorrecteException("les coordonnées doivent être des entiers et avec deux valeurs (x,y).");
			 session.add(new Triangle(nom,a,b,c));
		}

}
