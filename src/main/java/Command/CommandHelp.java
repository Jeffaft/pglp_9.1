package Command;

import Drawing.DrawingTUI;
import ExceptionPers.ExceptionPers;
import Forme.GroupeForme;

public class CommandHelp implements Command {
	public void execute(String commande,GroupeForme session,String dbname) 
			throws ExceptionPers {
		String display = "Commande possibles : \n"
				+ "initBD\n"
				+ "deleteBD\n"
				+ "draw\n"
				+ "erase\n"
				+ "save\n"
				+ "moove\n"
				+ "recap\n"
				+ "load\n"
				+ "exit\n"
				+ "group\n"
				+ "delete\n"
				+ "Pour plus d'informations sur le fonctionnement des commandes, consulter le README.MD.";
		DrawingTUI.afficheResultat(display);
	}
}
