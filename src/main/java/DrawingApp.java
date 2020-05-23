import ExceptionPers.CommandeInconnueException;

public class DrawingApp {
	public static void run() throws CommandeInconnueException{
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		DrawingTUI tui = new DrawingTUI();
		String commande;
		
		do {	
			commande = scanner.nextLine();
			try {
				
				tui.nextCommand(commande).execute(commande);
			}
			catch (CommandeInconnueException e) {
				
			}
		}
		while (!commande.equals("exit"));					
		scanner.close();
	}
	public static void main(final String[] args) throws CommandeInconnueException {
		DrawingApp.run();
	}

}
