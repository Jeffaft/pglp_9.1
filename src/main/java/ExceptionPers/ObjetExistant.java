package ExceptionPers;

public class ObjetExistant extends ExceptionPers {

	public ObjetExistant(String msg) {
		super("Objet déjà existant : "+msg);
	}

}
