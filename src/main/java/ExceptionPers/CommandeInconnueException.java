package ExceptionPers;

public class CommandeInconnueException extends Exception{
	public CommandeInconnueException() {
		System.out.println("Commande inconnue, veuillez vous referrez au manuel pour savoir les commandes autorisees;");
	}
	
}
