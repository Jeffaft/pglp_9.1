package ExceptionPers;

public class ObjetInexistant extends ExceptionPers{

	public ObjetInexistant(String nom) {
		super("Lobjet :" +nom +" n'est pas dessiné.");
	}

}
