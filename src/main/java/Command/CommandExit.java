package Command;

import Forme.GroupeForme;

public class CommandExit implements Command {

	public void execute(String command,GroupeForme session,String dbname) {
		System.out.println("****FIN DU PROGRAMME****");
		System.exit(0);
	}

}
