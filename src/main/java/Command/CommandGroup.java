package Command;

import ExceptionPers.ExceptionPers;
import ExceptionPers.InexistantException;
import ExceptionPers.ObjetExistant;
import ExceptionPers.ObjetInexistant;
import Forme.GroupeForme;

public class CommandGroup  implements Command{
	//group nomGroupe nom1 nom2...
	public void execute(String commande,GroupeForme session,String dbname) throws ExceptionPers{
		String[] str = commande.split(" ");
		//si déjà objet
		if (session.contain(str[1])) {
			throw new ObjetExistant(str[1]);
		}
		else {
			GroupeForme newGP = new GroupeForme(str[1]);
			int i = str.length;
			for(int j=2; j<i; j++ ) {
				if (session.contain(str[j])) {
					newGP.add(session.getForme(str[j]));
					session.delete(str[j]);
				}
				else {
					throw new ObjetInexistant(str[j]);
				}
			}
			session.add(newGP);
			
		}
		System.out.println(session.toString());

	}
	
}
