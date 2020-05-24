package Command;

import DAO.DataBase;
import ExceptionPers.ExceptionPers;
import Forme.GroupeForme;

public class CommandInitBD implements Command {
	public void execute(String commande,GroupeForme session,String dbname) {
		DataBase.create(dbname);
	}
			
	
}
