package Command;

import DAO.DataBase;
import Forme.GroupeForme;

public class CommandDeleteBD implements Command{
	public void execute(String commande,GroupeForme session,String dbname) {
		DataBase.reset(dbname);
	}
}
