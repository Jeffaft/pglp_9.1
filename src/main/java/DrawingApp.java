import ExceptionPers.CommandeInconnueException;
import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import Forme.GroupeForme;

public class DrawingApp {
	public static void run(GroupeForme session,String dbname) throws ExceptionPers{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		DrawingTUI tui = new DrawingTUI();
		String commande;
		
		do {	
			commande = scanner.nextLine();
			try {
				
				tui.nextCommand(commande).execute(commande,session,dbname);
			}
			catch (ExceptionPers e) {
				
			}
		}
		while (!commande.equals("exit"));					
		scanner.close();
	}
	public static void main(final String[] args) throws ExceptionPers {
		GroupeForme session = new GroupeForme("sessionActuelle");
		DrawingApp.run(session,"DessinBDD");
	}

}
