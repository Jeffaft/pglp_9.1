import java.util.List; 
import java.util.ArrayList;
import java.util.Iterator;

public class GroupeForme implements Forme {
	private String nom;
	private ArrayList<Forme>listForme = new ArrayList<Forme>();
	
	public GroupeForme (String nom) {
		this.nom = nom;
	}
	
	public void add (Forme f) {
		listForme.add(f);
	}
	
	public String toString() {
		String str = "Nom (";
		Iterator<Forme> itr = listForme.iterator();
		while (itr.hasNext()) {
			str += itr.next().toString() +", "; 
		}
		
		str += ")";
		return str;
	}
	public String getNom() {
		return nom;
	}

	public ArrayList<Forme> getListForme() {
		return listForme;
	}
	
}
