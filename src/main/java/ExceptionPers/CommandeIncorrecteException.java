package ExceptionPers;

public class CommandeIncorrecteException extends ExceptionPers {

	public CommandeIncorrecteException(String msg) {
		super("Arguments demandés :"+ msg);
	}

}
