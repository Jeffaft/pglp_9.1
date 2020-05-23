import ExceptionPers.CommandeInconnueException;
import Command.*;
public class DrawingTUI {
	public Command nextCommand(String entree) throws CommandeInconnueException {
		String[] commande = entree.split(" ");
		if (commande[0].equals("draw")) {
			return new CommandDraw();
		}
		else if (commande[0].equals("group")) {
			return new CommandGroup();
		}
		else if (commande[0].equals("moove")) {
			return new CommandMoove();
		}
		else if (commande[0].equals("delete")) {
			return new CommandDelete();
		}
		else if (commande[0].equals("save")) {
			return new CommandSave();
		}
		else if (commande[0].equals("exit")) {
			return new CommandExit();
		}
		else {
			throw new CommandeInconnueException();
		}
		
	}
}
