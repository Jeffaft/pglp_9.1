package Command;

import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import ExceptionPers.ObjetInexistant;
import Forme.GroupeForme;

public class CommandMoove implements Command {
	//moove nom x y
	public void execute(String commande,GroupeForme session,String dbname) throws ExceptionPers {
		String[] str = commande.split(" ");
		//si trop peu d'arguments
		if (str.length !=4) {
			throw new CommandeIncorrecteException("nom point x-direction y-direction");
		}		
		else if (!session.contain(str[1])) {
			throw new ObjetInexistant(str[1]);
		}
		else {
			int x = this.readInt(str[2]);
			int y = this.readInt(str[3]);
			session.getForme(str[1]).moove(x, y);
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

}
