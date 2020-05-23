package ExceptionPers;

public class CommandeInconnueException extends ExceptionPers{
	public CommandeInconnueException() {
		super ("Commande inconnue, veuillez vous referrez au manuel pour savoir les commandes autorisees.");
	}
	
}
