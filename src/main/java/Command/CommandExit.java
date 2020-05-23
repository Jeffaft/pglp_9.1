package Command;

public class CommandExit implements Command {

	public void execute(String command) {
		System.out.println("****FIN DU PROGRAMME****");
		System.exit(0);
	}

}
