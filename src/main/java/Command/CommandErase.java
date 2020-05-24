package Command;

import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import ExceptionPers.ObjetInexistant;
import Forme.GroupeForme;

public class CommandErase implements Command{
	//delete nom
	public void execute(String commande, GroupeForme session, String dbname ) 
		throws ExceptionPers
	{
		String[] str = commande.split(" ");
		//si trop peu d'arguments
		if (str.length !=2) {
			throw new CommandeIncorrecteException("nom");
		}
		else if (!session.contain(str[1])) {
			throw new ObjetInexistant(str[1]);
		}
		else {
			session.delete(str[1]);
		}
		System.out.println(session.toString());
	}

}
