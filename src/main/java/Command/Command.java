package Command;

import ExceptionPers.CommandeInconnueException;
import ExceptionPers.CommandeIncorrecteException;
import ExceptionPers.ExceptionPers;
import Forme.GroupeForme;

public interface Command{
	public void execute(String commande,GroupeForme session,String dbname) 
			throws ExceptionPers;
}
