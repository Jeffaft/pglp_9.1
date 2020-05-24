package Command;

import DAO.DataBase;
import Drawing.DrawingTUI;
import Forme.GroupeForme;

public class CommandRecap implements Command{
	public void execute(String commande,GroupeForme session,String dbname) {
		DrawingTUI.afficheResultat(DataBase.recapBDD(dbname));
	}

}
